package poga.docs.clientmicroservice.models;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Problem")
public class Problem {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("problem_id")
    @Column(name = "problem_id")
    private Long id;
    
    @JsonProperty("topic")
    private String topic;

    @JsonProperty("description")
    private String description;

    @JsonProperty("category")
    private String category;

    @JsonProperty("rating")
    private Long rating;

    @JsonProperty("idea_visible")
    private boolean ideaVisible;

    @JsonProperty("date")
    @JsonFormat(pattern = "dd:MM:yyyy:HH:mm")
    private LocalDateTime date;

    // Releationship to entity 1-->N
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private Client client;

    Problem(){
        this.date = LocalDateTime.now();
     }

    public Problem(Long id, String topic, String category, String description, Long rating, Client client) {
        super();
        this.id = id;
        this.topic = topic;
        this.category = category;
        this.description = description;
        this.rating = rating;
        this.ideaVisible = false;
        this.client = client;
    }

    public Long getProblem_id() {
        return id;
    }

    public void setProblem_id(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isIdeaVisible() {
        return ideaVisible;
    }

    public void setIdeaVisible(boolean ideaVisible) {
        this.ideaVisible = ideaVisible;
    }

}
