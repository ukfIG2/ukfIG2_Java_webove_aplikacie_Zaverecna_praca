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

    public List<SponsorHasConference> findAll() {
        return sponsorHasConferenceRepository.findAll();
    }

    public Optional<SponsorHasConference> findById(Long id) {
        return sponsorHasConferenceRepository.findById(id);
    }

    public SponsorHasConference create(SponsorHasConference sponsorHasConference) {
        SponsorHasConference newSponsorHasConference = new SponsorHasConference();

        Optional<Conference> conferenceOptional = conferenceService.findById(sponsorHasConference.getConference().getId());
        if (conferenceOptional.isPresent()) {
            newSponsorHasConference.setConference(conferenceOptional.get());
        } else {
            throw new IllegalArgumentException("Conference with id " + sponsorHasConference.getConference().getId() + " not found");
        }

        Optional<Sponsor> sponsorOptional = sponsorRepository.findById(sponsorHasConference.getSponsor().getId());
        if (sponsorOptional.isPresent()) {
            newSponsorHasConference.setSponsor(sponsorOptional.get());
        } else {
            throw new IllegalArgumentException("Sponsor with id " + sponsorHasConference.getSponsor().getId() + " not found");
        }

        newSponsorHasConference.setComment(sponsorHasConference.getComment());

        return sponsorHasConferenceRepository.save(newSponsorHasConference);
    }

    public Optional<SponsorHasConference> update(Long id, String comment) {
        Optional<SponsorHasConference> sponsorHasConferenceOptional = sponsorHasConferenceRepository.findById(id);

        if (sponsorHasConferenceOptional.isPresent()) {
            SponsorHasConference sponsorHasConference = sponsorHasConferenceOptional.get();
            sponsorHasConference.setComment(comment);
            return Optional.of(sponsorHasConferenceRepository.save(sponsorHasConference));
        } else {
            return Optional.empty();
        }
    }

    public void deleteById(Long id) {
        sponsorHasConferenceRepository.deleteById(id);
    }
}