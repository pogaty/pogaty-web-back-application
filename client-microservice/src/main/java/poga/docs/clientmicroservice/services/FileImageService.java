package poga.docs.clientmicroservice.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import poga.docs.clientmicroservice.models.FileImage;
import poga.docs.clientmicroservice.repositories.FileImageRepository;

@Service
public class FileImageService {
    @Autowired
    private FileImageRepository fileImageRepository;

    private final String FOLDER_PATH = "..client-microservice/Asset-image/";

    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        // Define a maximum file size (7 MB in bytes)
        long maxFileSize = 3 * 1024 * 1024; // 7 MB in bytes
    
        if (file.getSize() > maxFileSize) {
            // File size exceeds the limit
            throw new IOException("File size too large. Maximum allowed size is 7 MB.");
        }
    
        String filePath = FOLDER_PATH + file.getOriginalFilename();
        File destinationFile = new File(filePath);
    
        FileImage fileData = fileImageRepository.save(FileImage.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filepath(filePath).build());
    
        file.transferTo(destinationFile); // Transfer the uploaded file to the destination file
    
        if (fileData != null) {
            return "File uploaded successfully: " + filePath;
        }
        return null;
    }

    public byte[] downloadImageFromFileSystem(String name) throws IOException {
        Optional<FileImage> fileData = fileImageRepository.findByName(name);
        if (fileData.isPresent()) {
            String filePath = fileData.get().getFilepath();
            return Files.readAllBytes(new File(filePath).toPath());
        } else {
            // Handle the case when no file is found with the given name
            throw new FileNotFoundException("File not found for the given name: " + name);
        }
    }
}
