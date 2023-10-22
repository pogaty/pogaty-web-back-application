package poga.docs.clientmicroservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IdeaDTO {
    @JsonProperty("idea_id")
    private Long idea_id;

    @JsonProperty("ideaHeader")
    private String ideaHeader;

    @JsonProperty("board")
    private String board;

    @JsonProperty("agreement")
    private int agreement;

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

    
}
