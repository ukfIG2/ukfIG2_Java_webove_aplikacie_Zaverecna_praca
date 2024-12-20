package sk.ukf.Zaverecna_praca.RESTControllers;

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
    public ResponseEntity<List<SponsorHasConference>> getAll(){
        List<SponsorHasConference> sponsorHasConferences = sponsorHasConferenceService.findAll();
        return ResponseEntity.ok(sponsorHasConferences);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SponsorHasConference> getById(@PathVariable Long id){
        return sponsorHasConferenceService.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SponsorHasConference> create(@RequestBody SponsorHasConference sponsorHasConference){
        if (sponsorHasConference.getConference().getId() == null || sponsorHasConference.getSponsor().getId() == null){
            return ResponseEntity.badRequest().body(null);
        }

        sponsorHasConferenceService.save(sponsorHasConference.getConference().getId(), sponsorHasConference.getSponsor().getId());
        return ResponseEntity.ok(sponsorHasConference);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SponsorHasConference> update(@PathVariable Long id, @RequestBody SponsorHasConference updatedSponsorHasConference){
        if (updatedSponsorHasConference.getConference().getId() == null || updatedSponsorHasConference.getSponsor().getId() == null){
            return ResponseEntity.badRequest().body(null);
        }

        Optional<SponsorHasConference> existingSponsorHasConference = sponsorHasConferenceService.findById(id);
        if (existingSponsorHasConference.isPresent()) {
            updatedSponsorHasConference.setId(id);
            sponsorHasConferenceService.update(id, updatedSponsorHasConference.getComment());
            return ResponseEntity.ok(updatedSponsorHasConference);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<SponsorHasConference> existingSponsorHasConference = sponsorHasConferenceService.findById(id);
        if (existingSponsorHasConference.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        sponsorHasConferenceService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
