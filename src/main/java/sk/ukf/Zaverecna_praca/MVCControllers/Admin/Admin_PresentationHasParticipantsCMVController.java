package sk.ukf.Zaverecna_praca.MVCControllers.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sk.ukf.Zaverecna_praca.Entity.PresentationsHasParticipants;
import sk.ukf.Zaverecna_praca.Service.PresentationService;
import sk.ukf.Zaverecna_praca.Service.PresentationsHasParticipantsService;
import sk.ukf.Zaverecna_praca.Service.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/MVC/admin/presentationhasparticipants")
public class Admin_PresentationHasParticipantsCMVController {

    @Autowired
    private PresentationsHasParticipantsService presentationsHasParticipantsService;

    @Autowired
    private PresentationService presentationsService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllParticipants(Model model) {
        model.addAttribute("participants", presentationsHasParticipantsService.findAll());
        return "Admin/PresentationHasParticipants/PresentationHasParticipants";
    }
/*
    @GetMapping("/create")
    public String createParticipantForm(Model model) {
        model.addAttribute("participant", new PresentationsHasParticipants());
        model.addAttribute("presentations", presentationsService.getAllPresentations()); // For dropdown
        model.addAttribute("users", userService.findAll()); // For dropdown
        return "Admin/PresentationHasParticipants/PresentationHasParticipantCreate";
    }

    @PostMapping("/create")
    public String createParticipant(@ModelAttribute("participant") PresentationsHasParticipants participant) {
        presentationsHasParticipantsService.save(participant);
        return "redirect:/MVC/admin/presentationhasparticipants";
    }

    @GetMapping("/update/{id}")
    public String updateParticipantForm(@PathVariable Long id, Model model) {
        Optional<PresentationsHasParticipants> participantOptional = presentationsHasParticipantsService.findById(id);
        if (participantOptional.isEmpty()) {
            return "redirect:/MVC/admin/presentationhasparticipants"; // Redirect if not found
        }
        model.addAttribute("participant", participantOptional.get());
        model.addAttribute("presentations", presentationsService.getAllPresentations()); // For dropdown
        model.addAttribute("users", userService.findAll()); // For dropdown
        return "Admin/PresentationHasParticipants/PresentationHasParticipantUpdate";
    }

    @PostMapping("/update/{id}")
    public String updateParticipant(@PathVariable Long id, @ModelAttribute("participant") PresentationsHasParticipants participantDetails) {
        presentationsHasParticipantsService.update(id, participantDetails);
        return "redirect:/MVC/admin/presentationhasparticipants";
    }

    @GetMapping("/delete/{id}")
    public String deleteParticipant(@PathVariable Long id) {
        presentationsHasParticipantsService.deleteById(id);
        return "redirect:/MVC/admin/presentationhasparticipants";
    }*/
}

