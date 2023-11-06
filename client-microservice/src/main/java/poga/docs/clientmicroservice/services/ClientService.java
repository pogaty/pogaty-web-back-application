package poga.docs.clientmicroservice.services;

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

import poga.docs.clientmicroservice.models.Client;
import poga.docs.clientmicroservice.repositories.ClientRepository;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final String FOLDER_PATH = System.getProperty( "user.dir" ).concat("\\client-microservice\\Asset-image\\");

    @Autowired
    ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAllClients() {
        return this.clientRepository.findAll();
    }

    public Client findByUserNameClients(String username) {
        return this.clientRepository.findByUsername(username);
    }

    public List<Client> findByUsernameStartingWithClients(String prefix) {
        return this.clientRepository.findByUsernameStartingWith(prefix);
    }

    public Optional<Client> findByClientId(Long id) {
        return this.clientRepository.findById(id);
    }

    public List<Client> findRandClients() {
        List<Client> clients = this.clientRepository.findRandomClients();
        int limit = Math.min(5, clients.size());
        return clients.subList(0, limit);
    }

    public List<Client> findWithoutParticipantAndSelf(Long idea_id, Long client_id) {
        return this.clientRepository.findWithoutParticipantAndSelf(idea_id, client_id);
    }

    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        // Define a maximum file size (3 MB in bytes)
        long maxFileSize = 3 * 1024 * 1024; // 3 MB in bytes
    
        if (file.getSize() > maxFileSize) {
            // File size exceeds the limit
            throw new IOException("File size too large. Maximum allowed size is 3 MB.");
        }
    
        String filePath = new Date().getTime() + "_" + file.getOriginalFilename();
        File destinationFile = new File(FOLDER_PATH + File.separator + filePath);
    
        try {
            file.transferTo(destinationFile); // Transfer the uploaded file to the destination file
            return filePath;
        } catch (IOException e) {
            // Handle file transfer exceptions
            // You might want to delete the file from the file system if the transfer fails
            if (destinationFile.exists()) {
                destinationFile.delete(); // Delete the file if transfer failed
            }
            throw new IOException("Failed to save the file to the file system.", e);
        }
    }

    public byte[] downloadImageFromFileSystem(String fileImage) throws IOException {
        Optional<Client> fileData = clientRepository.findByfileImage(fileImage);
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
