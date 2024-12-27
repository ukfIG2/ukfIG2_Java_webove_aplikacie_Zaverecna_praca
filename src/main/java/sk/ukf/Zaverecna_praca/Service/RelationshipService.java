package sk.ukf.Zaverecna_praca.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.ukf.Zaverecna_praca.Entity.Presentation;
import sk.ukf.Zaverecna_praca.Entity.PresentationsHasParticipants;
import sk.ukf.Zaverecna_praca.Entity.PresentationsHasSpeakers;
import sk.ukf.Zaverecna_praca.Entity.User;
import sk.ukf.Zaverecna_praca.Enums.Role;
import sk.ukf.Zaverecna_praca.Repository.PresentationRepository;
import sk.ukf.Zaverecna_praca.Repository.PresentationsHasParticipantsRepository;
import sk.ukf.Zaverecna_praca.Repository.PresentationsHasSpeakersRepository;
import sk.ukf.Zaverecna_praca.Repository.UserRepository;

@Service
public class RelationshipService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PresentationRepository presentationRepository;
    @Autowired
    private PresentationsHasParticipantsRepository presentationHasParticipantsRepository;
    @Autowired
    private PresentationsHasSpeakersRepository presentationHasSpeakersRepository;

    public void addUserToPresentation(Long userId, Long presentationId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Presentation presentation = presentationRepository.findById(presentationId).orElseThrow(() -> new EntityNotFoundException("Presentation not found"));

        if (user.getRole() == Role.ROLE_speaker) {
            PresentationsHasSpeakers speakerRelation = new PresentationsHasSpeakers();
            speakerRelation.setUser(user);
            speakerRelation.setPresentation(presentation);
            presentationHasSpeakersRepository.save(speakerRelation);
        } else if (user.getRole() == Role.ROLE_registered_visitor) {
            PresentationsHasParticipants participantRelation = new PresentationsHasParticipants();
            participantRelation.setUser(user);
            participantRelation.setPresentation(presentation);
            presentationHasParticipantsRepository.save(participantRelation);
        }
    }

    public void deleteUserFromPresentation(Long userId, Long presentationId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Presentation presentation = presentationRepository.findById(presentationId).orElseThrow(() -> new EntityNotFoundException("Presentation not found"));

        PresentationsHasSpeakers speakerRelation = presentationHasSpeakersRepository.findByUserAndPresentation(user, presentation);
        if (speakerRelation != null) {
            presentationHasSpeakersRepository.delete(speakerRelation);
        }

        PresentationsHasParticipants participantRelation = presentationHasParticipantsRepository.findByUserAndPresentation(user, presentation)
                .orElse(null);

        if (participantRelation != null) {
            presentationHasParticipantsRepository.delete(participantRelation);
        }
    }
}