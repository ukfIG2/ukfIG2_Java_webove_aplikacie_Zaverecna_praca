package sk.ukf.Zaverecna_praca.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.ukf.Zaverecna_praca.Entity.Conference;
import sk.ukf.Zaverecna_praca.Repository.ConferenceRepository;

import java.util.List;
import java.util.Optional;

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
}
