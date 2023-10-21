package poga.docs.clientmicroservice.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Participants")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("participant_id")
    @Column(name = "participant_id")
    private Long participant_id;

    @JsonProperty("role")
    private String role;

    // Releationship to entity 1-->1
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private Client client;

    Participant(){}

    public Participant(Long participant_id, String role, Client client) {
        this.participant_id = participant_id;
        this.role = role;
        this.client = client;
    }

    public Long getParticipant_id() {
        return participant_id;
    }

    public void setParticipant_id(Long participant_id) {
        this.participant_id = participant_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    
}
