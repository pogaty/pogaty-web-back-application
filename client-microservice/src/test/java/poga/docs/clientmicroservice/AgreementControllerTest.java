package poga.docs.clientmicroservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import poga.docs.clientmicroservice.controllers.AgreementController;
import poga.docs.clientmicroservice.models.Agreement;
import poga.docs.clientmicroservice.services.AgreementService;

@ExtendWith(MockitoExtension.class)
public class AgreementControllerTest {
    @Mock
    AgreementService agreementService;

    @InjectMocks
    AgreementController agreementController;

     @Test
    void testGetIdeaReactions() {
        // Assuming you have a list of agreements to return for this test
        List<Agreement> mockAgreements = List.of(new Agreement(), new Agreement());

        when(agreementService.findAllReactions()).thenReturn(mockAgreements);

        ResponseEntity<List<Agreement>> response = agreementController.getIdeaReactions();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockAgreements, response.getBody());
    }
    @Test
    void testGetIdeaReactionByClientId() {
         // Client and Idea IDs for the test
         Long clientId = 1L;
         Long ideaId = 2L;
 
         // Mock a response when searching for an agreement by client and idea IDs
         when(agreementService.findByReactionFactor(clientId, ideaId)).thenReturn(Optional.empty());
 
         ResponseEntity<?> response = agreementController.getIdeaReactionByClientId(ideaId, clientId);
 
         assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
         assertEquals("reaction not found.", response.getBody());
    }

    @Test
    void testGetIdeaReactionByIdeaIdAgree() {
        Long ideaId = 3L; // Idea ID for the test

        // Mock an empty list for reaction type 'agree' for the given idea ID
        when(agreementService.findByIdeaIdAgree(ideaId)).thenReturn(List.of());

        ResponseEntity<?> response = agreementController.getIdeaReactionByIdeaIdAgree(ideaId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("no reaction contents.", response.getBody());
    }

    @Test
    void testGetIdeaReactionByIdeaIdDisagree() {
        Long ideaId = 4L; // Idea ID for the test

        // Mock an empty list for reaction type 'disagree' for the given idea ID
        when(agreementService.findByIdeaIdDisagree(ideaId)).thenReturn(List.of());

        ResponseEntity<?> response = agreementController.getIdeaReactionByIdeaIdDisagree(ideaId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("no reaction contents.", response.getBody());
    }
}
