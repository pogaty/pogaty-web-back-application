package poga.docs.clientmicroservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poga.docs.clientmicroservice.models.Agreement;
import poga.docs.clientmicroservice.repositories.AgreementRepository;

@Service
public class AgreementService {
    private final AgreementRepository agreementRepository;

    @Autowired
    public AgreementService(AgreementRepository agreementRepository) {
        this.agreementRepository= agreementRepository;
    }

    public List<Agreement> findAllReactions() {
        return agreementRepository.findAll();
    }

    public Optional<Agreement> findByReactionFactor(Long client_id, Long idea_id) {
        return agreementRepository.findByClientIdAndIdeaId(client_id, idea_id);
    }

    public List<Agreement> findByIdeaIdAgree(Long idea_id) {
        return agreementRepository.findByIdeaIdAgree(idea_id);
    }

    public List<Agreement> findByIdeaIdDisagree(Long idea_id) {
        return agreementRepository.findByIdeaIdDisagree(idea_id);
    }
}
