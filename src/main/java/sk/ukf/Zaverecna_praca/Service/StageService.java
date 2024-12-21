package sk.ukf.Zaverecna_praca.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.ukf.Zaverecna_praca.Entity.Conference;
import sk.ukf.Zaverecna_praca.Entity.Stage;
import sk.ukf.Zaverecna_praca.Repository.StageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StageService {

    @Autowired
    private StageRepository stageRepository;
    @Autowired
    private ConferenceService conferenceService;

    public List<Stage> getAllStages() {
        return stageRepository.findAll();
    }

    public Optional<Stage> findStageById(Long id) {
        return stageRepository.findById(id);
    }

    public Stage createStage(Stage stage) {
        Optional<Conference> conferenceOptional = conferenceService.findById(stage.getConference().getId());
        if (conferenceOptional.isEmpty()) {
            throw new IllegalArgumentException("Conference with id " + stage.getConference().getId() + " not found");
        }

        Stage newStage = new Stage();
        newStage.setNameOfStage(stage.getNameOfStage());
        newStage.setComment(stage.getComment());
        newStage.setConference(conferenceOptional.get());

        return stageRepository.save(newStage);
    }

    public Optional<Stage> updateStage(Long id, Stage updatedStage) {
        Optional<Stage> existingStageOpt = stageRepository.findById(id);
        if (existingStageOpt.isEmpty()) {
            return Optional.empty();
        }

        Stage existingStage = existingStageOpt.get();
        existingStage.setNameOfStage(updatedStage.getNameOfStage());
        existingStage.setComment(updatedStage.getComment());

        // Only update the conference if it's provided in the request
        if (updatedStage.getConference() != null && updatedStage.getConference().getId() != null) {
            Optional<Conference> conferenceOptional = conferenceService.findById(updatedStage.getConference().getId());
            if (conferenceOptional.isEmpty()) {
                throw new IllegalArgumentException("Conference with id " + updatedStage.getConference().getId() + " not found");
            }
            existingStage.setConference(conferenceOptional.get());
        }

        return Optional.of(stageRepository.save(existingStage));
    }

    public void deleteById(Long id) {
        stageRepository.deleteById(id);
    }
}