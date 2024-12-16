package sk.ukf.Zaverecna_praca.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import sk.ukf.Zaverecna_praca.StateOfConference;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "conferences", schema = "JAVA_Zaverecna_praca")
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name_of_conference", nullable = false, length = 255,
            columnDefinition = "VARCHAR(255) COLLATE utf16_slovak_ci")
    @NotBlank(message = "Conference name must not be empty")
    @Size(max = 255, message = "Conference name must not exceed 255 characters")
    private String nameOfConference;

    @Column(name = "date_of_conference")
    @NotNull(message = "Conference date is required")
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    //@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfConference;

    @Enumerated(EnumType.STRING)
    @Column(name = "state_of_conference", nullable = false,
            columnDefinition = "ENUM('prepearing', 'registering', 'registering_ended', 'ended')")
    @NotNull(message = "State of the conference is required")
    private StateOfConference stateOfConference = StateOfConference.PREPARING;

    @Column(name = "comment", columnDefinition = "TEXT COLLATE utf16_slovak_ci")
    @Size(max = 65500, message = "Comment must not exceed 65500 characters")
    private String comment;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Relationship with stages
    @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stage> stages;

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

    //////////////////////////Getters and Setters////////////////

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfConference() {
        return nameOfConference;
    }

    public void setNameOfConference(String nameOfConference) {
        this.nameOfConference = nameOfConference;
    }

    public LocalDate getDateOfConference() {
        return dateOfConference;
    }

    public void setDateOfConference(LocalDate dateOfConference) {
        this.dateOfConference = dateOfConference;
    }

    public StateOfConference getStateOfConference() {
        return stateOfConference;
    }

    public void setStateOfConference(StateOfConference stateOfConference) {
        this.stateOfConference = stateOfConference;
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

    public List<Stage> getStages() {
        return stages;
    }
}
