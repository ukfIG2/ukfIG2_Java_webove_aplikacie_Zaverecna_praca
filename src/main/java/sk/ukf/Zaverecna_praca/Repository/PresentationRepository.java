package sk.ukf.Zaverecna_praca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.ukf.Zaverecna_praca.Entity.Presentation;

public interface PresentationRepository extends JpaRepository<Presentation, Long> {
}
