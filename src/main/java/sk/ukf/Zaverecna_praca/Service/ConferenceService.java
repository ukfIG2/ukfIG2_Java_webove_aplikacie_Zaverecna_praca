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
        Map<String, StageDTO> stagesMap = new LinkedHashMap<>();

        for (Object[] row : results) {
            // Extract row data
            String conferenceName = (String) row[0];
            java.sql.Date sqlDate = (java.sql.Date) row[1];
            LocalDate conferenceDate = sqlDate.toLocalDate();
            String conferenceState = (String) row[2];
            String stageName = (String) row[3];
            String presentationName = (String) row[4];

            // Handle LocalTime conversions
            java.sql.Time sqlStartTime = (java.sql.Time) row[5];
            LocalTime startAt = sqlStartTime.toLocalTime();

            java.sql.Time sqlEndTime = (java.sql.Time) row[6];
            LocalTime endAt = sqlEndTime.toLocalTime();

            String longDescription = (String) row[7];
            int capacity = (int) row[8];
            String titleBeforeName = (String) row[9];
            String firstName = (String) row[10];
            String lastName = (String) row[11];
            String titleAfterName = (String) row[12];
            String comment = (String) row[13];

            // Set top-level conference details
            conferenceDetails.setNameOfConference(conferenceName);
            conferenceDetails.setDateOfConference(conferenceDate);
            conferenceDetails.setStateOfConference(conferenceState);

            // Process stages
            StageDTO stage = stagesMap.computeIfAbsent(stageName, name -> new StageDTO(name, new ArrayList<>()));

            // Process presentations
            PresentationDTO presentation = stage.getPresentations().stream()
                    .filter(p -> p.getNameOfPresentation().equals(presentationName))
                    .findFirst()
                    .orElseGet(() -> {
                        PresentationDTO newPresentation = new PresentationDTO(presentationName, startAt, endAt, longDescription, capacity, new ArrayList<>());
                        stage.getPresentations().add(newPresentation);
                        return newPresentation;
                    });

            // Add users to presentations
            UserDTO user = new UserDTO(titleBeforeName, firstName, lastName, titleAfterName, comment);
            presentation.getUsers().add(user);
        }

        // Add stages to conference details
        conferenceDetails.setStages(new ArrayList<>(stagesMap.values()));

        return Optional.of(conferenceDetails);
    }
    ////custom
}
