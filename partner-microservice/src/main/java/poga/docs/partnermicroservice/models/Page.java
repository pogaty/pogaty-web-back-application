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

@Entity
@Table(name = "Pages")
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("page_id")
    @Column(name = "page_id")
    private Long page_id;

    @JsonProperty("details")
    private String details;

    @JsonProperty("service")
    @OneToMany(mappedBy="page")
    private List<Service> service;

    @JsonProperty("Campaign")
    @OneToMany(mappedBy="page")
    private List<Campaign> campaign;

    Page(){

    }

    public Page(Long page_id, String details, List<Service> service, List<Campaign> campaign) {
        this.page_id = page_id;
        this.details = details;
        this.service = service;
        this.campaign = campaign;
    }

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

    public List<Service> getService() {
        return service;
    }

    public void setService(List<Service> service) {
        this.service = service;
    }

    public List<Campaign> getCampaign() {
        return campaign;
    }

    public void setCampaign(List<Campaign> campaign) {
        this.campaign = campaign;
    }

    

    
    
    
}
