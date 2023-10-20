package poga.docs.clientmicroservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poga.docs.clientmicroservice.models.Client;
import poga.docs.clientmicroservice.repositories.ClientRepository;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAllClients() {
        return this.clientRepository.findAll();
    }

    public List<Client> findByUserNameClients(String username){
        return this.clientRepository.findByUsername(username);
    }

    public List<Client> findByUsernameStartingWithClients(String prefix){
        return this.clientRepository.findByUsernameStartingWith(prefix);
    }
}
