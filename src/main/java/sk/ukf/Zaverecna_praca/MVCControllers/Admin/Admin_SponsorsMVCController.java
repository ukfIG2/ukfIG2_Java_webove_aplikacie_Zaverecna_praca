package sk.ukf.Zaverecna_praca.MVCControllers.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sk.ukf.Zaverecna_praca.Entity.Sponsor;
import sk.ukf.Zaverecna_praca.Service.SponsorService;

import java.util.Optional;

@Controller
@RequestMapping("/MVC/admin/sponsors")
public class Admin_SponsorsMVCController {

    @Autowired
    private SponsorService sponsorService;

    // Display all sponsors
    @GetMapping
    public String showAllSponsors(Model model) {
        model.addAttribute("sponsors", sponsorService.findAll());
        return "Admin/Sponsors/AdminSponsors";
    }

    // Display the form to create a new sponsor
    @GetMapping("/create")
    public String showCreateSponsorForm(Model model) {
        model.addAttribute("sponsor", new Sponsor());
        return "Admin/Sponsors/AdminSponsorCreate";
    }

    // Handle the submission of a new sponsor
    @PostMapping("/create")
    public String createSponsor(@ModelAttribute Sponsor sponsor) {
        sponsorService.createSponsor(sponsor);
        return "redirect:/MVC/admin/sponsors";
    }

    // Display the form to update an existing sponsor
    @GetMapping("/update/{id}")
    public String showUpdateSponsorForm(@PathVariable Long id, Model model) {
        Optional<Sponsor> sponsorOptional = sponsorService.findById(id);
        if (sponsorOptional.isEmpty()) {
            return "redirect:/MVC/admin/sponsors"; // Redirect if sponsor not found
        }
        model.addAttribute("sponsor", sponsorOptional.get());
        return "Admin/Sponsors/AdminSponsorUpdate";
    }

    // Handle the submission of an updated sponsor
    @PostMapping("/update/{id}")
    public String updateSponsor(@PathVariable Long id, @ModelAttribute Sponsor sponsor) {
        sponsorService.updateSponsor(id, sponsor);
        return "redirect:/MVC/admin/sponsors";
    }

    // Delete a sponsor
    @PostMapping("/delete/{id}")
    public String deleteSponsor(@PathVariable Long id) {
        sponsorService.deleteById(id);
        return "redirect:/MVC/admin/sponsors";
    }
}

