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
@Table(name = "Campaigns")
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("campaign_id")
    @Column(name = "campaign_id")
    private Long campaign_id;

    @JsonProperty("topic")
    private String topic;

    @JsonProperty("description")
    private String description;

    @JsonProperty("reward")
    private double reward;

    @ManyToOne
    @JoinColumn(name = "page_id",referencedColumnName = "page_id")
    private Page page;


    Campaign(){

    }


    public Campaign(Long campaign_id, String topic, String description, double reward, Page page) {
        this.campaign_id = campaign_id;
        this.topic = topic;
        this.description = description;
        this.reward = reward;
        this.page = page;
    }


    public Long getCampaign_id() {
        return campaign_id;
    }


    public void setCampaign_id(Long campaign_id) {
        this.campaign_id = campaign_id;
    }


    public String getTopic() {
        return topic;
    }


    public void setTopic(String topic) {
        this.topic = topic;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public double getReward() {
        return reward;
    }


    public void setReward(double reward) {
        this.reward = reward;
    }


    public Page getPage() {
        return page;
    }


    public void setPage(Page page) {
        this.page = page;
    }
    
    

    
}
