package sk.ukf.Zaverecna_praca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sk.ukf.Zaverecna_praca.Entity.Conference;

import java.util.List;

public interface ConferenceRepository extends JpaRepository<Conference, Long> {

    @Query(value = """
    SELECT 
        c.id AS conferenceID,
        c.name_of_conference AS nameOfConference,
        c.date_of_conference AS dateOfConference,
        c.state_of_conference AS stateOfConference,
        s.id AS stageID,
        s.name_of_stage AS nameOfStage,
        p.id AS presentationID,
        p.name_of_presentation AS nameOfPresentation,
        p.start_at AS startAt,
        p.end_at AS endAt,
        p.long_description AS longDescription,
        p.capacity AS capacity,
        u.id AS userID,
        u.title_before_name AS titleBeforeName,
        u.first_name AS firstName,
        u.last_name AS lastName,
        u.title_after_name AS titleAfterName,
        u.comment AS comment
    FROM conferences c
    JOIN stages s ON s.conference_id = c.id
    JOIN presentations p ON p.stage_id = s.id
    JOIN presentations_has_speakers phs ON phs.presentation_id = p.id
    JOIN users u ON phs.user_id = u.id
    WHERE c.id = :conferenceId
    ORDER BY s.name_of_stage, p.start_at, u.last_name
""", nativeQuery = true)
    List<Object[]> findConferenceDetails(@Param("conferenceId") Long conferenceId);

}