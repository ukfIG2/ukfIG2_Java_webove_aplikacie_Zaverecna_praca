package sk.ukf.Zaverecna_praca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.ukf.Zaverecna_praca.Entity.Presentation;
import sk.ukf.Zaverecna_praca.Entity.PresentationsHasSpeakers;
import sk.ukf.Zaverecna_praca.Entity.User;

public interface PresentationsHasSpeakersRepository extends JpaRepository<PresentationsHasSpeakers, Long> {

    PresentationsHasSpeakers findByUserAndPresentation(User user, Presentation presentation);

}
