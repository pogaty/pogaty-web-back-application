package poga.docs.partnermicroservice.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import poga.docs.partnermicroservice.ServiceMapper;
import poga.docs.partnermicroservice.models.Collaborator;
import poga.docs.partnermicroservice.models.CollaboratorDTO;
import poga.docs.partnermicroservice.models.Services;
import poga.docs.partnermicroservice.repositories.CollaboratorRepository;
import poga.docs.partnermicroservice.services.CollaboratorService;

@RestController
@RequestMapping("/collabs")
public class CollaboratorController {
    private final CollaboratorRepository collaboratorRepository;
    private final CollaboratorService collaboratorService;
    private final ServiceMapper serviceMapper;

    private final String FOLDER_PATH = System.getProperty( "user.dir" ).concat("\\partner-microservice\\Asset-image\\");

    @Autowired
    public CollaboratorController(CollaboratorRepository collaboratorRepository,
            CollaboratorService collaboratorService, ServiceMapper serviceMapper) {
        this.collaboratorRepository = collaboratorRepository;
        this.collaboratorService = collaboratorService;
        this.serviceMapper = serviceMapper;
    }

    @GetMapping()
    public ResponseEntity<?> getAllCollaborators() {
        return ResponseEntity.ok(collaboratorService.findAllCollaborators());
    }

    @GetMapping("/{collaborator_id}")
    public ResponseEntity<?> getColloabratorByCollaborator_id(@PathVariable long collaborator_id) {
        Optional<Collaborator> optCollaborator = collaboratorRepository.findById(collaborator_id);

        // check if id exists in db
        if (!optCollaborator.isPresent()) {
            // return error message 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Collaborator Not Found");

        }
        Collaborator collaborators = optCollaborator.get();
        return ResponseEntity.ok(collaborators);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> getNameColloabrator(@PathVariable String name) {
        Optional<Collaborator> collabOpt = collaboratorService.findByName(name);

        if (!collabOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("collab not found.");
        }

        Collaborator collab = collabOpt.get();
        return ResponseEntity.ok(collab);
    }

    @PutMapping("/{collaborator_id}/image")
    public ResponseEntity<String> updateCollaboratorImage(@PathVariable Long collaborator_id,
            @RequestParam("file") MultipartFile file) {
        Optional<Collaborator> optionalCollab = collaboratorRepository.findById(collaborator_id);

        if (optionalCollab.isPresent()) {
            Collaborator collaborator = optionalCollab.get();

            try {
                // Update the image only
                String updatedImage = collaboratorService.uploadImageToFileSystem(file);
                collaborator.setFileImage(updatedImage);
                collaboratorRepository.save(collaborator);
                return ResponseEntity.ok("Collaborator image updated");
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to update Collaborator image");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Collaborator not found");
        }
    }

    @GetMapping("/image/{fileImage}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileImage) {
        try {
            byte[] imageData = collaboratorService.downloadImageFromFileSystem(fileImage);
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.valueOf("image/png")) // Set the appropriate image type
                    .body(imageData);
        } catch (IOException e) {
            // Handle the IOException (e.g., log, return an error response)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
        }
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<?> downloadImageFromFileSystemById(@PathVariable Long id) {
        try {
            Optional<Collaborator> collabData = collaboratorRepository.findById(id);

            if (collabData.isPresent()) {
                String filePath = collabData.get().getFileImage();
                File imageFile = new File(FOLDER_PATH + filePath);

                if (imageFile.exists() && imageFile.isFile()) {
                    byte[] imageData = Files.readAllBytes(imageFile.toPath());
                    return ResponseEntity.status(HttpStatus.OK)
                            .contentType(MediaType.IMAGE_PNG) // Adjust based on the actual image type
                            .body(imageData);
                } else {
                    // Handle when the file does not exist
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found for the given ID: " + id);
                }
            } else {
                // Handle when no file data is found for the given ID
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No image found for the given ID: " + id);
            }
        } catch (IOException e) {
            // Handle IO exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching image: " + e.getMessage());
        }
    }

    @GetMapping("/{collab_id}/services")
    public ResponseEntity<?> getServicesByCollabId(@PathVariable Long collab_id) {
        List<Services> services = collaboratorService.findServiceByCollabId(collab_id);
        
        if (services.isEmpty()) {
            return ResponseEntity.status(204).body("no services content.");
        }

        return ResponseEntity.ok(services);
    }

    @PostMapping
    public ResponseEntity<String> createCollaborator(@RequestBody Collaborator collaborator) {
        Optional<Collaborator> collabOpt = collaboratorService.findByName(collaborator.getName());

        if (collabOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("this name already exist!");
        }

        collaboratorRepository.save(collaborator);
        return ResponseEntity.ok("Collaborator created");
    }

    @PatchMapping("/{collaborator_id}")
    public ResponseEntity<String> partialUpdateCollaborator(@PathVariable Long collaborator_id,
            @RequestBody CollaboratorDTO collaboratorDTO) {
        Optional<Collaborator> optCollaborator = collaboratorRepository.findById(collaborator_id);
        if (!optCollaborator.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Colloabrator not found");
        }

        Collaborator collaborators = optCollaborator.get();
        serviceMapper.updateCollaboratorFromDto(collaboratorDTO, collaborators);
        collaboratorRepository.save(collaborators);
        return ResponseEntity.ok("Collaborator updated");
    }

    @DeleteMapping("/{collaborator_id}")
    public ResponseEntity<String> deleteCollaborator(@PathVariable Long collaborator_id) {
        if (!collaboratorRepository.existsById(collaborator_id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Collaborator not found");
        }

        collaboratorRepository.deleteById(collaborator_id);
        return ResponseEntity.ok("Collaborator deleted");
    }
}
