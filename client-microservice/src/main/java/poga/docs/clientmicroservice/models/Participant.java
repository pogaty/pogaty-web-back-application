package poga.docs.clientmicroservice.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Participants")
@JsonIgnoreProperties("ideas")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("participant_id")
    private Long id;

    @JsonProperty("role")
    private String role;

    // Releationship to entity 1-->1
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @ManyToMany(mappedBy = "participants")
    private List<Idea> ideas;

    public Participant(){
        this.ideas = new ArrayList<Idea>();
    }

    public Participant(Long id, String role, Client client) {
        this.id = id;
        this.role = role;
        this.client = client;
    }

    public Long getParticipant_id() {
        return id;
    }

    public void setParticipant_id(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @JsonIgnoreProperties({"problem", "password","firstname", "lastname", "gender", "phoneNumber", "mail", "address", "rating", "description"})
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Idea> getIdeas() {
        return ideas;
    }

    public void setIdeas(List<Idea> ideas) {
        this.ideas = ideas;
    }

}
