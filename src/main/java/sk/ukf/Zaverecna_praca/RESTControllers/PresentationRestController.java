package sk.ukf.Zaverecna_praca.RESTControllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.ukf.Zaverecna_praca.Entity.Presentation;
import sk.ukf.Zaverecna_praca.Service.PresentationService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/presentations")
public class PresentationRestController {

    @Autowired
    private PresentationService presentationService;

    @GetMapping
    public ResponseEntity<List<Presentation>> getAllPresentations() {
        List<Presentation> presentations = presentationService.getAllPresentations();
        return ResponseEntity.ok(presentations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Presentation> getPresentationById(@PathVariable Long id) {
        return presentationService.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Presentation> createPresentation(@RequestBody @Valid Presentation presentation) {
        Presentation savedPresentation = presentationService.createPresentation(presentation);
        return ResponseEntity.ok(savedPresentation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Presentation> updatePresentation(@PathVariable Long id, @RequestBody @Valid Presentation updatedPresentation) {
        Optional<Presentation> updatedPresentationOpt = presentationService.updatePresentation(id, updatedPresentation);
        return updatedPresentationOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePresentation(@PathVariable Long id) {
        Optional<Presentation> existingPresentation = presentationService.findById(id);
        if (existingPresentation.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        presentationService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
