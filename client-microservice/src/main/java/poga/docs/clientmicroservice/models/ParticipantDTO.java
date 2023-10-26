package poga.docs.clientmicroservice.models;


public class ParticipantDTO {

    private Long id;

    private String role;

    private Long clientId;

    // Constructors, getters, and setters

    public ParticipantDTO() {
        // Default constructor
    }

    public ParticipantDTO(Long id, String role, Long clientId) {
        this.id = id;
        this.role = role;
        this.clientId = clientId;
    }

    // Getters and Setters
    public Long getParticipant_Id() {
        return id;
    }

    public void setParticipant_Id(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

}
