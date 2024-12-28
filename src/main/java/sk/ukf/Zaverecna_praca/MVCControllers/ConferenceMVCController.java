package sk.ukf.Zaverecna_praca.MVCControllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/presentations/register/{presentationId}")
    public String registerForPresentation(@PathVariable Long presentationId, HttpServletRequest request) {
        // Get the current logged-in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findUserByEmail(username);

        if (user != null) {
            relationshipService.addUserToPresentation(user.getId(), presentationId);
        }

        // Get the referrer URL (the URL where the user came from)
        String referer = request.getHeader("Referer");

        // Redirect to the page where the request came from
        return "redirect:" + referer;
    }

    @PostMapping("/presentations/unregister/{presentationId}")
    public String unregisterForPresentation(@PathVariable Long presentationId, HttpServletRequest request) {
        // Get the current logged-in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findUserByEmail(username);

        if (user != null) {
            relationshipService.deleteUserFromPresentation(user.getId(), presentationId);
        }

        // Get the referrer URL (the page where the user came from)
        String referer = request.getHeader("Referer");

        // Fallback to /MVC/conferences if the referrer is not available
        if (referer == null || referer.isEmpty()) {
            referer = "/MVC/conferences";
        }

        // Redirect to the page where the request originated
        return "redirect:" + referer;
    }

}
