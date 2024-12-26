package sk.ukf.Zaverecna_praca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sk.ukf.Zaverecna_praca.Entity.Presentation;

import java.util.List;

public interface PresentationRepository extends JpaRepository<Presentation, Long> {
}