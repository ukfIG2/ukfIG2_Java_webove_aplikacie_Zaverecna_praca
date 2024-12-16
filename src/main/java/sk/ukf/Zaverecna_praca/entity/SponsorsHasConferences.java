package sk.ukf.Zaverecna_praca.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sponsors_has_conferences", schema = "JAVA_Zaverecna_praca")
public class SponsorsHasConferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "sponsors_id", nullable = false, referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_sponsors_has_conferences_sponsors",
                    foreignKeyDefinition = "FOREIGN KEY (sponsors_id) REFERENCES sponsors(id) ON DELETE CASCADE ON UPDATE CASCADE"))
    private Sponsors sponsor;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "conferences_id", nullable = false, referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_sponsors_has_conferences_conferences",
                    foreignKeyDefinition = "FOREIGN KEY (conferences_id) REFERENCES conferences(id) ON DELETE CASCADE ON UPDATE CASCADE"))
    private Conference conference;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

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

    public Sponsors getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsors sponsor) {
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
}