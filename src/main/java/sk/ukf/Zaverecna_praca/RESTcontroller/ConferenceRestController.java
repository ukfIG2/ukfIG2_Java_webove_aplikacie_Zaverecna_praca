package sk.ukf.Zaverecna_praca.RESTcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.ukf.Zaverecna_praca.StateOfConference;
import sk.ukf.Zaverecna_praca.dto.ConferenceDTO;
import sk.ukf.Zaverecna_praca.entity.Conference;
import sk.ukf.Zaverecna_praca.service.ConferenceService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/conferences")
public class ConferenceRestController {

    @Autowired
    private ConferenceService conferenceService;

    // Get all conferences as DTOs
    @GetMapping
    /*public List<ConferenceDTO> getAllConferences() {
        return conferenceService.findAll();
    }*/
    public List<Conference> getAllConferences()
    {
        return conferenceService.findAll();
    }

    // Get a single conference by ID as a DTO
    @GetMapping("/{id}")
    public ResponseEntity<ConferenceDTO> getConferenceById(@PathVariable Long id) {
        Optional<ConferenceDTO> conference = conferenceService.findById(id);
        return conference.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new conference using a DTO
    @PostMapping
    public ResponseEntity<ConferenceDTO> createConference(@RequestBody ConferenceDTO conferenceDTO) {
        // Map DTO to Entity
        Conference newConference = new Conference();
        newConference.setNameOfConference(conferenceDTO.getNameOfConference());
        newConference.setDateOfConference(conferenceDTO.getDateOfConference());
        newConference.setComment(conferenceDTO.getComment());

        // Save and return the saved DTO
        ConferenceDTO savedConference = conferenceService.save(newConference);
        return ResponseEntity.ok(savedConference);
    }

    // Update an existing conference by ID
   @PutMapping("/{id}")
public ResponseEntity<ConferenceDTO> updateConference(@PathVariable Long id, @RequestBody ConferenceDTO conferenceDTO) {
    Optional<ConferenceDTO> existingConference = conferenceService.findById(id);
    if (existingConference.isPresent()) {
        Conference updatedConference = new Conference();
        updatedConference.setId(id); // Retain original ID
        updatedConference.setNameOfConference(conferenceDTO.getNameOfConference());
        updatedConference.setDateOfConference(conferenceDTO.getDateOfConference());
        updatedConference.setComment(conferenceDTO.getComment());

        // Save and return updated DTO
        ConferenceDTO updatedConferenceDTO = conferenceService.save(updatedConference);
        return ResponseEntity.ok(updatedConferenceDTO);
    } else {
        return ResponseEntity.notFound().build();
    }
}

    // Delete a conference by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConference(@PathVariable Long id) {
        if (conferenceService.findById(id).isPresent()) {
            conferenceService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
