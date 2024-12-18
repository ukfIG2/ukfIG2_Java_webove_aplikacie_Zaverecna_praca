package sk.ukf.Zaverecna_praca.RESTControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.ukf.Zaverecna_praca.Entity.Conference;
import sk.ukf.Zaverecna_praca.Entity.Sponsor;
import sk.ukf.Zaverecna_praca.Entity.SponsorHasConference;
import sk.ukf.Zaverecna_praca.Service.ConferenceService;
import sk.ukf.Zaverecna_praca.Service.SponsorService;

import java.util.*;

@RestController
@RequestMapping("api/sponsors")
public class SponsorRestController {

    @Autowired
    private SponsorService sponsorService;
    @Autowired
    private ConferenceService conferenceService;

    @GetMapping
    public ResponseEntity<List<Sponsor>> getAllSponsors(){
        List<Sponsor> sponsors = sponsorService.findAll();
        return ResponseEntity.ok(sponsors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sponsor> getSponsorById(@PathVariable Long id) {
        return sponsorService.findById(id)
               .map(ResponseEntity::ok)
               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Sponsor> createSponsor(@RequestBody Map<String, Object> sponsorRequest) {
        // Extract fields from the JSON request
        String nameOfSponsor = (String) sponsorRequest.get("nameOfSponsor");
        String url = (String) sponsorRequest.get("url");
        String image = (String) sponsorRequest.get("image");
        String comment = (String) sponsorRequest.get("comment");
        Long conferenceId = Long.valueOf(sponsorRequest.get("conferenceId").toString());

        // Validate and fetch the referenced conference
        Optional<Conference> existingConference = conferenceService.findById(conferenceId);
        if (existingConference.isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Return 400 Bad Request if conference doesn't exist
        }

        // Create the Sponsor object
        Sponsor newSponsor = new Sponsor();
        newSponsor.setNameOfSponsor(nameOfSponsor);
        newSponsor.setUrl(url);
        newSponsor.setImage(image);
        newSponsor.setComment(comment);

        // Create and link SponsorHasConference
        SponsorHasConference sponsorHasConference = new SponsorHasConference();
        sponsorHasConference.setConference(existingConference.get());
        sponsorHasConference.setSponsor(newSponsor);

        Set<SponsorHasConference> sponsorHasConferences = new HashSet<>();
        sponsorHasConferences.add(sponsorHasConference);
        newSponsor.setSponsorHasConference(sponsorHasConferences);

        // Save the sponsor
        sponsorService.save(newSponsor);
        return ResponseEntity.ok(newSponsor);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Sponsor> updateSponsor(@PathVariable Long id, @RequestBody Map<String, Object> sponsorRequest) {
        // Find the sponsor by ID
        Optional<Sponsor> existingSponsorOpt = sponsorService.findById(id);
        if (existingSponsorOpt.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return 404 if sponsor doesn't exist
        }

        Sponsor existingSponsor = existingSponsorOpt.get();

        // Update fields from the JSON request
        String nameOfSponsor = (String) sponsorRequest.get("nameOfSponsor");
        String url = (String) sponsorRequest.get("url");
        String image = (String) sponsorRequest.get("image");
        String comment = (String) sponsorRequest.get("comment");

        if (nameOfSponsor != null) {
            existingSponsor.setNameOfSponsor(nameOfSponsor);
        }
        if (url != null) {
            existingSponsor.setUrl(url);
        }
        if (image != null) {
            existingSponsor.setImage(image);
        }
        if (comment != null) {
            existingSponsor.setComment(comment);
        }

        // Save the updated sponsor
        sponsorService.save(existingSponsor);
        return ResponseEntity.ok(existingSponsor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSponsor(@PathVariable Long id) {
        Optional<Sponsor> existingSponsorOpt = sponsorService.findById(id);
        if (existingSponsorOpt.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return 404 if sponsor doesn't exist
        }

        sponsorService.deleteById(id);
        return ResponseEntity.ok().build();
    }



}
