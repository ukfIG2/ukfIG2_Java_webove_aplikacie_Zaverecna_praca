package sk.ukf.Zaverecna_praca.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.ukf.Zaverecna_praca.Entity.Stage;
import sk.ukf.Zaverecna_praca.Repository.StageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StageService {

    @Autowired
    private StageRepository stageRepository;

    public List<Stage> getAllStages(){
        return stageRepository.findAll();
    }

    public Optional<Stage> getStageById(Long id){
        return stageRepository.findById(id);
    }

    public void save(Stage stage){
        stageRepository.save(stage);
    }

    public void deleteById(Long id){
        stageRepository.deleteById(id);
    }

}
