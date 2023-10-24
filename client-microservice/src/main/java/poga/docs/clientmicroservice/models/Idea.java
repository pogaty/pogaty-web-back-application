package poga.docs.clientmicroservice.models;

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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Idea")
public class Idea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("idea_id")
    @Column(name = "idea_id")
    private Long idea_id;

    @JsonProperty("ideaHeader")
    private String ideaHeader;

    @JsonProperty("board")
    private String board;

    @JsonProperty("agreement")
    private int agreement;

    

    // Releationship to entity M-->N
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "idea_participants",
                joinColumns = @JoinColumn(name="idea_id",referencedColumnName = "idea_id"),
                inverseJoinColumns = @JoinColumn(name="participant_id" , referencedColumnName = "participant_id"))
    private List<Participant> participant;

    Idea(){
        
    }

    public Idea(Long idea_id, String ideaHeader, String board, int agreement,
            List<Participant> participants) {
        this.idea_id = idea_id;
        this.ideaHeader = ideaHeader;
        this.board = board;
        this.agreement = agreement;
        this.participant = participants;
    }

    public Long getIdea_id() {
        return idea_id;
    }

    public void setIdea_id(Long idea_id) {
        this.idea_id = idea_id;
    }

    public String getIdeaHeader() {
        return ideaHeader;
    }

    public void setIdeaHeader(String ideaHeader) {
        this.ideaHeader = ideaHeader;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public int getAgreement() {
        return agreement;
    }

    public void setAgreement(int agreement) {
        this.agreement = agreement;
    }

    public List<Participant> getParticipant() {
        return participant;
    }

    public void setParticipant(List<Participant> participant) {
        this.participant = participant;
    }

    
}
