package sk.ukf.Zaverecna_praca.RESTControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.ukf.Zaverecna_praca.Entity.Conference;
import sk.ukf.Zaverecna_praca.Service.ConferenceService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/conferences")
public class ConferenceRestController {

    @Autowired
    private ConferenceService conferenceService;

    @GetMapping
    public ResponseEntity<List<Conference>> getAllConferences() {
        List<Conference> conferences = conferenceService.findAll();
        return ResponseEntity.ok(conferences);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conference> getConferenceById(@PathVariable Long id) {
        Optional<Conference> conference = conferenceService.findById(id);
        return conference.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Conference> createConference(@RequestBody Conference conference) {
        // Create a new Conference object with only the required fields
        Conference newConference = new Conference();
        newConference.setNameOfConference(conference.getNameOfConference());
        newConference.setDateOfConference(conference.getDateOfConference());
        newConference.setComment(conference.getComment());
        // Set other fields to their default values or handle them as needed

        conferenceService.save(newConference);
        return ResponseEntity.ok(newConference);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conference> updateConference(@PathVariable Long id, @RequestBody Conference updatedConference) {
        Optional<Conference> conferenceOptional = conferenceService.findById(id);
        if (conferenceOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Conference conference = conferenceOptional.get();
    
        // Update specific fields
        conference.setNameOfConference(updatedConference.getNameOfConference());
        conference.setDateOfConference(updatedConference.getDateOfConference());
        conference.setComment(updatedConference.getComment());
        // Add more fields as needed
    
        conferenceService.save(conference);
        return ResponseEntity.ok(conference);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteConference(@PathVariable Long id) {
        Optional<Conference> conference = conferenceService.findById(id);
        if (conference.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        conferenceService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
