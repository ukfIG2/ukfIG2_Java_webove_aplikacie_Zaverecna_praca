package sk.ukf.Zaverecna_praca.DTOs.ConferenceDetail;

public class UserDTO {
    private Long userID;
    private String titleBeforeName;
    private String firstName;
    private String lastName;
    private String titleAfterName;
    private String comment;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getTitleBeforeName() {
        return titleBeforeName;
    }

    public void setTitleBeforeName(String titleBeforeName) {
        this.titleBeforeName = titleBeforeName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitleAfterName() {
        return titleAfterName;
    }

    public void setTitleAfterName(String titleAfterName) {
        this.titleAfterName = titleAfterName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UserDTO(Long id, String titleBeforeName, String firstName, String lastName, String titleAfterName, String comment) {
        userID = id;
        this.titleBeforeName = titleBeforeName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.titleAfterName = titleAfterName;
        this.comment = comment;
    }
}
