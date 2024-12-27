package sk.ukf.Zaverecna_praca.MVCControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sk.ukf.Zaverecna_praca.DTOs.ConferenceDetail.ConferenceDTO;
import sk.ukf.Zaverecna_praca.DTOs.ConferenceDetail.PresentationDTO;
import sk.ukf.Zaverecna_praca.DTOs.ConferenceDetail.StageDTO;
import sk.ukf.Zaverecna_praca.Entity.Sponsor;
import sk.ukf.Zaverecna_praca.Service.ConferenceService;
import sk.ukf.Zaverecna_praca.Service.PresentationsHasParticipantsService;
import sk.ukf.Zaverecna_praca.Service.SponsorService;
import sk.ukf.Zaverecna_praca.Entity.User;
import sk.ukf.Zaverecna_praca.Repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/MVC")
public class ConferenceMVCController {

    @Autowired
    private ConferenceService conferenceService;
    @Autowired
    private SponsorService sponsorService;
    @Autowired
    private PresentationsHasParticipantsService presentationsHasParticipantsService;
    @Autowired
    private UserRepository userRepository;

    // Display all conferences
    @GetMapping("/conferences")
    public String showAllConferences(Model model) {
        model.addAttribute("conferences", conferenceService.findAll());
        return "Public/Conferences"; // Thymeleaf template location for listing all conferences
    }

    // Display details for a specific conference
    @GetMapping("/conferences/{conferenceId}")
    public String showConferenceDetails(@PathVariable Long conferenceId, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        // Fetch conference details
        Optional<ConferenceDTO> optionalConferenceDetails = conferenceService.getConferenceDetails(conferenceId);

        if (optionalConferenceDetails.isEmpty()) {
            // Handle case where conference details are not found
            model.addAttribute("error", "Conference details not found for ID: " + conferenceId);
            return "Public/Error"; // Thymeleaf template for error page
        }

        ConferenceDTO conferenceDetails = optionalConferenceDetails.get();
        model.addAttribute("conferenceDetails", conferenceDetails);

        // Fetch sponsors for the conference and add to the model
        List<Sponsor> sponsors = sponsorService.getSponsorsByConferenceId(conferenceId);
        model.addAttribute("sponsors", sponsors);

        // Check if the user is registered for each presentation
        if (userDetails != null) {
            User user = userRepository.findByEmail(userDetails.getUsername());
            if (user != null) {
                model.addAttribute("userId", user.getId());

                // Set the isUserRegisteredForPresentation attribute for each presentation
                for (StageDTO stage : conferenceDetails.getStages()) {
                    for (PresentationDTO presentation : stage.getPresentations()) {
                        boolean isRegistered = presentationsHasParticipantsService.isUserRegisteredForPresentation(presentation.getId(), user.getId());
                        presentation.setUserRegisteredForPresentation(isRegistered);
                    }
                }
            }
        }

        return "Public/ConferencesDetail"; // Thymeleaf template for displaying conference details
    }

    // Register for a presentation
    @PostMapping("/presentations/register/{presentationId}")
    public synchronized String registerForPresentation(@PathVariable Long presentationId, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails == null) {
            model.addAttribute("error", "You need to be logged in to register for the presentation.");
            return "redirect:/MVC/conferences/" + presentationId;
        }

        User user = userRepository.findByEmail(userDetails.getUsername());
        try {
            presentationsHasParticipantsService.registerUserForPresentation(presentationId, user.getId());
        } catch (IllegalStateException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/MVC/conferences/" + presentationId;
    }

    // Unregister from a presentation
    @PostMapping("/presentations/unregister/{presentationId}")
    public synchronized String unregisterFromPresentation(@PathVariable Long presentationId, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/MVC/conferences/" + presentationId;
        }

        User user = userRepository.findByEmail(userDetails.getUsername());
        presentationsHasParticipantsService.unregisterUserFromPresentation(presentationId, user.getId());
        return "redirect:/MVC/conferences/" + presentationId;
    }
}