package sk.ukf.Zaverecna_praca.RESTControllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.ukf.Zaverecna_praca.Entity.SponsorHasConference;
import sk.ukf.Zaverecna_praca.Service.SponsorHasConferenceService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/relationships2")
public class SponsorHasConferenceController {

    @Autowired
    private SponsorHasConferenceService sponsorHasConferenceService;

    @GetMapping
    public ResponseEntity<List<SponsorHasConference>> getAll() {
        List<SponsorHasConference> sponsorHasConferences = sponsorHasConferenceService.findAll();
        return ResponseEntity.ok(sponsorHasConferences);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SponsorHasConference> getById(@PathVariable Long id) {
        return sponsorHasConferenceService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SponsorHasConference> create(@RequestBody @Valid SponsorHasConference sponsorHasConference) {
        SponsorHasConference savedSponsorHasConference = sponsorHasConferenceService.create(sponsorHasConference);
        return ResponseEntity.ok(savedSponsorHasConference);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SponsorHasConference> update(@PathVariable Long id, @RequestBody @Valid SponsorHasConference updatedSponsorHasConference) {
        Optional<SponsorHasConference> updatedSponsorHasConferenceOpt = sponsorHasConferenceService.update(id, updatedSponsorHasConference.getComment());
        return updatedSponsorHasConferenceOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<SponsorHasConference> existingSponsorHasConference = sponsorHasConferenceService.findById(id);
        if (existingSponsorHasConference.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        sponsorHasConferenceService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}