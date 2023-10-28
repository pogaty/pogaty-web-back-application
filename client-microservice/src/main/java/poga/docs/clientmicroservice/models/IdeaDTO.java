package poga.docs.clientmicroservice.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IdeaDTO {
    @JsonProperty("idea_id")
    private Long id;

    @JsonProperty("ideaHeader")
    private String ideaHeader;

    @JsonProperty("key")
    private String key;

    @JsonProperty("board")
    private String board;

    @JsonProperty("publicState")
    private boolean publicState;

    @JsonFormat(pattern = "dd:MM:yyyy:HH:mm")
    private LocalDateTime date;

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isPublicState() {
        return publicState;
    }

    public void setPublicState(boolean publicState) {
        this.publicState = publicState;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    
}
