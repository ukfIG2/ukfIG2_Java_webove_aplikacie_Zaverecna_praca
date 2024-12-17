package sk.ukf.Zaverecna_praca.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.ukf.Zaverecna_praca.Entity.Sponsor;
import sk.ukf.Zaverecna_praca.Repository.ConferenceRepository;
import sk.ukf.Zaverecna_praca.Repository.SponsorRepository;

import java.util.List;

@Service
public class SponsorService {

    @Autowired
    private SponsorRepository sponsorRepository;

    public List<Sponsor> findAll(){
        return sponsorRepository.findAll();
    }
}
