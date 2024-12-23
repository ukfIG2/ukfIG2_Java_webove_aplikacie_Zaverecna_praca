package sk.ukf.Zaverecna_praca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sk.ukf.Zaverecna_praca.Entity.Sponsor;

import java.util.List;

public interface SponsorRepository extends JpaRepository<Sponsor, Long> {

    @Query("SELECT c.nameOfConference, c.dateOfConference, c.stateOfConference, s.nameOfSponsor, s.url, s.image FROM Sponsor s " +
            "JOIN s.sponsorHasConference sc " +
            "JOIN sc.conference c " +
            "WHERE c.id = :conferenceId")
    List<Object[]> findSponsorsByConferenceId(@Param("conferenceId") Long conferenceId);


}
