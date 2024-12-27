package sk.ukf.Zaverecna_praca.RESTControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.ukf.Zaverecna_praca.Entity.PresentationsHasParticipants;
import sk.ukf.Zaverecna_praca.Service.PresentationsHasParticipantsService;

import java.util.List;

@RestController
@RequestMapping("api/presentation_has_participants")
public class PresentationsHasParticipantsRestController {

    @Autowired
    private PresentationsHasParticipantsService presentationService;

    @GetMapping
    public ResponseEntity<List<PresentationsHasParticipants>> getAllPresentations(){
        List<PresentationsHasParticipants> presentations = presentationService.findAll();
        return ResponseEntity.ok(presentations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PresentationsHasParticipants> getPresentationById(@PathVariable Long id){
        return presentationService.findById(id).map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Participant with id " + id + " not found"));
    }

    @GetMapping("/{presentationId}/{userId}")
    public ResponseEntity<Boolean> isUserRegisteredForPresentation(@PathVariable Long presentationId, @PathVariable Long userId) {
        boolean isRegistered = presentationService.isUserRegisteredForPresentation(presentationId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(isRegistered);
    }
}
