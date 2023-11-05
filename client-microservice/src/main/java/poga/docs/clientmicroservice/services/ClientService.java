package poga.docs.clientmicroservice.services;

import java.util.List;
import java.util.Optional;

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

    public Client findByUserNameClients(String username){
        return this.clientRepository.findByUsername(username);
    }

    public List<Client> findByUsernameStartingWithClients(String prefix){
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
}
