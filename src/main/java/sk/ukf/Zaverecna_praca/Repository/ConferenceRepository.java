package sk.ukf.Zaverecna_praca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.ukf.Zaverecna_praca.Entity.Conference;

public interface ConferenceRepository extends JpaRepository<Conference, Long> {
}