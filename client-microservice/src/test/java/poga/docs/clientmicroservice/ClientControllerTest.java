package poga.docs.clientmicroservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import poga.docs.clientmicroservice.controllers.ClientController;
import poga.docs.clientmicroservice.models.Client;
import poga.docs.clientmicroservice.repositories.ClientRepository;
import poga.docs.clientmicroservice.services.ClientService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientController clientController;

    @Test
    public void testGetAllClients() {
        List<Client> clients = new ArrayList<>(); // Prepare some mock data
        when(clientService.findAllClients()).thenReturn(clients);

        ResponseEntity<List<Client>> response = clientController.getAllClients();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clients, response.getBody());
    }

    @Test
    public void testGetRandomClient() {
        List<Client> clients = new ArrayList<>(); // Prepare some mock data
        when(clientService.findRandClients()).thenReturn(clients);

        ResponseEntity<List<Client>> response = clientController.getRandomClient();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clients, response.getBody());
    }

    @Test
    public void testCreateClient() {
        Client client = new Client();
        // Set properties for the client
        client.setUsername("sampleUsername"); // Set a non-null username

        when(clientRepository.existsByLowercaseUsername(client.getUsername().toLowerCase())).thenReturn(false);
        when(clientRepository.save(client)).thenReturn(client);

        ResponseEntity<String> response = clientController.createClient(client);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Client created", response.getBody());
    }

    @Test
    public void testUpdateClient() {
        Long clientId = 101L; // Assuming an existing client with this ID
        Client client = new Client();
        // Set properties for the client
        client.setClient_id(clientId);

        when(clientRepository.existsById(clientId)).thenReturn(true);
        when(clientRepository.save(client)).thenReturn(client);

        ResponseEntity<String> response = clientController.updateClient(clientId, client);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Client updated", response.getBody());
    }

    @Test
    public void testDeleteClient() {
        Long clientId = 101L; // Assuming an existing client with this ID

        when(clientRepository.existsById(clientId)).thenReturn(true);

        ResponseEntity<String> response = clientController.deleteClient(clientId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Client deleted", response.getBody());
    }


    @Test
    public void testGetUsernameStartingWithClients() {
        String username = "testUser"; // Change to match a username for testing

        when(clientService.findByUsernameStartingWithClients(username)).thenReturn(Collections.emptyList());

        ResponseEntity<?> response = clientController.getUsernameStartingWithClients(username);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
    }


}
