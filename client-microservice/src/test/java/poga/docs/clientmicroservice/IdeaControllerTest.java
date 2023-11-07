package poga.docs.clientmicroservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import poga.docs.clientmicroservice.controllers.IdeaController;
import poga.docs.clientmicroservice.models.Idea;
import poga.docs.clientmicroservice.repositories.AgreementRepository;
import poga.docs.clientmicroservice.repositories.IdeaRepository;
import poga.docs.clientmicroservice.repositories.ParticipantRepository;
import poga.docs.clientmicroservice.repositories.ProblemRepository;
import poga.docs.clientmicroservice.services.AgreementService;
import poga.docs.clientmicroservice.services.ClientService;
import poga.docs.clientmicroservice.services.IdeaService;
import poga.docs.clientmicroservice.services.ProblemService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IdeaControllerTest {
    @Mock
    private IdeaService ideaService;

    @Mock
    private IdeaRepository ideaRepository;

    @Mock
    private ServiceMapper serviceMapper;

    @Mock
    private ClientService clientService;

    @Mock
    private AgreementRepository agreementRepository;

    @Mock
    private AgreementService agreementService;

    @Mock
    private ProblemService problemService;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private ProblemRepository problemRepository;

    @InjectMocks
    private IdeaController ideaController;

    @Test
    @Sql("/clientmicroservice:data-test.sql")
    public void testGetAllIdea() {
        // Mock the data - Create a list of Idea objects
        List<Idea> ideaList = new ArrayList<>();

        // Set up the mock behavior for the ideaService to return the ideaList when
        // findAllIdea is called
        when(ideaService.findAllIdea()).thenReturn(ideaList);

        // Call the method to test
        ResponseEntity<List<Idea>> response = ideaController.getAllIdea();

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ideaList, response.getBody());
    }

    @Test
    @Sql("/clientmicroservice:data-test.sql")
    public void testGetById() {
        // Mock the idea ID you want to retrieve
        Long ideaId = 101L;

        // Mock the Idea instance that should be returned by the repository
        Idea mockedIdea = new Idea(); // Assuming a constructed Idea entity

        // Mock the ideaRepository to return the mocked Idea when findById is called
        // with the given ID
        when(ideaRepository.findById(ideaId)).thenReturn(Optional.of(mockedIdea));

        // Call the method in the controller that retrieves the idea by ID
        ResponseEntity<?> response = ideaController.getById(ideaId);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Check if the response status is OK
        assertEquals(mockedIdea, response.getBody()); // Check if the returned idea matches the mocked idea
    }

    // Write similar tests for other methods in the IdeaController
}
