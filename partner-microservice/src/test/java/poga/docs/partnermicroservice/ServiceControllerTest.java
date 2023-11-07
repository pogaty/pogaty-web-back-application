package poga.docs.partnermicroservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import poga.docs.partnermicroservice.controllers.ServiceController;
import poga.docs.partnermicroservice.models.Collaborator;
import poga.docs.partnermicroservice.models.ServiceDTO;
import poga.docs.partnermicroservice.models.Services;
import poga.docs.partnermicroservice.repositories.CollaboratorRepository;
import poga.docs.partnermicroservice.repositories.ServiceRepository;
import poga.docs.partnermicroservice.services.CollaboratorService;
import poga.docs.partnermicroservice.services.ServiceService;

@ExtendWith(MockitoExtension.class)
public class ServiceControllerTest {

    @InjectMocks
    private ServiceController serviceController;

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private ServiceService serviceService;

    @Mock
    private ServiceMapper serviceMapper;

    @Mock
    private CollaboratorService collaboratorService;

    @Mock
    private CollaboratorRepository collaboratorRepository;

    @Test
    public void testGetAllPage() {
        // Prepare data
        List<Services> servicesList = new ArrayList<>(); // Add some sample services
        Mockito.when(serviceRepository.findAll()).thenReturn(servicesList);

        ResponseEntity<?> response = serviceController.getAllPage();

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(servicesList, response.getBody());
    }

    @Test
    public void testGetServiceById() {
        long serviceId = 1; 

        Services service = new Services(); 
        Optional<Services> serviceOptional = Optional.of(service);

        Mockito.when(serviceRepository.findById(serviceId)).thenReturn(serviceOptional);

        ResponseEntity<?> response = serviceController.getAllServiceByservice_id(serviceId);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(service, response.getBody());
    }

    @Test
    public void testGetServiceById_NotFound() {
        long serviceId = 10; // An ID that might not exist

        Optional<Services> serviceOptional = Optional.empty();
        Mockito.when(serviceRepository.findById(serviceId)).thenReturn(serviceOptional);

        ResponseEntity<?> response = serviceController.getAllServiceByservice_id(serviceId);

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Service Not Found", response.getBody());
    }

    @Test
    public void testPartialUpdateService() {
        Long serviceId = 1L; 
        ServiceDTO serviceDTO = new ServiceDTO();

        Services service = new Services();
        Optional<Services> serviceOptional = Optional.of(service);

        Mockito.when(serviceRepository.findById(serviceId)).thenReturn(serviceOptional);
        Mockito.when(serviceRepository.save(service)).thenReturn(service);

        ResponseEntity<String> response = serviceController.partialUpdateService(serviceId, serviceDTO);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Service updated", response.getBody());
    }

    @Test
    public void testDeleteService() {
        Long serviceId = 1L;

        Mockito.when(serviceRepository.existsById(serviceId)).thenReturn(true);

        ResponseEntity<String> response = serviceController.deleteService(serviceId);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Service deleted", response.getBody());
    }

    @Test
    public void testPartialUpdateService_ServiceNotFound() {
        Long serviceId = 1L; 
        ServiceDTO serviceDTO = new ServiceDTO(); 

        Mockito.when(serviceRepository.findById(serviceId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = serviceController.partialUpdateService(serviceId, serviceDTO);

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Service not found", response.getBody());
    }

    @Test
    public void testCreateService_CollaboratorNotFound() {
        Long collabId = 1L;
        Services service = new Services(); 

        Mockito.when(collaboratorService.findById(collabId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = serviceController.createService(collabId, service);

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("collab not found.", response.getBody());
    }

    @Test
    public void testDeleteService_ServiceNotFound() {
        Long serviceId = 1L; 

        Mockito.when(serviceRepository.existsById(serviceId)).thenReturn(false);

        ResponseEntity<String> response = serviceController.deleteService(serviceId);

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Service not found", response.getBody());
    }

}

