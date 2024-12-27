package sk.ukf.Zaverecna_praca.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.ukf.Zaverecna_praca.Entity.Presentation;
import sk.ukf.Zaverecna_praca.Entity.PresentationsHasParticipants;
import sk.ukf.Zaverecna_praca.Entity.User;
import sk.ukf.Zaverecna_praca.Repository.PresentationRepository;
import sk.ukf.Zaverecna_praca.Repository.PresentationsHasParticipantsRepository;
import sk.ukf.Zaverecna_praca.Repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PresentationsHasParticipantsService {

    @Autowired
    private PresentationsHasParticipantsRepository presentationsHasParticipantsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PresentationRepository presentationRepository;

    public List<PresentationsHasParticipants> findAll() {
        return presentationsHasParticipantsRepository.findAll();
    }

    public Optional<PresentationsHasParticipants> findById(Long id) {
        return presentationsHasParticipantsRepository.findById(id);
    }

    public boolean isUserRegisteredForPresentation(Long presentationId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        Presentation presentation = presentationRepository.findById(presentationId).orElseThrow(() -> new IllegalArgumentException("Invalid presentation ID"));
        Optional<PresentationsHasParticipants> record = presentationsHasParticipantsRepository.findByUserAndPresentation(user, presentation);
        return record.isPresent();
    }

    public synchronized void registerUserForPresentation(Long presentationId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        Presentation presentation = presentationRepository.findById(presentationId).orElseThrow(() -> new IllegalArgumentException("Invalid presentation ID"));

        if (isUserRegisteredForPresentation(presentationId, userId)) {
            throw new IllegalStateException("User is already registered for this presentation");
        }

        PresentationsHasParticipants registration = new PresentationsHasParticipants();
        registration.setUser(user);
        registration.setPresentation(presentation);
        presentationsHasParticipantsRepository.save(registration);
    }

    public synchronized void unregisterUserFromPresentation(Long presentationId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        Presentation presentation = presentationRepository.findById(presentationId).orElseThrow(() -> new IllegalArgumentException("Invalid presentation ID"));

        PresentationsHasParticipants registration = presentationsHasParticipantsRepository.findByUserAndPresentation(user, presentation)
                .orElseThrow(() -> new IllegalStateException("User is not registered for this presentation"));
        presentationsHasParticipantsRepository.delete(registration);
    }
}