package poga.docs.clientmicroservice.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Agreement {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @JsonProperty("agreement_id")
    private Long id;

    private boolean agreed;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @ManyToOne
    private Idea idea;

    public Agreement() { }

    public Agreement(Long id, boolean agreed) {
        this.id = id;
        this.agreed = agreed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAgreed() {
        return agreed;
    }

    public void setAgreed(boolean agreed) {
        this.agreed = agreed;
    }

    @JsonIgnoreProperties({"problem", "password","firstname", "lastname", "gender", "phoneNumber", "mail", "address", "rating", "description"})
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @JsonIgnoreProperties({"participants", "agreements", "ideaheader", "key", "board", "publicState", "date"})
    public Idea getIdea() {
        return idea;
    }

    public void setIdea(Idea idea) {
        this.idea = idea;
    }
}
