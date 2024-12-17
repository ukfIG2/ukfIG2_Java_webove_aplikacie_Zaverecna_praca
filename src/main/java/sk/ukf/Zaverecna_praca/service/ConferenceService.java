package sk.ukf.Zaverecna_praca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.ukf.Zaverecna_praca.dto.ConferenceDTO;
import sk.ukf.Zaverecna_praca.entity.Conference;
import sk.ukf.Zaverecna_praca.repository.ConferenceRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConferenceService {

    @Autowired
    private ConferenceRepository conferenceRepository;

    /*public List<ConferenceDTO> findAll() {
        return conferenceRepository.findAll().stream()
                .map(Conference -> new ConferenceDTO(
                        Conference.getId(),
                        Conference.getNameOfConference(),
                        Conference.getDateOfConference(),
                        Conference.getStateOfConference(),
                        Conference.getComment(),
                        Conference.getCreatedAt(),
                        Conference.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }*/
    public List<Conference> findAll() {
        return conferenceRepository.findAll();
    }

public Optional<ConferenceDTO> findById(Long id) {
    return conferenceRepository.findById(id).map(Conference -> new ConferenceDTO(
            Conference.getId(),
            Conference.getNameOfConference(),
            Conference.getDateOfConference(),
            Conference.getStateOfConference(),
            Conference.getComment(),
            Conference.getCreatedAt(),
            Conference.getUpdatedAt()
    ));
}

    public ConferenceDTO save(Conference conference) {
        Conference savedConference = conferenceRepository.save(conference);
        return new ConferenceDTO(
                savedConference.getId(),
                savedConference.getNameOfConference(),
                savedConference.getDateOfConference(),
                savedConference.getStateOfConference(),
                savedConference.getComment(),
                savedConference.getCreatedAt(),
                savedConference.getUpdatedAt()
        );
    }

    public void deleteById(Long id) {
        conferenceRepository.deleteById(id);
    }

}
