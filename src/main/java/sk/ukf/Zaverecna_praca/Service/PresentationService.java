package sk.ukf.Zaverecna_praca.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.ukf.Zaverecna_praca.Entity.Presentation;
import sk.ukf.Zaverecna_praca.Entity.Stage;
import sk.ukf.Zaverecna_praca.Repository.PresentationRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PresentationService {

    @Autowired
    private PresentationRepository presentationRepository;
    @Autowired
    private StageService stageService;

    public List<Presentation> getAllPresentations() {
        return presentationRepository.findAll();
    }

    public Optional<Presentation> findById(Long id) {
        return presentationRepository.findById(id);
    }

    public Presentation createPresentation(Presentation presentation) {
        Optional<Stage> stageOptional = stageService.findStageById(presentation.getStage().getId());
        if (stageOptional.isEmpty()) {
            throw new IllegalArgumentException("Stage with id " + presentation.getStage().getId() + " not found");
        }

        Presentation newPresentation = new Presentation();
        newPresentation.setNameOfPresentation(presentation.getNameOfPresentation());
        newPresentation.setStartAt(presentation.getStartAt());
        newPresentation.setEndAt(presentation.getEndAt());
        newPresentation.setShortDescription(presentation.getShortDescription());
        newPresentation.setLongDescription(presentation.getLongDescription());
        newPresentation.setCapacity(presentation.getCapacity());
        newPresentation.setStage(stageOptional.get());
        newPresentation.setComment(presentation.getComment());

        return presentationRepository.save(newPresentation);
    }

    public Optional<Presentation> updatePresentation(Long id, Presentation updatedPresentation) {
        Optional<Presentation> existingPresentationOpt = presentationRepository.findById(id);
        if (existingPresentationOpt.isEmpty()) {
            return Optional.empty();
        }

        Presentation existingPresentation = existingPresentationOpt.get();
        existingPresentation.setNameOfPresentation(updatedPresentation.getNameOfPresentation());
        existingPresentation.setStartAt(updatedPresentation.getStartAt());
        existingPresentation.setEndAt(updatedPresentation.getEndAt());
        existingPresentation.setShortDescription(updatedPresentation.getShortDescription());
        existingPresentation.setLongDescription(updatedPresentation.getLongDescription());
        existingPresentation.setCapacity(updatedPresentation.getCapacity());

        // Only update the stage if it's provided in the request
        if (updatedPresentation.getStage() != null && updatedPresentation.getStage().getId() != null) {
            Optional<Stage> stageOptional = stageService.findStageById(updatedPresentation.getStage().getId());
            if (stageOptional.isEmpty()) {
                throw new IllegalArgumentException("Stage with id " + updatedPresentation.getStage().getId() + " not found");
            }
            existingPresentation.setStage(stageOptional.get());
        }

        existingPresentation.setComment(updatedPresentation.getComment());

        return Optional.of(presentationRepository.save(existingPresentation));
    }

    public void deleteById(Long id) {
        presentationRepository.deleteById(id);
    }
}