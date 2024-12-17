package sk.ukf.Zaverecna_praca.RESTControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.ukf.Zaverecna_praca.Entity.Conference;
import sk.ukf.Zaverecna_praca.Service.ConferenceService;

import java.util.List;

@RestController
@RequestMapping("api/conferences")
public class ConferenceRestController {

    @Autowired
    private ConferenceService conferenceService;

    @GetMapping
    public List<Conference> getAllVonferences(){
        return conferenceService.findAll();
    }
}
