package sk.ukf.Zaverecna_praca.RESTControllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.ukf.Zaverecna_praca.Entity.Sponsor;
import sk.ukf.Zaverecna_praca.Service.SponsorService;

import java.util.*;

@RestController
@RequestMapping("api/sponsors")
public class SponsorRestController {

    @Autowired
    private SponsorService sponsorService;

    @GetMapping
    public ResponseEntity<List<Sponsor>> getAllSponsors() {
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
    public ResponseEntity<Sponsor> createSponsor(@RequestBody @Valid Sponsor sponsor) {
        Sponsor savedSponsor = sponsorService.createSponsor(sponsor);
        return ResponseEntity.ok(savedSponsor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sponsor> updateSponsor(@PathVariable Long id, @RequestBody @Valid Sponsor sponsor) {
        Optional<Sponsor> updatedSponsor = sponsorService.updateSponsor(id, sponsor);
        return updatedSponsor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
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