package sk.ukf.Zaverecna_praca.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.ukf.Zaverecna_praca.Entity.Presentation;
import sk.ukf.Zaverecna_praca.Repository.PresentationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PresentationService {

    @Autowired
    private PresentationRepository presentationRepository;

    public List<Presentation> getAllPresentations() {
        return presentationRepository.findAll();
    }

    public Optional<Presentation> findById(Long id) {
        return presentationRepository.findById(id);
    }

    public void save(Presentation presentation) {
        presentationRepository.save(presentation);
    }

    public void deleteById(Long id) {
        presentationRepository.deleteById(id);
    }
}
