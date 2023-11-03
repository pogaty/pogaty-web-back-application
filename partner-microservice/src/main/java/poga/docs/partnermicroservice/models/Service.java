package poga.docs.partnermicroservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("service_id")
    @Column(name = "service_id")
    private Long service_id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("category")
    private String category;

    


    Service(){
        
    }


    public Service(Long service_id, String name, String description, String category) {
        this.service_id = service_id;
        this.name = name;
        this.description = description;
        this.category = category;
   
    }


    public Long getService_id() {
        return service_id;
    }


    public void setService_id(Long service_id) {
        this.service_id = service_id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }


    
    

    
}
