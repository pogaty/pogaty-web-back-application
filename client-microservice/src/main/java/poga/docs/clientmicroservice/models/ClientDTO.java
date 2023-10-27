package poga.docs.clientmicroservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientDTO {
    @JsonProperty("client_id")
    private Long id;

    @JsonProperty("username")
    private String username;
    
    @JsonProperty("password")
    private String password;

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("mail")
    private String mail;

    @JsonProperty("address")
    private String address;

    @JsonProperty("description")
    private String description;

    @JsonProperty("rating")
    private Long rating;

    public Long getClient_id() {
        return id;
    }

    public void setClient_id(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    
    
}
