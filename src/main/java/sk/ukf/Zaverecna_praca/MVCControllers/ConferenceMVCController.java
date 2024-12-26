package sk.ukf.Zaverecna_praca.MVCControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.ukf.Zaverecna_praca.DTOs.ConferenceDetail.ConferenceDTO;
import sk.ukf.Zaverecna_praca.Entity.Sponsor;
import sk.ukf.Zaverecna_praca.Service.ConferenceService;
import sk.ukf.Zaverecna_praca.Service.SponsorService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/MVC")
public class ConferenceMVCController {

    @Autowired
    private ConferenceService conferenceService;
    @Autowired
    private SponsorService sponsorService;

    // Display all conferences
    @GetMapping("/conferences")
    public String showAllConferences(Model model) {
        model.addAttribute("conferences", conferenceService.findAll());
        return "Public/Conferences"; // Thymeleaf template location for listing all conferences
    }

    // Display details for a specific conference
    @GetMapping("/conferences/{conferenceId}")
    public String showConferenceDetails(@PathVariable Long conferenceId, Model model) {
        // Fetch conference details
        Optional<ConferenceDTO> optionalConferenceDetails = conferenceService.getConferenceDetails(conferenceId);

        if (optionalConferenceDetails.isEmpty()) {
            // Handle case where conference details are not found
            model.addAttribute("error", "Conference details not found for ID: " + conferenceId);
            return "Public/Error"; // Thymeleaf template for error page
        }

        // Add conference details to the model
        model.addAttribute("conferenceDetails", optionalConferenceDetails.get());

        // Fetch sponsors for the conference and add to the model
        List<Sponsor> sponsors = sponsorService.getSponsorsByConferenceId(conferenceId);
        model.addAttribute("sponsors", sponsors);

        return "Public/ConferencesDetail"; // Thymeleaf template for displaying conference details
    }
}
