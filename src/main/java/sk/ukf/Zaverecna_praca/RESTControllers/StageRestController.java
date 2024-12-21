package sk.ukf.Zaverecna_praca.RESTControllers;

import jakarta.validation.Valid;
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
public class StageRestController {

    @Autowired
    private StageService stageService;

    @GetMapping
    public ResponseEntity<List<Stage>> getAllStages() {
        List<Stage> stages = stageService.getAllStages();
        return ResponseEntity.ok(stages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stage> getStageById(@PathVariable Long id) {
        Optional<Stage> stage = stageService.findStageById(id);
        return stage.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Stage> createStage(@RequestBody @Valid Stage stage) {
        Stage savedStage = stageService.createStage(stage);
        return ResponseEntity.ok(savedStage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stage> updateStage(@PathVariable Long id, @RequestBody @Valid Stage updatedStage) {
        Optional<Stage> updatedStageOpt = stageService.updateStage(id, updatedStage);
        return updatedStageOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStage(@PathVariable Long id) {
        Optional<Stage> existingStage = stageService.findStageById(id);
        if (existingStage.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        stageService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}