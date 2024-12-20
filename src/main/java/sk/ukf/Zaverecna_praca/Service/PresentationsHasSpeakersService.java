package sk.ukf.Zaverecna_praca.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.ukf.Zaverecna_praca.Entity.PresentationsHasSpeakers;
import sk.ukf.Zaverecna_praca.Repository.PresentationsHasSpeakersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PresentationsHasSpeakersService {

    @Autowired
    private PresentationsHasSpeakersRepository presentationRepository;

    public List<PresentationsHasSpeakers> findaAll(){
        return presentationRepository.findAll();
    }

    public Optional<PresentationsHasSpeakers> findById(Long id){
        return presentationRepository.findById(id);
    }
}
