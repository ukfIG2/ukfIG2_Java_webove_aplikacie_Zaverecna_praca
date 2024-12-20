package sk.ukf.Zaverecna_praca.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.ukf.Zaverecna_praca.Entity.Sponsor;
import sk.ukf.Zaverecna_praca.Repository.ConferenceRepository;
import sk.ukf.Zaverecna_praca.Repository.SponsorRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SponsorService {

    @Autowired
    private SponsorRepository sponsorRepository;

    public List<Sponsor> findAll() {
        return sponsorRepository.findAll();
    }

    public Optional<Sponsor> findById(Long id) {
        return sponsorRepository.findById(id);
    }

    public Sponsor createSponsor(Sponsor sponsor) {
        Sponsor newSponsor = new Sponsor();
        newSponsor.setNameOfSponsor(sponsor.getNameOfSponsor());
        newSponsor.setUrl(sponsor.getUrl());
        newSponsor.setImage(sponsor.getImage());
        newSponsor.setComment(sponsor.getComment());

        return sponsorRepository.save(newSponsor);
    }

    public Optional<Sponsor> updateSponsor(Long id, Sponsor sponsor) {
        Optional<Sponsor> existingSponsorOpt = sponsorRepository.findById(id);
        if (existingSponsorOpt.isEmpty()) {
            return Optional.empty();
        }

        Sponsor existingSponsor = existingSponsorOpt.get();

        existingSponsor.setNameOfSponsor(sponsor.getNameOfSponsor());
        existingSponsor.setUrl(sponsor.getUrl());
        existingSponsor.setImage(sponsor.getImage());
        existingSponsor.setComment(sponsor.getComment());

        return Optional.of(sponsorRepository.save(existingSponsor));
    }

    public void deleteById(Long id) {
        sponsorRepository.deleteById(id);
    }
}