package poga.docs.clientmicroservice.controllers;

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

import poga.docs.clientmicroservice.ServiceMapper;
import poga.docs.clientmicroservice.models.Client;
import poga.docs.clientmicroservice.models.ClientDTO;
import poga.docs.clientmicroservice.repositories.ClientRepository;
import poga.docs.clientmicroservice.services.ClientService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;
    private final ClientRepository clientRepository;
    private final ServiceMapper serviceMapper;
    private final String FOLDER_PATH = "Asset-image/";
  

    @Autowired
    public ClientController(ClientService clientService, ClientRepository clientRepository,
            ServiceMapper serviceMapper) {
        this.clientService = clientService;
        this.clientRepository = clientRepository;
        this.serviceMapper = serviceMapper;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.findAllClients();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/rand")
    public ResponseEntity<List<Client>> getRandomClient() {
        List<Client> clients = clientService.findRandClients();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUsernameClients(@PathVariable String username) {
        Client clients = clientService.findByUserNameClients(username);
        if (clients == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client Not Found");
        }

        return ResponseEntity.ok(clients);
    }

    @GetMapping("/without/{client_id}/on_idea/{idea_id}")
    public ResponseEntity<?> getWithoutSelf(@PathVariable Long client_id, @PathVariable Long idea_id) {
        List<Client> clients = clientService.findWithoutParticipantAndSelf(idea_id, client_id);

        if (clients.isEmpty()) {
            return ResponseEntity.status(204).body("no content of client or idea.");
        }
        return ResponseEntity.ok(clients);
    }

    @PutMapping("/{id}/image")
    public ResponseEntity<String> updateClientImage(@PathVariable Long id,@RequestParam ("file") MultipartFile file) {
        Optional<Client> optionalClient = clientRepository.findById(id);

        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();

            try {
                // Update the image only
                String updatedImage = clientService.uploadImageToFileSystem(file);
                client.setFileImage(updatedImage);
                clientRepository.save(client);
                return ResponseEntity.ok("Client image updated");
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update client image");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
    }

    @GetMapping("/image/{fileImage}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileImage) {
        try {
            byte[] imageData = clientService.downloadImageFromFileSystem(fileImage);
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
        Optional<Client> clientData = clientRepository.findById(id);
        
        if (clientData.isPresent()) {
            String filePath = clientData.get().getFileImage();
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
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching image: " + e.getMessage());
    }
}


    // for search bar to find Client by username
    @GetMapping("/search/{username}")
    public ResponseEntity<?> getUsernameStartingWithClients(@PathVariable String username) {
        if (username.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client Not Found");
        }

        List<Client> clients = clientService.findByUsernameStartingWithClients(username);
        return ResponseEntity.ok(clients);
    }

    // Create Client and chacek username isn't exsits
    @PostMapping
    public ResponseEntity<String> createClient(@RequestBody Client client) {
        String lowercaseUsername = client.getUsername().toLowerCase();

        // Check if a client with the same lowercase username exists
        if (clientRepository.existsByLowercaseUsername(lowercaseUsername)) {
            // A client with the same lowercase username already exists
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        } else {
            // Save the client
            client.setFileImage("sample-default.png");
            clientRepository.save(client);
            return ResponseEntity.ok("Client created");
        }
    }


    // Update client by specific parameter
    @PatchMapping("/{id}")
    public ResponseEntity<String> partialUpdateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        Optional<Client> optClient = clientRepository.findById(id);
        if (!optClient.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }

        Client client = optClient.get();
        serviceMapper.updateClientFromDto(clientDTO, client);
        clientRepository.save(client);
        return ResponseEntity.ok("Client updated");
    }

     //Update client by handle
     @PutMapping("/{client_id}")
     public ResponseEntity<String> updateClient(@PathVariable Long client_id, @RequestBody Client client) {
         if (!clientRepository.existsById(client_id)) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
         }
         
         client.setClient_id(client_id); // Ensure the client_id is set
         clientRepository.save(client);
         return ResponseEntity.ok("Client updated");
     }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        if (!clientRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }

        clientRepository.deleteById(id);
        return ResponseEntity.ok("Client deleted");
    }

}
