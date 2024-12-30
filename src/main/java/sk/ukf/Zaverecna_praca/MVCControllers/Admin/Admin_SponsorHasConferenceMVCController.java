package sk.ukf.Zaverecna_praca.MVCControllers.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sk.ukf.Zaverecna_praca.Entity.Conference;
import sk.ukf.Zaverecna_praca.Entity.Sponsor;
import sk.ukf.Zaverecna_praca.Entity.SponsorHasConference;
import sk.ukf.Zaverecna_praca.Service.ConferenceService;
import sk.ukf.Zaverecna_praca.Service.SponsorHasConferenceService;
import sk.ukf.Zaverecna_praca.Service.SponsorService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/MVC/admin/sponsorshasconferences")
public class Admin_SponsorHasConferenceMVCController {

    @Autowired
    private SponsorHasConferenceService sponsorHasConferenceService;

    @Autowired
    private SponsorService sponsorService;

    @Autowired
    private ConferenceService conferenceService;

    // Display all sponsor-conference relationships
    @GetMapping
    public String showAllSponsorHasConferences(Model model) {
        List<SponsorHasConference> sponsorHasConferences = sponsorHasConferenceService.findAll();
        model.addAttribute("sponsorHasConferences", sponsorHasConferences);
        return "Admin/SponsorHasConferences/SponsorHasConferences";
    }

    // Display the form to create a new sponsor-conference relationship
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("sponsorHasConference", new SponsorHasConference());
        model.addAttribute("sponsors", sponsorService.findAll());
        model.addAttribute("conferences", conferenceService.findAll());
        return "Admin/SponsorHasConferences/SponsorHasConferenceCreate";
    }

    // Handle the creation of a new sponsor-conference relationship
    @PostMapping("/create")
    public String createSponsorHasConference(@ModelAttribute SponsorHasConference sponsorHasConference) {
        sponsorHasConferenceService.create(sponsorHasConference);
        return "redirect:/MVC/admin/sponsorshasconferences";
    }

    // Display the form to update a sponsor-conference relationship
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Optional<SponsorHasConference> sponsorHasConferenceOpt = sponsorHasConferenceService.findById(id);
        if (sponsorHasConferenceOpt.isEmpty()) {
            return "redirect:/MVC/admin/sponsorshasconferences";
        }

        model.addAttribute("sponsorHasConference", sponsorHasConferenceOpt.get());
        return "Admin/SponsorHasConferences/SponsorHasConferenceUpdate";
    }

    // Handle the update of a sponsor-conference relationship
    @PostMapping("/update/{id}")
    public String updateSponsorHasConference(@PathVariable Long id, @RequestParam String comment) {
        sponsorHasConferenceService.update(id, comment);
        return "redirect:/MVC/admin/sponsorshasconferences";
    }

    // Handle deletion of a sponsor-conference relationship
    @PostMapping("/delete/{id}")
    public String deleteSponsorHasConference(@PathVariable Long id) {
        sponsorHasConferenceService.deleteById(id);
        return "redirect:/MVC/admin/sponsorshasconferences";
    }
}
