package poga.docs.clientmicroservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poga.docs.clientmicroservice.models.Problem;
import poga.docs.clientmicroservice.repositories.ProblemRepository;

@Service
public class ProblemService {
    
    private final ProblemRepository problemRepository;

    @Autowired
    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public List<Problem> findAllProblem() {
        return this.problemRepository.findAll();
    }

    public List<Problem> findByCategoryProblem(String category) {
        return this.problemRepository.findByCategory(category);
    }

    public List<Problem> findByTopicProblemStartingWith(String prefix) {
        return this.problemRepository.findByTopicStartingWith(prefix);
    }
}
