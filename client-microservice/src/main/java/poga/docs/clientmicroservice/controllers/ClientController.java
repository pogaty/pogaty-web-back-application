package poga.docs.clientmicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poga.docs.clientmicroservice.services.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {
    
    private final ClientService clientService;

    @Autowired
    ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllClients() {
        return ResponseEntity.ok(clientService.findAllClients());
    }
}
