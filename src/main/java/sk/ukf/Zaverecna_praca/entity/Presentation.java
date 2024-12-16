package sk.ukf.Zaverecna_praca.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "presentations", schema = "JAVA_Zaverecna_praca")
public class Presentation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name_of_presentation", nullable = false, length = 255,
            columnDefinition = "VARCHAR(255) COLLATE utf16_slovak_ci")
    @NotBlank(message = "Presentation name must not be empty")
    @Size(max = 255, message = "Presentation name must not exceed 255 characters")
    private String nameOfPresentation;

    @Column(name = "start_at")
    @NotNull(message = "Start time is required")
    private LocalTime startAt;

    @Column(name = "end_at")
    @NotNull(message = "End time is required")
    private LocalTime endAt;

    @Column(name = "short_description", length = 255,
            columnDefinition = "VARCHAR(255) COLLATE utf16_slovak_ci")
    @Size(max = 255, message = "Short description must not exceed 255 characters")
    private String shortDescription;

    @Column(name = "long_description", columnDefinition = "TEXT COLLATE utf16_slovak_ci")
    @Size(max = 65500, message = "Long description must not exceed 65500 characters")
    private String longDescription;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id", nullable = false, referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_presentations_stages"))
    @NotNull(message = "Stage must be specified")
    private Stage stage;

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

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
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

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
