package sk.ukf.Zaverecna_praca.RESTControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.ukf.Zaverecna_praca.Entity.Conference;
import sk.ukf.Zaverecna_praca.Entity.Stage;
import sk.ukf.Zaverecna_praca.Service.ConferenceService;
import sk.ukf.Zaverecna_praca.Service.StageService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/stages")
public class StageController {

    @Autowired
    private StageService stageService;
    @Autowired
    private ConferenceService conferenceService;

    @GetMapping
    public ResponseEntity<List<Stage>> getAllStages(){
        List<Stage> stages = stageService.getAllStages();
        return ResponseEntity.ok(stages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stage> getStageById(@PathVariable Long id) {
        Optional<Stage> stage = stageService.getStageById(id);
        return stage.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //make post maping we will input nameOfStage, comment and conference
    @PostMapping
    public ResponseEntity<Stage> createStage(@RequestBody Stage stage) {
        if (stage.getConference() == null || stage.getConference().getId() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Optional<Conference> conferenceOptional = conferenceService.findById(stage.getConference().getId());
        if (conferenceOptional.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        stage.setConference(conferenceOptional.get());

        Stage newStage = new Stage();
        newStage.setNameOfStage(stage.getNameOfStage());
        newStage.setComment(stage.getComment());
        newStage.setConference(conferenceOptional.get());
        stageService.save(newStage);
        return ResponseEntity.ok(newStage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stage> updateStage(@PathVariable Long id, @RequestBody Stage updatedStage) {
        Optional<Stage> existingStage = stageService.getStageById(id);

        if (existingStage.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (updatedStage.getConference() != null && updatedStage.getConference().getId() != null) {
            Optional<Conference> conferenceOptional = conferenceService.findById(updatedStage.getConference().getId());
            if (conferenceOptional.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            updatedStage.setConference(conferenceOptional.get());
        }

        Stage stageToUpdate = existingStage.get();
        stageToUpdate.setNameOfStage(updatedStage.getNameOfStage());
        stageToUpdate.setComment(updatedStage.getComment());
        stageToUpdate.setConference(updatedStage.getConference());

        stageService.save(stageToUpdate);
        return ResponseEntity.ok(stageToUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStage(@PathVariable Long id) {
        Optional<Stage> existingStage = stageService.getStageById(id);
        if (existingStage.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        stageService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
