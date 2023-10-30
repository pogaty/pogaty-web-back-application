package poga.docs.clientmicroservice.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public List<Problem> findByCategory(String category) {
        return this.problemRepository.findByCategory(category);
    }

    public Optional<Problem> findByProblemId(Long id) {
        return this.problemRepository.findById(id);
    }

    public List<Problem> findByClientId(Long id) {
        return this.problemRepository.findByMarksClientId(id);
    }

    public Optional<Problem> findByMarksFactor(Long client_id, Long problem_id) {
        return this.problemRepository.findByMarksFactor(client_id, problem_id);
    }

    public String getTimeAgo(LocalDateTime date) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(date, now);

        if (duration.toMinutes() < 1) {
            return "just now";
        } else if (duration.toHours() < 1) {
            long minutes = duration.toMinutes();
            return minutes + (minutes == 1 ? " minute ago" : " minutes ago");
        } else if (duration.toDays() < 1) {
            long hours = duration.toHours();
            return hours + (hours == 1 ? " hour ago" : " hours ago");
        } else {
            long days = duration.toDays();
            return days + (days == 1 ? " day ago" : " days ago");
        }
    }
}
