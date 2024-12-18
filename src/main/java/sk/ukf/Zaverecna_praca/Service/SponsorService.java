package sk.ukf.Zaverecna_praca.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.ukf.Zaverecna_praca.Entity.Sponsor;
import sk.ukf.Zaverecna_praca.Repository.ConferenceRepository;
import sk.ukf.Zaverecna_praca.Repository.SponsorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SponsorService {

    @Autowired
    private SponsorRepository sponsorRepository;

    public List<Sponsor> findAll(){
        return sponsorRepository.findAll();
    }

    public Optional<Sponsor> findById(Long id){
        return sponsorRepository.findById(id);
    }

    public void save(Sponsor sponsor){
        sponsorRepository.save(sponsor);
    }

    public void deleteById(Long id){
        sponsorRepository.deleteById(id);
    }
}
