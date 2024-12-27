package sk.ukf.Zaverecna_praca.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.ukf.Zaverecna_praca.DTOs.ConferenceDetail.*;
import sk.ukf.Zaverecna_praca.Entity.Conference;
import sk.ukf.Zaverecna_praca.Repository.ConferenceRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class ConferenceService {

    @Autowired
    private ConferenceRepository conferenceRepository;

    public Conference createConference(Conference conference) {
        Conference newConference = new Conference();
        newConference.setNameOfConference(conference.getNameOfConference());
        newConference.setDateOfConference(conference.getDateOfConference());
        newConference.setComment(conference.getComment());
        // Set other fields to their default values or handle them as needed

        return conferenceRepository.save(newConference);
    }

    public Optional<Conference> updateConference(Long id, Conference updatedConference) {
        Optional<Conference> conferenceOptional = conferenceRepository.findById(id);
        if (conferenceOptional.isEmpty()) {
            return Optional.empty();
        }

        Conference conference = conferenceOptional.get();
        conference.setNameOfConference(updatedConference.getNameOfConference());
        conference.setDateOfConference(updatedConference.getDateOfConference());
        conference.setComment(updatedConference.getComment());
        // Update other fields as needed

        return Optional.of(conferenceRepository.save(conference));
    }

    public Optional<Conference> findById(Long id) {
        return conferenceRepository.findById(id);
    }

    public List<Conference> findAll() {
        return conferenceRepository.findAll();
    }

    public void deleteById(Long id) {
        conferenceRepository.deleteById(id);
    }
    ////custom
    public Optional<ConferenceDTO> getConferenceDetails(Long conferenceId) {
        List<Object[]> results = conferenceRepository.findConferenceDetails(conferenceId);

        if (results.isEmpty()) {
            return Optional.empty();
        }

        ConferenceDTO conferenceDetails = new ConferenceDTO();
        Map<Long, StageDTO> stagesMap = new LinkedHashMap<>();

        for (Object[] row : results) {
            // Extract row data

            System.out.println(Arrays.toString(row));

            Long conferenceIdFromRow = ((Number) row[0]).longValue();
            String conferenceName = (String) row[1];
            java.sql.Date sqlDate = (java.sql.Date) row[2];
            LocalDate conferenceDate = sqlDate.toLocalDate();
            String conferenceState = (String) row[3];
            Long stageIdFromRow = ((Number) row[4]).longValue();
            String stageName = (String) row[5];
            Long presentationIdFromRow = ((Number) row[6]).longValue();
            String presentationName = (String) row[7];

            // Handle LocalTime conversions
            java.sql.Time sqlStartTime = (java.sql.Time) row[8];
            LocalTime startAt = sqlStartTime.toLocalTime();

            java.sql.Time sqlEndTime = (java.sql.Time) row[9];
            LocalTime endAt = sqlEndTime.toLocalTime();

            String longDescription = (String) row[10];
            int capacity = (int) row[11];

            // Handle potential null values for speaker details
            Long speakerIdFromRow = row[12] != null ? ((Number) row[12]).longValue() : null;
            String titleBeforeName = row[13] != null ? row[13].toString() : null;
            String firstName = row[14] != null ? row[14].toString() : null;
            String lastName = row[15] != null ? row[15].toString() : null;
            String titleAfterName = row[16] != null ? row[16].toString() : null;
            String comment = row[17] != null ? row[17].toString() : null;

            // Set top-level conference details
            conferenceDetails.setConferenceID(conferenceIdFromRow);
            conferenceDetails.setNameOfConference(conferenceName);
            conferenceDetails.setDateOfConference(conferenceDate);
            conferenceDetails.setStateOfConference(conferenceState);

            // Process stages
            StageDTO stage = stagesMap.computeIfAbsent(stageIdFromRow, id -> new StageDTO(id, stageName, new ArrayList<>()));

            // Process presentations
            PresentationDTO presentation = stage.getPresentations().stream()
                    .filter(p -> p.getPresentationID().equals(presentationIdFromRow))
                    .findFirst()
                    .orElseGet(() -> {
                        PresentationDTO newPresentation = new PresentationDTO(presentationIdFromRow, presentationName, startAt, endAt, longDescription, capacity, null);
                        stage.getPresentations().add(newPresentation);
                        return newPresentation;
                    });

            // Add users to presentations if speakerId is not null
            if (speakerIdFromRow != null) {
                if (presentation.getUsers() == null) {
                    presentation.setUsers(new ArrayList<>());
                }
                UserDTO user = new UserDTO(speakerIdFromRow, titleBeforeName, firstName, lastName, titleAfterName, comment);
                presentation.getUsers().add(user);
            }
        }

        // Add stages to conference details
        conferenceDetails.setStages(new ArrayList<>(stagesMap.values()));

        return Optional.of(conferenceDetails);
    }
    ////custom
}
