package poga.docs.clientmicroservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.multipart.MultipartFile;

import poga.docs.clientmicroservice.controllers.ClientController;
import poga.docs.clientmicroservice.models.Client;
import poga.docs.clientmicroservice.repositories.ClientRepository;
import poga.docs.clientmicroservice.services.ClientService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
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
    @Sql(scripts = "test-data.sql")
    public void testGetAllClients() {
        List<Client> clients = new ArrayList<>(); // Prepare some mock data
        when(clientService.findAllClients()).thenReturn(clients);

        ResponseEntity<List<Client>> response = clientController.getAllClients();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clients, response.getBody());
    }

    @Test
    @Sql(scripts = "test-data.sql")
    public void testGetRandomClient() {
        List<Client> clients = new ArrayList<>(); // Prepare some mock data
        when(clientService.findRandClients()).thenReturn(clients);

        ResponseEntity<List<Client>> response = clientController.getRandomClient();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clients, response.getBody());
    }

    // Test other methods similarly

    // Example: Test the createClient method
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

    // Example: Test the deleteClient method
    @Test
    public void testDeleteClient() {
        Long clientId = 101L; // Assuming an existing client with this ID

        when(clientRepository.existsById(clientId)).thenReturn(true);

        ResponseEntity<String> response = clientController.deleteClient(clientId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Client deleted", response.getBody());
    }

    // Example: Test the getUsernameStartingWithClients method
    @Test
    public void testGetUsernameStartingWithClients() {
        String username = "testUser"; // Change to match a username for testing

        when(clientService.findByUsernameStartingWithClients(username)).thenReturn(Collections.emptyList());

        ResponseEntity<?> response = clientController.getUsernameStartingWithClients(username);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
    }

    // //Test the updateClientImage method
    // @Test
    // public void testUpdateClientImage() throws IOException {
    //     Long clientId = 101L; // Assuming an existing client with this ID
    //     Path imagePath = Paths.get("client-microservice\\Asset-image\\sample-1.png");

    //     // Simulating file upload
    //     MockMultipartFile file = new MockMultipartFile("file", "sample-1.png", null, Files.readAllBytes(imagePath));

    //     // Mocking repository and service behavior
    //     ClientRepository clientRepository = mock(ClientRepository.class);
    //     ClientService clientService = mock(ClientService.class);

    //     Optional<Client> optionalClient = Optional.of(new Client()); // Create a client or use a mock client
    //     when(clientRepository.findById(clientId)).thenReturn(optionalClient);
    //     when(clientService.uploadImageToFileSystem(file)).thenReturn("image.png");

    //     // Test the updateClientImage method
    //     ResponseEntity<String> response = clientController.updateClientImage(clientId, file);

    //     assertEquals(HttpStatus.OK, response.getStatusCode());
    //     assertEquals("Client image updated", response.getBody());
    // }

    // // Example: Test the downloadImageFromFileSystemById method
    // @Test
    // public void testDownloadImageFromFileSystemById() throws IOException {
    //     Long clientId = 101L; // Assuming an existing client with this ID
    //     File imageFile = new File("path_to_image.png"); // Adjust the path to a valid image file

    //     Optional<Client> clientData = Optional.of(new Client()); // Create a client or use a mock client
    //     clientData.get().setFileImage("image.png"); // Set the file image name

    //     when(clientRepository.findById(clientId)).thenReturn(clientData);
    //     when(Files.readAllBytes(imageFile.toPath())).thenReturn("client-microservice\\Asset-image\\".getBytes());

    //     ResponseEntity<?> response = clientController.downloadImageFromFileSystemById(clientId);
    //     assertEquals(HttpStatus.OK, response.getStatusCode());
    //     assertEquals(MediaType.IMAGE_PNG, response.getHeaders().getContentType());
    //     // Assert other conditions based on your test scenario
    // }

}
