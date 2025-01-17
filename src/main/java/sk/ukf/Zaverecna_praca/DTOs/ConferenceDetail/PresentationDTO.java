package sk.ukf.Zaverecna_praca.DTOs.ConferenceDetail;

import java.time.LocalTime;
import java.util.List;

public class PresentationDTO {
    private Long presentationID;
    private String nameOfPresentation;
    private LocalTime startAt;
    private LocalTime endAt;
    private String longDescription;
    private int capacity;
    private List<UserDTO> users;
    private boolean isAlreadyRegistered;

    public Long getPresentationID() {
        return presentationID;
    }

    public void setPresentationID(Long presentationID) {
        this.presentationID = presentationID;
    }

    public String getNameOfPresentation() {
        return nameOfPresentation;
    }

    public void setNameOfPresentation(String nameOfPresentation) {
        this.nameOfPresentation = nameOfPresentation;
    }

    public LocalTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalTime startAt) {
        this.startAt = startAt;
    }

    public LocalTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalTime endAt) {
        this.endAt = endAt;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    public PresentationDTO(Long id, String nameOfPresentation, LocalTime startAt, LocalTime endAt, String longDescription, int capacity, List<UserDTO> users) {
        presentationID = id;
        this.nameOfPresentation = nameOfPresentation;
        this.startAt = startAt;
        this.endAt = endAt;
        this.longDescription = longDescription;
        this.capacity = capacity;
        this.users = users;
    }

    public PresentationDTO(Long presentationID, String nameOfPresentation, LocalTime startAt, LocalTime endAt, String longDescription, int capacity, List<UserDTO> users, boolean isAlreadyRegistered) {
        this.presentationID = presentationID;
        this.nameOfPresentation = nameOfPresentation;
        this.startAt = startAt;
        this.endAt = endAt;
        this.longDescription = longDescription;
        this.capacity = capacity;
        this.users = users;
        this.isAlreadyRegistered = isAlreadyRegistered;
    }

    public boolean isAlreadyRegistered() {
        return isAlreadyRegistered;
    }

    public void setAlreadyRegistered(boolean alreadyRegistered) {
        isAlreadyRegistered = alreadyRegistered;
    }
}
