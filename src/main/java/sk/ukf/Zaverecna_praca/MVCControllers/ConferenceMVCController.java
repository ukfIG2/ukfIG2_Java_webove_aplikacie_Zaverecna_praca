package sk.ukf.Zaverecna_praca.MVCControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.ukf.Zaverecna_praca.DTOs.ConferenceDetail.ConferenceDTO;
import sk.ukf.Zaverecna_praca.Entity.Sponsor;
import sk.ukf.Zaverecna_praca.Service.ConferenceService;
import sk.ukf.Zaverecna_praca.Service.RelationshipService;
import sk.ukf.Zaverecna_praca.Entity.User;
import sk.ukf.Zaverecna_praca.Service.SponsorService;
import sk.ukf.Zaverecna_praca.Service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/MVC")
public class ConferenceMVCController {

    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private RelationshipService relationshipService;

    @Autowired
    private UserService userService;

    @Autowired
    private SponsorService sponsorService;

    // Display all conferences
    @GetMapping("/conferences")
    public String showAllConferences(Model model) {
        model.addAttribute("conferences", conferenceService.findAll());
        return "Public/Conferences"; // Thymeleaf template location for listing all conferences
    }

    @GetMapping("/conferences/{conferenceId}")
    public String showConferenceDetails(@PathVariable Long conferenceId, Model model) {
        // Fetch conference details
        Optional<ConferenceDTO> optionalConferenceDetails = conferenceService.getConferenceDetails(conferenceId);

        if (optionalConferenceDetails.isEmpty()) {
            model.addAttribute("error", "Conference details not found for ID: " + conferenceId);
            return "Public/Error";
        }

        ConferenceDTO conferenceDetails = optionalConferenceDetails.get();
        model.addAttribute("conferenceDetails", conferenceDetails);

        // Fetch sponsors for the conference
        List<Sponsor> sponsors = sponsorService.getSponsorsByConferenceId(conferenceId);
        model.addAttribute("sponsors", sponsors);

        // Get user from security context
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findUserByEmail(username);

        if (user != null) {
            // Add user's role to the model
            model.addAttribute("role", user.getRole());

            // Check if the user is already registered for any presentations
            conferenceDetails.getStages().forEach(stage -> stage.getPresentations().forEach(presentation -> {
                boolean alreadyRegistered = relationshipService.isAlreadyRegistered(presentation.getPresentationID(), user.getId());
                presentation.setAlreadyRegistered(alreadyRegistered);
            }));
        }
        return "Public/ConferencesDetail";
    }



    @GetMapping("/presentations/register/{presentationId}")
    public String registerForPresentation(@PathVariable Long presentationId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findUserByEmail(username);

        if (user != null) {
            relationshipService.addUserToPresentation(user.getId(), presentationId);
        }

        return "redirect:/MVC/conferences";
    }

    @GetMapping("/presentations/unregister/{presentationId}")
    public String unregisterForPresentation(@PathVariable Long presentationId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findUserByEmail(username);

        if (user != null) {
            relationshipService.deleteUserFromPresentation(user.getId(), presentationId);
        }

        return "redirect:/MVC/conferences";
    }

}
