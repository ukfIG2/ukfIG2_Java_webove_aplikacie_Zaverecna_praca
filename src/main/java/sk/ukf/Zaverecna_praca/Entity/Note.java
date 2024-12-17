package sk.ukf.Zaverecna_praca.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "notes", schema = "JAVA_Zaverecna_praca")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name_of_note", nullable = false, columnDefinition = "TEXT COLLATE utf8mb4_slovak_ci")
    @NotNull(message = "Name of the note is required")
    @Size(max = 255, message = "Name of the note must not exceed 255 characters")
    private String nameOfNote;

    @Column(name = "note", columnDefinition = "TEXT COLLATE utf8mb4_slovak_ci")
    @Size(max = 65500, message = "Note must not exceed 65500 characters")
    private String comment;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "conference_id", nullable = false, referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_notes_conferences",
                    foreignKeyDefinition = "FOREIGN KEY (conference_id) REFERENCES conferences(id) ON DELETE SET NULL ON UPDATE CASCADE"))
    //@JsonBackReference
    @JsonIgnoreProperties({"notes"}) // Prevents back-reference recursion
    private Conference conference;

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

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public String getNameOfNote() {
        return nameOfNote;
    }

    public void setNameOfNote(String nameOfNote) {
        this.nameOfNote = nameOfNote;
    }
}