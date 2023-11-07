package poga.docs.clientmicroservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import poga.docs.clientmicroservice.controllers.ProblemController;
import poga.docs.clientmicroservice.models.Client;
import poga.docs.clientmicroservice.models.Idea;
import poga.docs.clientmicroservice.models.Participant;
import poga.docs.clientmicroservice.models.Problem;
import poga.docs.clientmicroservice.models.ProblemDTO;
import poga.docs.clientmicroservice.repositories.ClientRepository;
import poga.docs.clientmicroservice.repositories.ParticipantRepository;
import poga.docs.clientmicroservice.repositories.ProblemRepository;
import poga.docs.clientmicroservice.repositories.TrendRepository;
import poga.docs.clientmicroservice.services.ClientService;
import poga.docs.clientmicroservice.services.ProblemService;
import poga.docs.clientmicroservice.services.TrendService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProblemControllerTest {

    @Mock
    private ProblemService problemService;

    @Mock
    private ProblemRepository problemRepository;

    @Mock
    private ClientService clientService;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private ServiceMapper serviceMapper;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private TrendService trendService;

    @Mock
    private TrendRepository trendRepository;

    @InjectMocks
    private ProblemController problemController;

    @Test
    void testGetAllProblem() {
        // Create a list of Problem objects for testing purposes
        List<Problem> problemList = new ArrayList<>();

        // Mock the behavior to return the problemList when findAllProblem is called
        when(problemService.findAllProblem()).thenReturn(problemList);

        // Execute the method under test
        ResponseEntity<List<Problem>> response = problemController.getAllProblem();

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(problemList, response.getBody());
    }

    @Test
    void testGetProblemsByCategory() {
        // Your category for testing purposes
        String category = "YourCategory";

        // Create a list of Problem objects for testing purposes
        List<Problem> problemsInCategory = new ArrayList<>();

        // Mock the behavior to return problemsInCategory when findByCategory is called
        when(problemService.findByCategory(category)).thenReturn(problemsInCategory);

        // Execute the method under test
        ResponseEntity<?> response = problemController.getProblemsByCategory(category);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(problemsInCategory, response.getBody());
    }

    @Test
    void testGetTopicByProblem() {
        // Your category for testing purposes
        String category = "YourCategory";

        // Create a list of Problem objects for testing purposes
        List<Problem> topicsInCategory = new ArrayList<>();

        // Mock the behavior to return topicsInCategory when findByCategoryProblem is
        // called
        when(problemService.findByCategoryProblem(category)).thenReturn(topicsInCategory);

        // Execute the method under test
        ResponseEntity<?> response = problemController.getTopicByProblem(category);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(topicsInCategory, response.getBody());
    }

    @Test
    void testCreateProblem() {
        // Mock a client ID for testing purposes
        Long clientId = 1L;

        // Mock a Problem object for testing purposes
        Problem mockProblem = new Problem(); // Create a problem object here with necessary details

        // Mock the behavior to return a client when findByClientId is called
        when(clientService.findByClientId(clientId)).thenReturn(Optional.of(new Client())); // Assuming the client is

        // Mock the behavior to return a client when save is called on clientRepository
        when(clientRepository.save(Mockito.any(Client.class))).thenReturn(new Client());

        // Mock the behavior to return the saved problem when save is called on
        // problemRepository
        when(problemRepository.save(Mockito.any(Problem.class))).thenReturn(mockProblem);

        // Execute the method under test
        ResponseEntity<String> response = problemController.createProblem(clientId, mockProblem);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Problem created", response.getBody());
    }

    @Test
    void testDeleteProblem() {
        // Mock a problem ID for testing purposes
        Long problemId = 1L;

        // Mock the behavior of problemRepository.existsById() for the problem ID
        when(problemRepository.existsById(problemId)).thenReturn(true); // Assuming the problem exists

        // Execute the method under test
        ResponseEntity<String> response = problemController.deleteProblem(problemId);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Problem deleted", response.getBody());
    }

    @Test
    void testCreateIdeaOnProblem() {
        // Mock input data
        Long problemId = 1L;
        Long clientId = 1L;
        Idea idea = new Idea(); // Create an Idea object for testing

        // Create a Problem object and ensure its ideas list is initialized
        Problem mockProblem = new Problem();
        mockProblem.setIdeas(new ArrayList<>()); // Initialize the ideas list

        // Mock behaviors for finding problem and client
        when(problemService.findByProblemId(problemId)).thenReturn(Optional.of(mockProblem));
        when(clientService.findByClientId(clientId)).thenReturn(Optional.of(new Client()));

        // Mock behavior for saving participant
        when(participantRepository.save(any(Participant.class))).thenReturn(new Participant());

        // Execute the method under test
        ResponseEntity<String> response = problemController.createIdeaOnProblem(problemId, clientId, idea);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("new idea has created", response.getBody());
    }

    @Test
    void testPartialUpdateProblem() {
        // Mock input data
        Long problemId = 1L;
        ProblemDTO problemDTO = new ProblemDTO(); // Create a ProblemDTO object for testing

        // Mock behavior for finding problem by ID
        when(problemRepository.findById(problemId)).thenReturn(Optional.of(new Problem()));

        // Execute the method under test
        ResponseEntity<String> response = problemController.partialUpdateProblem(problemId, problemDTO);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Problem updated", response.getBody());
    }

    

    @Test
    void testGetTopicStartingWithByProblem() {
        // Mock input data
        String topic = "TestTopic";

        // Mock behavior for finding topics starting with a given topic
        List<Problem> foundTopics = new ArrayList<>();
        when(problemService.findByTopicProblemStartingWith(topic)).thenReturn(new ArrayList<>());

        // Execute the method under test
        ResponseEntity<?> response = problemController.getTopicStartingWithByProblem(topic);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(foundTopics, response.getBody());
    }

    @Test
    void testMarkProblemsForClient() {
        // Mock input data
    Long problemId = 1L;
    Long clientId = 1L;

    // Create a Problem and initialize the marks list
    Problem mockProblem = new Problem();
    mockProblem.setMarks(new ArrayList<>()); // Initialize the marks list

    // Create a Client
    Client mockClient = new Client();

    // Mock behavior for finding problem and client
    when(problemService.findByProblemId(problemId)).thenReturn(Optional.of(mockProblem));
    when(clientService.findByClientId(clientId)).thenReturn(Optional.of(mockClient));

    // Execute the method under test
    ResponseEntity<?> response = problemController.markProblemsForClient(problemId, clientId);

    // Assertions
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("marked success.", response.getBody());
    }
}
