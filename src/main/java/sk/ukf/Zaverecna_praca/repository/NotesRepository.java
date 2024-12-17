package sk.ukf.Zaverecna_praca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.ukf.Zaverecna_praca.entity.Notes;

public interface NotesRepository extends JpaRepository<Notes, Long> {
}
