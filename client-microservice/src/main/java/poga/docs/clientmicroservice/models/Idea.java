package poga.docs.clientmicroservice.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Idea")
@JsonIgnoreProperties("ideaReactions")
public class Idea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("idea_id")
    private Long id;

    @JsonProperty("ideaHeader")
    private String ideaHeader;

    @Column(length = 1500)
    @JsonProperty("key")
    private String key;

    @JsonProperty("board")
    private String board;

    @JsonProperty("publicState")
    private boolean publicState;

    @JsonProperty("date")
    @JsonFormat(pattern = "dd:MM:yyyy:HH:mm")
    private LocalDateTime date;

    // Releationship to entity M-->N
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "idea_participants",
                joinColumns = @JoinColumn(name="idea_id",referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name="participant_id" , referencedColumnName = "id"))
    private List<Participant> participants;

    @OneToMany(cascade =CascadeType.MERGE)
    @JoinColumn(name = "idea_id", referencedColumnName = "id")
    private List<Agreement> agreements;

    public Idea(){
        this.date = LocalDateTime.now();
        this.participants = new ArrayList<Participant>();
    }

    public Idea(Long id, String ideaHeader, String key, String board, boolean publicState) {
        super();
        this.id = id;
        this.ideaHeader = ideaHeader;
        this.key = key;
        this.board = board;
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

    public boolean isPublicState() {
        return publicState;
    }

    public void setPublicState(boolean publicState) {
        this.publicState = publicState;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    @JsonIgnoreProperties("idea")
    public List<Agreement> getAgreements() {
        return agreements;
    }

    public void setAgreements(List<Agreement> agreements) {
        this.agreements = agreements;
    }
}
