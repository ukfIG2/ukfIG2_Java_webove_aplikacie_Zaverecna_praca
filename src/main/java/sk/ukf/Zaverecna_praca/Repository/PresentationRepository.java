package sk.ukf.Zaverecna_praca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sk.ukf.Zaverecna_praca.Entity.Presentation;

import java.util.List;

public interface PresentationRepository extends JpaRepository<Presentation, Long> {

    @Query("SELECT p.id, p.nameOfPresentation, p.startAt, p.endAt, p.longDescription, p.capacity, " +
            "s.nameOfStage, c.nameOfConference, c.dateOfConference, c.stateOfConference " +
            "FROM Presentation p " +
            "JOIN p.stage s " +
            "JOIN s.conference c " +
            "WHERE c.id = :conferenceId")
    List<Object[]> findPresentationsByConferenceId(@Param("conferenceId") Long conferenceId);


}