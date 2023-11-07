package poga.docs.partnermicroservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Services")
public class Services {
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

    @JsonProperty("serviceType")
    private String serviceType;

    @ManyToOne
    @JoinColumn(name = "collaborator_id")
    @JsonIgnoreProperties("Service")
    private Collaborator collaborator;

    @JsonProperty("minimumPaid")
    private Double minimumPaid;

    @JsonProperty("costScope")
    private Double costScope;

    public Services() {

    }

    public Services(String name, String description, String category, String serviceType, Collaborator collaborator) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.serviceType = serviceType;
        this.collaborator = collaborator;
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

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    @JsonIgnoreProperties({"service", "fileImage", "password"})
    public Collaborator getCollaborator() {
        return collaborator;
    }

    public void setCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
    }

    public Double getMinimumPaid() {
        return minimumPaid;
    }

    public void setMinimumPaid(Double minimumPaid) {
        this.minimumPaid = minimumPaid;
    }

    public Double getCostScope() {
        return costScope;
    }

    public void setCostScope(Double costScope) {
        this.costScope = costScope;
    }

    

}
