package sk.ukf.Zaverecna_praca.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.ukf.Zaverecna_praca.Entity.Conference;
import sk.ukf.Zaverecna_praca.Entity.Sponsor;
import sk.ukf.Zaverecna_praca.Entity.SponsorHasConference;
import sk.ukf.Zaverecna_praca.Repository.SponsorHasConferenceRepository;
import sk.ukf.Zaverecna_praca.Repository.SponsorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SponsorHasConferenceService {

    @Autowired
    private SponsorHasConferenceRepository sponsorHasConferenceRepository;
    @Autowired
    private SponsorRepository sponsorRepository;
    @Autowired
    private ConferenceService conferenceService;

    public List<SponsorHasConference> findAll(){
        return sponsorHasConferenceRepository.findAll();
    }

    public Optional<SponsorHasConference> findById(Long id){
        return sponsorHasConferenceRepository.findById(id);
    }

    public void save(Long conferenceId, Long sponsorId){
    SponsorHasConference sponsorHasConference = new SponsorHasConference();

    Optional<Conference> conferenceOptional = conferenceService.findById(conferenceId);
    if (conferenceOptional.isPresent()) {
        sponsorHasConference.setConference(conferenceOptional.get());
    } else {
        throw new IllegalArgumentException("Conference with id " + conferenceId + " not found");
    }

    Optional<Sponsor> sponsorOptional = sponsorRepository.findById(sponsorId);
    if (sponsorOptional.isPresent()) {
        sponsorHasConference.setSponsor(sponsorOptional.get());
    } else {
        throw new IllegalArgumentException("Sponsor with id " + sponsorId + " not found");
    }

    sponsorHasConferenceRepository.save(sponsorHasConference);
}

    public void update(Long id, String comment){
    Optional<SponsorHasConference> sponsorHasConferenceOptional = sponsorHasConferenceRepository.findById(id);

    if (sponsorHasConferenceOptional.isPresent()) {
        SponsorHasConference sponsorHasConference = sponsorHasConferenceOptional.get();
        sponsorHasConference.setComment(comment);
        sponsorHasConferenceRepository.save(sponsorHasConference);
    } else {
        throw new IllegalArgumentException("SponsorHasConference with id " + id + " not found");
    }
}

    public void deleteById(Long id){
        sponsorHasConferenceRepository.deleteById(id);
    }
}
