package poga.docs.clientmicroservice.models;


public class ParticipantDTO {

    private Long participant_id;

    private String role;

    private Long clientId;

    // Constructors, getters, and setters

    public ParticipantDTO() {
        // Default constructor
    }

    public ParticipantDTO(Long participant_id, String role, Long clientId) {
        this.participant_id = participant_id;
        this.role = role;
        this.clientId = clientId;
    }

    // Getters and Setters
    public Long getParticipant_Id() {
        return participant_id;
    }

    public void setParticipant_Id(Long participant_id) {
        this.participant_id = participant_id;
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
