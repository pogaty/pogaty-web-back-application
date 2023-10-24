package poga.docs.clientmicroservice.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProblemDTO {

    @JsonProperty("problem_id")
    private Long problem_id;
    
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

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime date;

    public Long getProblem_id() {
        return problem_id;
    }

    public void setProblem_id(Long problem_id) {
        this.problem_id = problem_id;
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
