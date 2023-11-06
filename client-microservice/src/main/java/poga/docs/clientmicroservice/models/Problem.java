package poga.docs.clientmicroservice.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Problem")
@JsonIgnoreProperties("marks")
public class Problem {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("problem_id")
    private Long id;
    
    @JsonProperty("topic")
    private String topic;

    @Column(length = 1000)
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

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "problem_id", referencedColumnName = "id")
    private List<Idea> ideas;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "mark_problems",
                joinColumns = @JoinColumn(name="problem_id",referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name="client_id" , referencedColumnName = "id"))
    private List<Client> marks;

    @OneToMany(cascade =CascadeType.MERGE)
    @JoinColumn(name = "problem_id", referencedColumnName = "id")
    private List<Trend> trends;
    
    Problem(){
        this.date = LocalDateTime.now();
    }

    public Problem(Long id, String topic, String category, String description, Long rating, Client client) {
        super();
        this.id = id;
        this.topic = topic;
        this.category = category;
        this.description = description;
        this.category = category;
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

    @JsonIgnoreProperties({"problem", "password","firstname", "lastname", "gender", "phoneNumber", "mail", "address", "rating", "description"})
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

    @JsonIgnoreProperties({"board"})
    public List<Idea> getIdeas() {
        return ideas;
    }

    public void setIdeas(List<Idea> ideas) {
        this.ideas = ideas;
    }

    @JsonIgnoreProperties({"problem", "password","firstname", "lastname", "gender", "phoneNumber", "mail", "address", "rating", "description"})
    public List<Client> getMarks() {
        return this.marks;
    }

    public void setMarks(List<Client> marks) {
        this.marks = marks;
    }

    @JsonIgnoreProperties("problem")
    public List<Trend> getTrends() {
        return trends;
    }

    public void setTrends(List<Trend> trends) {
        this.trends = trends;
    }

    
}
