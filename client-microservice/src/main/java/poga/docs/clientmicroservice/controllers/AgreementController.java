package poga.docs.clientmicroservice.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poga.docs.clientmicroservice.models.Agreement;
import poga.docs.clientmicroservice.services.AgreementService;

@RestController
@RequestMapping("/agreements")
public class AgreementController {
    private final AgreementService agreementService;

    @Autowired
    public AgreementController(AgreementService agreementService) {
        this.agreementService = agreementService;
    }

    @GetMapping()
    public ResponseEntity<List<Agreement>> getIdeaReactions() {
        List<Agreement> agreements = agreementService.findAllReactions();
        return ResponseEntity.ok(agreements);
    }

    @GetMapping("on_idea/{idea_id}/by/{client_id}")
    public ResponseEntity<?> getIdeaReactionByClientId(@PathVariable Long idea_id, @PathVariable Long client_id) {
        Optional<Agreement> agreementOpt = agreementService.findByReactionFactor(client_id, idea_id);

        if (!agreementOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("reaction not found.");
        }

        Agreement agreement = agreementOpt.get();
        return ResponseEntity.ok(agreement);
    }

    @GetMapping("on_idea/{idea_id}/agree")
    public ResponseEntity<?> getIdeaReactionByIdeaIdAgree(@PathVariable Long idea_id) {
        List<Agreement> agreements = agreementService.findByIdeaIdAgree(idea_id);

        if (agreements.isEmpty()) {
            return ResponseEntity.status(204).body("no reaction contents.");
        }

        return ResponseEntity.ok(agreements);
    }

    @GetMapping("on_idea/{idea_id}/disagree")
    public ResponseEntity<?> getIdeaReactionByIdeaIdDisagree(@PathVariable Long idea_id) {
        List<Agreement> agreements = agreementService.findByIdeaIdDisagree(idea_id);

        if (agreements.isEmpty()) {
            return ResponseEntity.status(204).body("no reaction contents.");
        }

        return ResponseEntity.ok(agreements);
    }
}
