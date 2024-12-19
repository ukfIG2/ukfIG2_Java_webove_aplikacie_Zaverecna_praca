package sk.ukf.Zaverecna_praca.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import sk.ukf.Zaverecna_praca.Enums.Role;

import java.time.LocalDateTime;
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
    @NotBlank(message = "Email must not be empty")
    @Email(message = "Email must be valid")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email must be in the correct format. ***@**.**")
    @Size(max = 255, message = "Email must not exceed 255 characters")
    private String email;

    @Column(name = "title_before_name", length = 255,
            columnDefinition = "VARCHAR(255) COLLATE utf8mb4_slovak_ci")
    @Size(max = 255, message = "Title before name must not exceed 255 characters")
    private String titleBeforeName;

    @Column(name = "first_name", nullable = false, length = 255,
            columnDefinition = "VARCHAR(255) COLLATE utf8mb4_slovak_ci")
    @NotBlank(message = "First name must not be empty")
    @Size(max = 255, message = "First name must not exceed 255 characters")
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 255,
            columnDefinition = "VARCHAR(255) COLLATE utf8mb4_slovak_ci")
    @NotBlank(message = "Last name must not be empty")
    @Size(max = 255, message = "Last name must not exceed 255 characters")
    private String lastName;

    @Column(name = "title_after_name", length = 255,
            columnDefinition = "VARCHAR(255) COLLATE utf8mb4_slovak_ci")
    @Size(max = 255, message = "Title after name must not exceed 255 characters")
    private String titleAfterName;

    @Column(name = "password", nullable = false, length = 255,
            columnDefinition = "VARCHAR(255) COLLATE utf8mb4_slovak_ci")
    @NotBlank(message = "Password must not be empty")
    @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters long")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,255}$",
            message = "Password must contain at least one lowercase letter, one uppercase letter, and one number. Special characters are optional"
    )
    private String password;

    @Column(name = "phone_number", length = 30,
            columnDefinition = "VARCHAR(30) COLLATE utf8mb4_slovak_ci")
    @Size(max = 30, message = "Phone number must not exceed 30 characters")
    private String phoneNumber;

    @Column(name = "photo", length = 255,
            columnDefinition = "VARCHAR(255) COLLATE utf8mb4_slovak_ci")
    @Size(max = 255, message = "Photo must not exceed 255 characters")
    private String photo;

    @Column(name = "comment", columnDefinition = "TEXT COLLATE utf8mb4_slovak_ci")
    @Size(max = 65500, message = "Comment must not exceed 65500 characters")
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
/*
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PresentationsHasParticipants> presentationsHasParticipants;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PresentationsHasSpeakers> presentationsHasSpeakers;
*/
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
}