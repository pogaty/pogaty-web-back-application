package poga.docs.clientmicroservice.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poga.docs.clientmicroservice.ServiceMapper;
import poga.docs.clientmicroservice.models.Problem;
import poga.docs.clientmicroservice.models.ProblemDTO;
import poga.docs.clientmicroservice.repositories.ProblemRepository;
import poga.docs.clientmicroservice.services.ProblemService;

@RestController
@RequestMapping("/problems")
public class ProblemController {
    private final ProblemService problemService;
    private final ProblemRepository problemRepository;
    private final ServiceMapper serviceMapper;

    
    @Autowired
    public ProblemController(ProblemService problemService, ProblemRepository problemRepository,
            ServiceMapper serviceMapper) {
        this.problemService = problemService;
        this.problemRepository = problemRepository;
        this.serviceMapper = serviceMapper;
    }

    @GetMapping
    public ResponseEntity<List<Problem>> getAllProblem() {
        List<Problem> problem = problemService.findAllProblem();
        return ResponseEntity.ok(problem);
    }

    @GetMapping("/search/{topic}")
    public ResponseEntity<?> getTopicByProblem(@PathVariable String topic) {
        if (topic.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Problem Not Found");
        }

        List<Problem> topics = problemService.findByTopicProblem(topic);
        return ResponseEntity.ok(topics);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getProblemsByCategory(@PathVariable String category) {
        List<Problem> problems = problemService.findByCategory(category);

        if (problems.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("problem in category not found.");
        }
        return ResponseEntity.ok(problems);
    }

    @GetMapping("/searchStarting/{topic}")
    public ResponseEntity<?> getTopicStartingWithByProblem(@PathVariable String topic) {
        if (topic.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Problem Not Found");
        }

        List<Problem> topics = problemService.findByTopicProblemStartingWith(topic);
        return ResponseEntity.ok(topics);
    }

    @GetMapping("/timed/{problem_id}")
    public ResponseEntity<String> getTimeAgoByTopicis(@PathVariable long problem_id) {
        Optional<Problem> problemOpt = problemService.findByProblemId(problem_id);

        if (!problemOpt.isPresent()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("problem topic doesn't found.");
        }

        Problem problem = problemOpt.get();
        String time = problemService.getTimeAgo(problem.getDate());
        return ResponseEntity.ok(time);
    }

    @PostMapping()
    public ResponseEntity<String> createProblem(@RequestBody Problem problem) {
        problemRepository.save(problem);
        return ResponseEntity.ok("Problem created");
    }

    @PutMapping("/{problem_id}")
    public ResponseEntity<String> updateProblem(@PathVariable Long problem_id, @RequestBody Problem problem) {
        if (!problemRepository.existsById(problem_id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Problem not found");
        }
        
        problem.setProblem_id(problem_id); // Ensure the problem_id is set
        problemRepository.save(problem);
        return ResponseEntity.ok("Problem updated");
    }

    @DeleteMapping("/{problem_id}")
    public ResponseEntity<String> deleteProblem(@PathVariable Long problem_id) {
        if (!problemRepository.existsById(problem_id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Problem not found");
        }

        problemRepository.deleteById(problem_id);
        return ResponseEntity.ok("Problem deleted");
    }

    @PatchMapping("/{problem_id}")
    public ResponseEntity<String> partialUpdateProblem(@PathVariable Long problem_id, @RequestBody ProblemDTO problemDTO) {
        Optional<Problem> optProblem = problemRepository.findById(problem_id);
        if (!optProblem.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Problem not found");
        }

        Problem problem = optProblem.get();
        serviceMapper.updateProblemFromDto(problemDTO, problem);
        problemRepository.save(problem);
        return ResponseEntity.ok("Problem updated");
    }
}
