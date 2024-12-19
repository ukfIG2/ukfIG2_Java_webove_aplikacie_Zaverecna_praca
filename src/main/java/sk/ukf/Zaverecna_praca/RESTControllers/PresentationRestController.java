package sk.ukf.Zaverecna_praca.RESTControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.ukf.Zaverecna_praca.Entity.Presentation;
import sk.ukf.Zaverecna_praca.Entity.Stage;
import sk.ukf.Zaverecna_praca.Service.PresentationService;
import sk.ukf.Zaverecna_praca.Service.StageService;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/presentations")
public class PresentationRestController {

    @Autowired
    private PresentationService presentationService;
    @Autowired
    private StageService stageService;

    @GetMapping
    public ResponseEntity<List<Presentation>> getAllPresentations(){
        List<Presentation> presentations = presentationService.getAllPresentations();
        return ResponseEntity.ok(presentations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Presentation> getPresentationById(@PathVariable Long id){
        return presentationService.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Presentation> createPresentation(@RequestBody Presentation presentation){
        if (presentation.getStage() ==null || presentation.getStage().getId() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Optional<Stage> stageOptional = stageService.findStageById(presentation.getStage().getId());
        if(stageOptional.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        presentation.setStage(stageOptional.get());

        Presentation newPresentation = new Presentation();
        newPresentation.setNameOfPresentation(presentation.getNameOfPresentation());
        newPresentation.setStartAt(presentation.getStartAt());
        newPresentation.setEndAt(presentation.getEndAt());
        newPresentation.setShortDescription(presentation.getShortDescription());
        newPresentation.setLongDescription(presentation.getLongDescription());
        newPresentation.setCapacity(presentation.getCapacity());
        newPresentation.setStage(stageOptional.get());
        newPresentation.setComment(presentation.getComment());
        presentationService.save(newPresentation);
        return ResponseEntity.ok(newPresentation);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Presentation> updatePresentation(@PathVariable Long id, @RequestBody Presentation updatedPresentation) {
        Optional<Presentation> existingPresentation = presentationService.findById(id);

        if (existingPresentation.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (updatedPresentation.getStage() != null && updatedPresentation.getStage().getId() != null) {
            Optional<Stage> stageOptional = stageService.findStageById(updatedPresentation.getStage().getId());
            if (stageOptional.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            updatedPresentation.setStage(stageOptional.get());
        }

        Presentation presentationToUpdate = existingPresentation.get();
        presentationToUpdate.setNameOfPresentation(updatedPresentation.getNameOfPresentation());
        presentationToUpdate.setStartAt(updatedPresentation.getStartAt());
        presentationToUpdate.setEndAt(updatedPresentation.getEndAt());
        presentationToUpdate.setShortDescription(updatedPresentation.getShortDescription());
        presentationToUpdate.setLongDescription(updatedPresentation.getLongDescription());
        presentationToUpdate.setCapacity(updatedPresentation.getCapacity());
        presentationToUpdate.setStage(updatedPresentation.getStage());
        presentationToUpdate.setComment(updatedPresentation.getComment());

        presentationService.save(presentationToUpdate);
        return ResponseEntity.ok(presentationToUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePresentation(@PathVariable Long id) {
        Optional<Presentation> existingPresentation = presentationService.findById(id);

        if (existingPresentation.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        presentationService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
}
