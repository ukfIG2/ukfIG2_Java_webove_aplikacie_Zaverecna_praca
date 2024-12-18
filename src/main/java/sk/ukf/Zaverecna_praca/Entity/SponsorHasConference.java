package sk.ukf.Zaverecna_praca.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "sponsors_has_conferences", schema = "JAVA_Zaverecna_praca")
public class SponsorHasConference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "sponsors_id", nullable = false, referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_sponsors_has_conferences_sponsors",
                    foreignKeyDefinition = "FOREIGN KEY (sponsors_id) REFERENCES sponsors(id) ON DELETE CASCADE ON UPDATE CASCADE"))
    //@JsonManagedReference
    //@JsonIgnore
    @JsonBackReference // Tells Jackson this is the "back" part of the relationship
    private Sponsor sponsor;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "conferences_id", nullable = false, referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_sponsors_has_conferences_conferences",
                    foreignKeyDefinition = "FOREIGN KEY (conferences_id) REFERENCES conferences(id) ON DELETE CASCADE ON UPDATE CASCADE"))
    //@JsonManagedReference//
    //@JsonIgnore
    private Conference conference;

    @Column(name = "comment", columnDefinition = "TEXT COLLATE utf8mb4_slovak_ci")
    @Size(max = 65500, message = "Comment must not exceed 65500 characters")
    private String comment;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

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

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
