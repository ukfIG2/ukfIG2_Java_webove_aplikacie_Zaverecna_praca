package sk.ukf.Zaverecna_praca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.ukf.Zaverecna_praca.entity.Conference;

public interface ConferenceRepository extends JpaRepository<Conference, Long> {
}
