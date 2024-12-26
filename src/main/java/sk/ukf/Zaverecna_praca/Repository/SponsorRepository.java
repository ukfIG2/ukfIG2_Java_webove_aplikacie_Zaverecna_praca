package sk.ukf.Zaverecna_praca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sk.ukf.Zaverecna_praca.Entity.Sponsor;

import java.util.List;

public interface SponsorRepository extends JpaRepository<Sponsor, Long> {

    @Query(value = """
    SELECT s.*
    FROM conferences c
    JOIN sponsors_has_conferences shc ON c.id = shc.conferences_id
    JOIN sponsors s ON shc.sponsors_id = s.id
    WHERE c.id = :conferenceId
    """, nativeQuery = true)
    List<Sponsor> findSponsorsByConferenceId(@Param("conferenceId") Long conferenceId);
}
