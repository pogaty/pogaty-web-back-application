package poga.docs.partnermicroservice.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Entity
@Table(name = "Collaborators")
@Builder
public class Collaborator {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("collab_id")
    @Column(name = "collab_id")
    private Long collab_id;
    
    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("name")
    private String name;

    // Releationship to entity 1-->M
    @JsonProperty("Service")
    @OneToMany(mappedBy="collaborator")
    private List<Service> service;

    private String fileImage;

    Collaborator(){

    }

    public Collaborator(Long collab_id, String email, String password, String name, List<Service> service,
            String fileImage) {
        this.collab_id = collab_id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.service = service;
        this.fileImage = fileImage;
    }


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
    
    public List<Service> getService() {
        return service;
    }

    public void setService(List<Service> service) {
        this.service = service;
    }

    public String getFileImage() {
        return fileImage;
    }

    public void setFileImage(String fileImage) {
        this.fileImage = fileImage;
    }

    
}
