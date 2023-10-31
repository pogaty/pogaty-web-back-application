package poga.docs.clientmicroservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import poga.docs.clientmicroservice.models.Trend;
import poga.docs.clientmicroservice.repositories.TrendRepository;

@Service
public class TrendService {
    private final TrendRepository trendRepository;

    public TrendService(TrendRepository trendRepository) {
        this.trendRepository = trendRepository;
    }

    public List<Trend> findAllReactions() {
        return trendRepository.findAll();
    }

    public Optional<Trend> findByReactionFactor(Long client_id, Long problem_id) {
        return this.trendRepository.findByClientIdAndProblemId(client_id, problem_id);
    }

    public Integer[] findOnTrend(Long problem_id) {
        int trendUp = this.trendRepository.findCountByProblemIdTrendUp(problem_id);
        int trendDown = this.trendRepository.findCountByProblemIdTrendDown(problem_id);
        int trendCount = trendUp - trendDown;
        if (trendUp > trendDown) {
            return new Integer[]{trendCount, 2};
        } else if (trendUp == trendDown) {
            return new Integer[]{trendCount, 1};
        } else {
            return new Integer[]{trendCount, 0};
        }
        
    }
}
