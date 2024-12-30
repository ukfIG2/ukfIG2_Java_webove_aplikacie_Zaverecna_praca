package sk.ukf.Zaverecna_praca.MVCControllers.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.ukf.Zaverecna_praca.Service.PresentationsHasSpeakersService;

@Controller
@RequestMapping("/MVC/admin/presentationhasspeakers")
public class Admin_PresentationHasSpeakersMVCController {

    @Autowired
    private PresentationsHasSpeakersService presentationsHasSpeakersService;

    @GetMapping
    public String getAllSpeakers(Model model) {
        model.addAttribute("speakers", presentationsHasSpeakersService.findaAll());
        return "Admin/PresentationHasSpeakers/PresentationHasSpeakers";
    }
}

