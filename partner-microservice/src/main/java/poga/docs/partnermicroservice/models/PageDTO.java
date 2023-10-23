package poga.docs.partnermicroservice.models;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PageDTO {

    @JsonProperty("page_id")
    private Long page_id;

    @JsonProperty("details")
    private String details;

    public Long getPage_id() {
        return page_id;
    }

    public void setPage_id(Long page_id) {
        this.page_id = page_id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    
}
