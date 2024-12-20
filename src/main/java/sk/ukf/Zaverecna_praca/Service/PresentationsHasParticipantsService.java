package sk.ukf.Zaverecna_praca.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.ukf.Zaverecna_praca.Entity.PresentationsHasParticipants;
import sk.ukf.Zaverecna_praca.Repository.PresentationsHasParticipantsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PresentationsHasParticipantsService {

    @Autowired
    private PresentationsHasParticipantsRepository presentationRepository;

    public List<PresentationsHasParticipants> findAll(){
        return presentationRepository.findAll();
    }

    public Optional<PresentationsHasParticipants> findById(Long id){
        return presentationRepository.findById(id);
    }
}
