package poga.docs.partnermicroservice.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import poga.docs.partnermicroservice.models.Collaborator;
import poga.docs.partnermicroservice.repositories.CollaboratorRepository;

@Service
public class CollaboratorService {

    private final CollaboratorRepository collaboratorRepository;

    private final String FOLDER_PATH = "C:\\Users\\CAMT\\Desktop\\deployment\\pogaty-web-back-application\\partner-microservice\\Asset-Image\\";

    @Autowired
    CollaboratorService(CollaboratorRepository collaboratorRepository) {
        this.collaboratorRepository = collaboratorRepository;
    }

    public List<Collaborator> findAllCollaborators() {
        return this.collaboratorRepository.findAll();
    }

    public List<Collaborator> findByNameCollaboratorStartingWith(String prefix) {
        return this.collaboratorRepository.findByNameStartingWith(prefix);
    }

    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        // Define a maximum file size (3 MB in bytes)
        long maxFileSize = 3 * 1024 * 1024; // 3 MB in bytes

        if (file.getSize() > maxFileSize) {
            // File size exceeds the limit
            throw new IOException("File size too large. Maximum allowed size is 3 MB.");
        }

        String filePath = new Date().getTime() + "_" + file.getOriginalFilename();
        File destinationFile = new File(FOLDER_PATH + filePath);

        try {
            file.transferTo(destinationFile); // Transfer the uploaded file to the destination file
            return filePath;
        } catch (IOException e) {
            // Handle file transfer exceptions
            // You might want to delete the Client entry if the file transfer fails
            if (destinationFile.exists()) {
                destinationFile.delete(); // Delete the file if transfer failed
            }
            throw new IOException("Failed to save the file to the file system.", e);
        }
    }

    public byte[] downloadImageFromFileSystem(String fileImage) throws IOException {
        Optional<Collaborator> fileData = collaboratorRepository.findByfileImage(fileImage);
        if (fileData.isPresent()) {
            String filePath = fileData.get().getFileImage();
            File imageFile = new File(FOLDER_PATH + filePath);

            if (imageFile.exists() && imageFile.isFile()) {
                return Files.readAllBytes(imageFile.toPath());
            } else {
                // Handle the case when the file does not exist
                throw new FileNotFoundException("File not found for the given name: " + fileImage);
            }
        } else {
            // Handle the case when no file data is found in the repository
            throw new FileNotFoundException("No file data found for the given name: " + fileImage);
        }
    }
}
