package poga.docs.clientmicroservice.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poga.docs.clientmicroservice.models.Trend;
import poga.docs.clientmicroservice.services.TrendService;

@RestController
@RequestMapping("/trends")
public class TrendController {
    private final TrendService trendService;

    public TrendController(TrendService trendService) {
        this.trendService = trendService;
    }

    @GetMapping()
    public ResponseEntity<List<Trend>> getTrendReactions() {
        List<Trend> trends = trendService.findAllReactions();
        return ResponseEntity.ok(trends);
    }

    @GetMapping("/on_rate") 
    public ResponseEntity<?> getTrendRateByProblem() {
        List<Trend> trends = trendService.findAllReactions();

        if (trends.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("trend doesn't found");
        }

        HashMap<Long, Integer[]> eachTrend = new HashMap<Long, Integer[]>();
        for (Trend trend : trends) {
            Long problem_id = trend.getProblem().getProblem_id();
            Integer[] trendCount = trendService.findOnTrend(problem_id);
            eachTrend.put(problem_id, trendCount);
        }
        
        return ResponseEntity.ok(eachTrend);
    }
}
