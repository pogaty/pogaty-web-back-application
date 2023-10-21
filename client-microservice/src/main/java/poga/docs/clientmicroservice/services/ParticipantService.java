package poga.docs.clientmicroservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poga.docs.clientmicroservice.models.Participant;
import poga.docs.clientmicroservice.repositories.ParticipantRepository;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    @Autowired
    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public List<Participant> findAllPaticipant() {
        return this.participantRepository.findAll();
    }

    public List<Participant> findByRolePaticipant(String role) {
        return this.participantRepository.findByRole(role);
    }
}
