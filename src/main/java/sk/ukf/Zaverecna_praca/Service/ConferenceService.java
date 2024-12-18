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

    public List<Conference> findAll(){
        return conferenceRepository.findAll();
    }

    public Optional<Conference> findById(Long id) {
        return conferenceRepository.findById(id);
    }

    //make function for inserting
    public void save(Conference conference) {
        conferenceRepository.save(conference);
    }

    //make function for deleting
    public void deleteById(Long id) {
        conferenceRepository.deleteById(id);
    }

}
