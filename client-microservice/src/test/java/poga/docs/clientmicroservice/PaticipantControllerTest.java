package poga.docs.clientmicroservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import poga.docs.clientmicroservice.controllers.ParticipantController;
import poga.docs.clientmicroservice.models.Client;
import poga.docs.clientmicroservice.models.Idea;
import poga.docs.clientmicroservice.models.Participant;
import poga.docs.clientmicroservice.repositories.IdeaRepository;
import poga.docs.clientmicroservice.repositories.ParticipantRepository;
import poga.docs.clientmicroservice.services.ClientService;
import poga.docs.clientmicroservice.services.IdeaService;
import poga.docs.clientmicroservice.services.ParticipantService;

@ExtendWith(MockitoExtension.class)
public class PaticipantControllerTest {
    @Mock
    private ParticipantService participantService;

    @Mock
    private IdeaService ideaService;

    @Mock
    private ClientService clientService;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private IdeaRepository ideaRepository;

    @InjectMocks
    private ParticipantController participantController;

    @Test
    void testGetAllParticipant() {
        // Mock behavior for retrieving all participants
        List<Participant> participants = new ArrayList<>(); // Simulated list of participants
        when(participantService.findAllPaticipant()).thenReturn(participants);

        // Execute the method under test
        ResponseEntity<List<Participant>> response = participantController.getAllParticipant();

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(participants, response.getBody());
    }

    @Test
    void testGetRole() {
        // Mock input data
        String role = "SomeRole";

        // Create a list of participants for testing purposes
        List<Participant> participants = new ArrayList<>(); // Simulated list of participants

        // Mock behavior for finding participants by role
        when(participantService.findByRolePaticipant(role)).thenReturn(participants);

        // Execute the method under test
        ResponseEntity<?> response = participantController.getRole(role);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(participants, response.getBody());
    }

    @Test
    void testGetByClientIdAndIdeaId() {
        // Mock input data
        Long clientId = 1L;
        Long ideaId = 1L;

        // Create a participant for testing purposes
        Participant participant = new Participant(); // Simulated participant

        // Mock behavior for finding participant by client and idea IDs
        when(participantService.findByClientIdAndIdeaId(clientId, ideaId)).thenReturn(Optional.of(participant));

        // Execute the method under test
        ResponseEntity<?> response = participantController.getByClientIdAndIdeaId(clientId, ideaId);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(participant, response.getBody());
    }

    @Test
    void testCreateParticipant() {
         // Mock input data
         Long ideaId = 1L;
         Long clientId = 2L;
         Participant participant = new Participant();
         participant.setRole("SomeRole"); // Set role for participant
 
         Idea idea = new Idea();
         Client client = new Client();
 
         // Mock behavior for finding idea and client by their IDs
         when(ideaService.findByIdeaId(ideaId)).thenReturn(Optional.of(idea));
         when(clientService.findByClientId(clientId)).thenReturn(Optional.of(client));
 
         // Simulate the existing participant
         when(participantService.findByClientIdAndIdeaId(clientId, ideaId)).thenReturn(Optional.of(participant));
 
         // Execute the method under test
         ResponseEntity<String> response = participantController.createParticipant(ideaId, clientId, participant);
 
         // Assertions
         assertEquals(HttpStatus.OK, response.getStatusCode());
         assertEquals("Participant created", response.getBody());
    }

    @Test
    void testDeleteParticipant() {
        // Mock input data
        Long ideaId = 1L;
        Long clientId = 2L;

        // Mock behavior for finding idea and client by their IDs
        when(ideaService.findByIdeaId(ideaId)).thenReturn(Optional.of(new Idea()));
        when(clientService.findByClientId(clientId)).thenReturn(Optional.of(new Client()));

        // Simulate the existing participant
        when(participantService.findByClientIdAndIdeaId(clientId, ideaId)).thenReturn(Optional.of(new Participant()));

        // Execute the method under test
        ResponseEntity<String> response = participantController.deleteParticipant(ideaId, clientId);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("remove participant successfully", response.getBody());
    }
}
