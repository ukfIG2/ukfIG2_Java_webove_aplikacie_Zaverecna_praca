package sk.ukf.Zaverecna_praca.RESTControllers;

import jakarta.validation.Valid;
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
    public ResponseEntity<Conference> createConference(@RequestBody @Valid Conference conference) {
        Conference savedConference = conferenceService.createConference(conference);
        return ResponseEntity.ok(savedConference);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conference> updateConference(@PathVariable Long id, @RequestBody @Valid Conference updatedConference) {
        Optional<Conference> conferenceOptional = conferenceService.updateConference(id, updatedConference);
        return conferenceOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
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
