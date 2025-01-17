package sk.ukf.Zaverecna_praca.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import sk.ukf.Zaverecna_praca.Enums.Role;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", schema = "JAVA_Zaverecna_praca",
        uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true, length = 255,
            columnDefinition = "VARCHAR(255) COLLATE utf8mb4_slovak_ci")
    @NotBlank(message = "Email nesmie byť prázdny")
    @Email(message = "Email musí byť platný")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email musí byť v správnom formáte. ***@**.**")
    @Size(max = 255, message = "Email nesmie presiahnuť 255 znakov")
    private String email;

    @Column(name = "title_before_name", length = 255,
            columnDefinition = "VARCHAR(255) COLLATE utf8mb4_slovak_ci")
    @Size(max = 255, message = "Titul pred menom nesmie presiahnuť 255 znakov")
    private String titleBeforeName;

    @Column(name = "first_name", nullable = false, length = 255,
            columnDefinition = "VARCHAR(255) COLLATE utf8mb4_slovak_ci")
    @NotBlank(message = "Meno nesmie byť prázdne")
    @Size(max = 255, message = "Meno nesmie presiahnuť 255 znakov")
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 255,
            columnDefinition = "VARCHAR(255) COLLATE utf8mb4_slovak_ci")
    @NotBlank(message = "Priezvisko nesmie byť prázdne")
    @Size(max = 255, message = "Priezvisko nesmie presiahnuť 255 znakov")
    private String lastName;

    @Column(name = "title_after_name", length = 255,
            columnDefinition = "VARCHAR(255) COLLATE utf8mb4_slovak_ci")
    @Size(max = 255, message = "Titul za menom nesmie presiahnuť 255 znakov")
    private String titleAfterName;

    @Column(name = "password", nullable = false, length = 255,
            columnDefinition = "VARCHAR(255) COLLATE utf8mb4_slovak_ci")
    @NotBlank(message = "Heslo nesmie byť prázdne")
    @Size(min = 8, max = 255, message = "Heslo musí mať medzi 8 a 255 znakmi")
/*@Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,255}$",
        message = "Heslo musí obsahovať aspoň jedno malé písmeno, jedno veľké písmeno a jednu číslicu. Špeciálne znaky sú voliteľné"
)*/
    private String password;

    @Column(name = "phone_number", length = 30,
            columnDefinition = "VARCHAR(30) COLLATE utf8mb4_slovak_ci")
    @Size(max = 30, message = "Telefónne číslo nesmie presiahnuť 30 znakov")
    private String phoneNumber;

    @Column(name = "photo", length = 255,
            columnDefinition = "VARCHAR(255) COLLATE utf8mb4_slovak_ci")
    @Size(max = 255, message = "Fotka nesmie presiahnuť 255 znakov")
    private String photo;

    @Column(name = "comment", columnDefinition = "TEXT COLLATE utf8mb4_slovak_ci")
    @Size(max = 65500, message = "Komentár nesmie presiahnuť 65500 znakov")
    private String comment;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false,
            columnDefinition = "ENUM('ROLE_admin', 'ROLE_registered_visitor', 'ROLE_speaker')")
    //@NotBlank(message = "Role must not be empty") //Validator not found????
    private Role role = Role.ROLE_registered_visitor; // Default role

// Bi-directional mapping (optional)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PresentationsHasSpeakers> speakersRelations = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PresentationsHasParticipants> participantsRelations = new ArrayList<>();


    // Lifecycle hooks for automatic timestamp updates
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    ////////////////////////// Getters and Setters //////////////////////////

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
/*
    public Set<PresentationsHasParticipants> getPresentationsHasParticipants() {
        return presentationsHasParticipants;
    }

    public void setPresentationsHasParticipants(Set<PresentationsHasParticipants> presentationsHasParticipants) {
        this.presentationsHasParticipants = presentationsHasParticipants;
    }

    public Set<PresentationsHasSpeakers> getPresentationsHasSpeakers() {
        return presentationsHasSpeakers;
    }

    public void setPresentationsHasSpeakers(Set<PresentationsHasSpeakers> presentationsHasSpeakers) {
        this.presentationsHasSpeakers = presentationsHasSpeakers;
    }

 */

    public List<PresentationsHasSpeakers> getSpeakersRelations() {
        return speakersRelations;
    }

    public void setSpeakersRelations(List<PresentationsHasSpeakers> speakersRelations) {
        this.speakersRelations = speakersRelations;
    }

    public List<PresentationsHasParticipants> getParticipantsRelations() {
        return participantsRelations;
    }

    public void setParticipantsRelations(List<PresentationsHasParticipants> participantsRelations) {
        this.participantsRelations = participantsRelations;
    }
}