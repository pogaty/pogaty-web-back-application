package poga.docs.clientmicroservice.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Idea")
public class Idea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("idea_id")
    private Long id;

    @JsonProperty("ideaHeader")
    private String ideaHeader;

    @JsonProperty("key")
    private String key;

    @JsonProperty("board")
    private String board;

    @JsonProperty("agreement")
    private int agreement;

    @JsonProperty("publicState")
    private boolean publicState;

    @JsonProperty("date")
    @JsonFormat(pattern = "dd:MM:yyyy:HH:mm")
    private LocalDateTime date;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client creator;


    // Releationship to entity M-->N
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "idea_participants",
                joinColumns = @JoinColumn(name="idea_id",referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name="participant_id" , referencedColumnName = "id"))
    private List<Participant> participants;

    Idea(){
        this.date = LocalDateTime.now();
    }

    public Idea(Long id, String ideaHeader, String key, String board, int agreement, boolean publicState) {
        super();
        this.id = id;
        this.ideaHeader = ideaHeader;
        this.key = key;
        this.board = board;
        this.agreement = agreement;
        this.publicState = publicState;
    }

    public Long getIdea_id() {
        return id;
    }

    public void setIdea_id(Long id) {
        this.id = id;
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

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipant(List<Participant> participants) {
        this.participants = participants;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Client getCreator() {
        return creator;
    }

    public void setCreator(Client creator) {
        this.creator = creator;
    }

    public boolean isPublicState() {
        return publicState;
    }

    public void setPublicState(boolean publicState) {
        this.publicState = publicState;
    }
}
