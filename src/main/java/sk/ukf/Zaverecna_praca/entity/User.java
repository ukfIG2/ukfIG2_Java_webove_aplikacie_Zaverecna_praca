package sk.ukf.Zaverecna_praca.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", schema = "JAVA_Zaverecna_praca",
        uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "title_before_name", length = 255)
    @Size(max = 255, message = "Title before name must not exceed 255 characters")
    private String titleBeforeName;


    @Column(name = "first_name", nullable = false, length = 255)
    @NotBlank(message = "First name must not be empty")
    @Size(max = 255, message = "First name must not exceed 100 characters")
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    @NotBlank(message = "Last name must not be empty")
    @Size(max = 100, message = "Last name must not exceed 100 characters")
    private String lastName;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    @NotBlank(message = "Email must not be empty")
    @Email(message = "Email must be valid")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email must be in the correct format. ***@**.**")
    @Size(max = 255, message = "Email must not exceed 255 characters")
    private String email;

    @Column(name = "title_after_name", length = 255)
    @Size(max = 255, message = "Title after name must not exceed 255 characters")
    private String titleAfterName;

    @Column(name = "password", nullable = false, length = 60, columnDefinition = "VARCHAR(60)")
    @NotBlank(message = "Password must not be empty")
    @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters long")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,255}$",
            message = "Password must contain at least one lowercase letter, one uppercase letter, and one number. Special characters are optional"
    )
    private String password;

    @Column(name = "role", nullable = false)
    @NotBlank(message = "Role must not be empty")
    private String role = "ROLE_REGISTERED_VISITOR"; // Default role

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    // Relationships
    @ManyToMany
    @JoinTable(
            name = "user_conferences",
            schema = "JAVA_Zaverecna_praca",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk_user_conferences_user")),
            inverseJoinColumns = @JoinColumn(name = "conference_id", referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk_user_conferences_conference"))
    )
    private Set<Conference> conferences = new HashSet<>();

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public Set<Conference> getConferences() {
        return conferences;
    }

    public void setConferences(Set<Conference> conferences) {
        this.conferences = conferences;
    }
}