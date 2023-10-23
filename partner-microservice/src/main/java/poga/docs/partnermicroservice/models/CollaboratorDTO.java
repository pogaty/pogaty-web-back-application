package poga.docs.partnermicroservice.models;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CollaboratorDTO {

    @JsonProperty("collab_id")
    private Long collab_id;
    
    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("name")
    private String name;

    public Long getCollab_id() {
        return collab_id;
    }

    public void setCollab_id(Long collab_id) {
        this.collab_id = collab_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
