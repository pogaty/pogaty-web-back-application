package poga.docs.clientmicroservice.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import poga.docs.clientmicroservice.models.Client;
import poga.docs.clientmicroservice.repositories.ClientRepository;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    private final String PICTURE_PATH="";

    @Autowired
    ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAllClients() {
        return this.clientRepository.findAll();
    }

    public Client findByUserNameClients(String username){
        return this.clientRepository.findByUsername(username);
    }

    public List<Client> findByUsernameStartingWithClients(String prefix){
        return this.clientRepository.findByUsernameStartingWith(prefix);
    }

    public Optional<Client> findByClientId(Long id) {
        return this.clientRepository.findById(id);
    }

    public String UploadImageToFileSystem(MultipartFile file)throws IOException{
        String filePath = PICTURE_PATH+file.getOriginalFilename();

        Client client = clientRepository.save(Client.builder()
            .fileImage(filePath)
            .build());

        file.transferTo(new File(filePath));

        if(client != null){
            return filePath;
        }
        return null;
    }

    public byte[] DownloadImageToFileSystem(String fileName)throws IOException{
        Optional<Client> fileData = clientRepository.findByfileImage(fileName);
        String filePath= fileData.get().getFileImage();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }
}
