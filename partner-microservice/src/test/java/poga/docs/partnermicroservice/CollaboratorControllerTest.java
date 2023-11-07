package poga.docs.partnermicroservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import poga.docs.partnermicroservice.controllers.CollaboratorController;
import poga.docs.partnermicroservice.models.Collaborator;
import poga.docs.partnermicroservice.models.CollaboratorDTO;
import poga.docs.partnermicroservice.models.Services;
import poga.docs.partnermicroservice.repositories.CollaboratorRepository;
import poga.docs.partnermicroservice.services.CollaboratorService;

@ExtendWith(MockitoExtension.class)
public class CollaboratorControllerTest {

    @Mock
    CollaboratorService collaboratorService;

    @Mock
    ServiceMapper serviceMapper;

    @Mock
    CollaboratorRepository collaboratorRepository;

    @InjectMocks
    CollaboratorController collaboratorController;

    @Test
    void testGetAllCollaborators() {
        // Assuming you have a list of collaborators to return for this test
        List<Collaborator> mockCollaborators = List.of(new Collaborator(), new Collaborator());

        when(collaboratorService.findAllCollaborators()).thenReturn(mockCollaborators);

        ResponseEntity<?> response = collaboratorController.getAllCollaborators();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetCollaboratorById() {
        long existingCollaboratorId = 1L;

        // Assuming you have an existing collaborator with this ID in your database
        Collaborator existingCollaborator = new Collaborator();
        existingCollaborator.setCollab_id(existingCollaboratorId);

        when(collaboratorRepository.findById(existingCollaboratorId)).thenReturn(Optional.of(existingCollaborator));

        ResponseEntity<?> response = collaboratorController.getColloabratorByCollaborator_id(existingCollaboratorId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetNameCollaborator() {
        String existingCollaboratorName = "John Doe"; 

        // Assuming you have an existing collaborator with this name in your database
        Collaborator existingCollaborator = new Collaborator();
        existingCollaborator.setName(existingCollaboratorName);

        when(collaboratorService.findByName(existingCollaboratorName)).thenReturn(Optional.of(existingCollaborator));

        ResponseEntity<?> response = collaboratorController.getNameColloabrator(existingCollaboratorName);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdateCollaboratorImage() throws IOException {
        Long existingCollaboratorId = 1L; 
        MultipartFile mockFile = mock(MultipartFile.class); // Mock MultipartFile object

        // Mocking the Collaborator and file update
        Collaborator existingCollaborator = new Collaborator();
        when(collaboratorRepository.findById(existingCollaboratorId)).thenReturn(Optional.of(existingCollaborator));
        when(collaboratorService.uploadImageToFileSystem(mockFile)).thenReturn("updated_image.jpg");

        ResponseEntity<String> response = collaboratorController.updateCollaboratorImage(existingCollaboratorId,
                mockFile);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetServicesByCollabId() {
        Long existingCollaboratorId = 1L; 

        Services service = new Services();
        service.setService_id(1L);
        service.setName("Service Name");
        service.setDescription("Service Description");
        service.setCategory("Service Category");
        service.setServiceType("Type of Service");
        List<Services> services = Collections.singletonList(service);

        when(collaboratorService.findServiceByCollabId(existingCollaboratorId)).thenReturn(services);

        ResponseEntity<?> response = collaboratorController.getServicesByCollabId(existingCollaboratorId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDeleteCollaborator() {
        Long existingCollaboratorId = 1L;

        when(collaboratorRepository.existsById(existingCollaboratorId)).thenReturn(true);

        ResponseEntity<String> response = collaboratorController.deleteCollaborator(existingCollaboratorId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
