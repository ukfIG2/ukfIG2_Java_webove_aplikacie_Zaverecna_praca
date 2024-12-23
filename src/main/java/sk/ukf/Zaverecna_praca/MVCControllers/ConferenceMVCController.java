package sk.ukf.Zaverecna_praca.MVCControllers;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.ukf.Zaverecna_praca.Entity.Conference;
import sk.ukf.Zaverecna_praca.Service.ConferenceService;
import sk.ukf.Zaverecna_praca.Service.PresentationService;
import sk.ukf.Zaverecna_praca.Service.SponsorService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/MVC")
public class ConferenceMVCController {

    @Autowired
    private ConferenceService conferenceService;
    @Autowired
    private PresentationService presentationService;
    @Autowired
    private SponsorService sponsorService;

    @GetMapping("/conferences")
    public String showAllConferences(Model model) {
        List<Conference> conferences = conferenceService.findAll();
        model.addAttribute("conferences", conferences);
        return "Public/Conferences"; // Thymeleaf template location
    }

    /*@GetMapping("/conferences/{id}")
    public String showConferenceById(@PathVariable Long id, Model model) {
        List<Object[]> presentations = presentationService.findPresentationsByConferenceId(id);
        List<Object[]> sponsors = sponsorService.findSponsorsByConferenceId(id);

        model.addAttribute("presentations", presentations);
        model.addAttribute("sponsors", sponsors);

        return "Public/ConferencesDetail"; // Thymeleaf template location
    }*/

    @GetMapping("/conferences/{id}")
    public String showConferenceById(@PathVariable Long id, Model model) {
        // Get the list of sponsors for the conference
        List<Object[]> sponsors = sponsorService.findSponsorsByConferenceId(id);

        // Get the grouped presentations by stage for the conference
        Map<String, List<Object[]>> groupedPresentations = presentationService.getPresentationsGroupedByStage(id);

        // Add both sponsors and grouped presentations to the model
        model.addAttribute("groupedPresentations", groupedPresentations);
        model.addAttribute("sponsors", sponsors);

        // Return the Thymeleaf template location
        return "Public/ConferencesDetail";
    }


}
