package sk.ukf.Zaverecna_praca.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "sponsors", schema = "JAVA_Zaverecna_praca")
public class Sponsor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name_of_sponsor", nullable = false, length = 255,
            columnDefinition = "VARCHAR(255) COLLATE utf8mb4_slovak_ci")
    @NotBlank(message = "Sponsor name must not be empty")
    @Size(max = 255, message = "Sponsor name must not exceed 255 characters")
    private String nameOfSponsor;

    @Column(name = "url", length = 255,
            columnDefinition = "VARCHAR(255) COLLATE utf8mb4_slovak_ci")
    @NotBlank(message = "Url name must not be empty")
    @Size(max = 255, message = "Url name must not exceed 255 characters")
    private String url;

    @Column(name = "image", length = 255,
            columnDefinition = "VARCHAR(255) COLLATE utf8mb4_slovak_ci")
    @NotBlank(message = "Image name must not be empty")
    @Size(max = 255, message = "Image name must not exceed 255 characters")
    private String image;

    @Column(name = "comment", columnDefinition = "TEXT COLLATE utf8mb4_slovak_ci")
    @Size(max = 65500, message = "Comment must not exceed 65500 characters")
    private String comment;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Relationship with conferences
    @OneToMany(mappedBy = "sponsor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    //@JsonManagedReference("sponsor-conferences")
    private Set<SponsorHasConference> sponsorHasConference;

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

    public String getNameOfSponsor() {
        return nameOfSponsor;
    }

    public void setNameOfSponsor(String nameOfSponsor) {
        this.nameOfSponsor = nameOfSponsor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public Set<SponsorHasConference> getSponsorHasConference() {
        return sponsorHasConference;
    }

    public void setSponsorHasConference(Set<SponsorHasConference> sponsorHasConference) {
        this.sponsorHasConference = sponsorHasConference;
    }
}