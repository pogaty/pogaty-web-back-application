package poga.docs.clientmicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poga.docs.clientmicroservice.ServiceMapper;
import poga.docs.clientmicroservice.models.Client;
import poga.docs.clientmicroservice.models.ClientDTO;
import poga.docs.clientmicroservice.repositories.ClientRepository;
import poga.docs.clientmicroservice.services.ClientService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;
    private final ClientRepository clientRepository;
    private final ServiceMapper serviceMapper;

    @Autowired
    public ClientController(ClientService clientService, ClientRepository clientRepository, ServiceMapper serviceMapper) {
        this.clientService = clientService;
        this.clientRepository = clientRepository;
        this.serviceMapper = serviceMapper;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.findAllClients();
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

    @GetMapping("/searchStarting/{username}")
    public ResponseEntity<?> getUsernameStartingWithClients(@PathVariable String username) {
        if (username.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client Not Found");
        }

        List<Client> clients = clientService.findByUsernameStartingWithClients(username);
        return ResponseEntity.ok(clients);
    }

    @PostMapping
    public ResponseEntity<String> createClient(@RequestBody Client client) {
        clientRepository.save(client);
        return ResponseEntity.ok("Client created");
    }

    @PutMapping("/{client_id}")
    public ResponseEntity<String> updateClient(@PathVariable Long client_id, @RequestBody Client client) {
        if (!clientRepository.existsById(client_id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
        
        client.setClient_id(client_id); // Ensure the client_id is set
        clientRepository.save(client);
        return ResponseEntity.ok("Client updated");
    }

    @DeleteMapping("/{client_id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long client_id) {
        if (!clientRepository.existsById(client_id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }

        clientRepository.deleteById(client_id);
        return ResponseEntity.ok("Client deleted");
    }

    @PatchMapping("/{client_id}")
    public ResponseEntity<String> partialUpdateClient(@PathVariable Long client_id, @RequestBody ClientDTO clientDTO) {
        Optional<Client> optClient = clientRepository.findById(client_id);
        if (!optClient.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }

        Client client = optClient.get();
        serviceMapper.updateClientFromDto(clientDTO, client);
        clientRepository.save(client);
        return ResponseEntity.ok("Client updated");
    }
}
