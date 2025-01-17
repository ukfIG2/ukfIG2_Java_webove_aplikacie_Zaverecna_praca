package sk.ukf.Zaverecna_praca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.ukf.Zaverecna_praca.Entity.Presentation;
import sk.ukf.Zaverecna_praca.Entity.PresentationsHasParticipants;
import sk.ukf.Zaverecna_praca.Entity.User;

public interface PresentationsHasParticipantsRepository extends JpaRepository<PresentationsHasParticipants, Long> {

    PresentationsHasParticipants findByUserAndPresentation(User user, Presentation presentation);
}
