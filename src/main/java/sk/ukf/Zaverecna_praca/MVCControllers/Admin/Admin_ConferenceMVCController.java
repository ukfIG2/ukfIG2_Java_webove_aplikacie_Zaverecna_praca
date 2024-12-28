package sk.ukf.Zaverecna_praca.MVCControllers.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sk.ukf.Zaverecna_praca.Entity.Conference;
import sk.ukf.Zaverecna_praca.Service.ConferenceService;

import java.util.List;

@Controller
@RequestMapping("/MVC/admin/conferences")
public class Admin_ConferenceMVCController {

    @Autowired
    private ConferenceService conferenceService;

    // Show all conferences
    @GetMapping
    public String showAllConferences(Model model) {
        List<Conference> conferences = conferenceService.findAll();
        model.addAttribute("conferences", conferences);
        return "Admin/Conferences/AdminConferences";
    }

    // Show form for creating a new conference
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("conference", new Conference());
        return "Admin/Conferences/AdminConferenceCreate";
    }

    // Handle the creation of a new conference
    @PostMapping("/create")
    public String createConference(@ModelAttribute Conference conference) {
        conferenceService.createConference(conference);
        return "redirect:/MVC/admin/conferences";
    }

    // Show form for updating an existing conference
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        conferenceService.findById(id).ifPresent(conference -> model.addAttribute("conference", conference));
        return "Admin/Conferences/AdminConferenceUpdate";
    }

    // Handle the update of a conference
    @PostMapping("/update/{id}")
    public String updateConference(@PathVariable Long id, @ModelAttribute Conference conference) {
        conferenceService.updateConference(id, conference);
        return "redirect:/MVC/admin/conferences";
    }

    // Handle the deletion of a conference
    @GetMapping("/delete/{id}")
    public String deleteConference(@PathVariable Long id) {
        conferenceService.deleteById(id);
        return "redirect:/MVC/admin/conferences";
    }
}


