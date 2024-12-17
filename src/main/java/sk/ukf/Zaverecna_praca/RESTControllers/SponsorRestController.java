package sk.ukf.Zaverecna_praca.RESTControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.ukf.Zaverecna_praca.Entity.Sponsor;
import sk.ukf.Zaverecna_praca.Service.SponsorService;

import java.util.List;

@RestController
@RequestMapping("api/sponsors")
public class SponsorRestController {

    @Autowired
    private SponsorService sponsorService;

    @GetMapping
    public List<Sponsor> getAllSponsors(){
        return sponsorService.findAll();
    }


}
