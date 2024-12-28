package sk.ukf.Zaverecna_praca.MVCControllers.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sk.ukf.Zaverecna_praca.Entity.Conference;
import sk.ukf.Zaverecna_praca.Entity.Presentation;
import sk.ukf.Zaverecna_praca.Entity.Stage;
import sk.ukf.Zaverecna_praca.Service.ConferenceService;
import sk.ukf.Zaverecna_praca.Service.PresentationService;
import sk.ukf.Zaverecna_praca.Service.StageService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/MVC/admin/presentations")
public class Admin_PresentationMVCController {

    @Autowired
    private PresentationService presentationService;

    @Autowired
    private StageService stageService;

    @Autowired
    private ConferenceService conferenceService;

    // Show all presentations
    @GetMapping
    public String showAllPresentations(Model model) {
        List<Presentation> presentations = presentationService.getAllPresentations();
        model.addAttribute("presentations", presentations);
        return "Admin/Presentations/AdminPresentations";
    }

    // Create a new presentation
    @GetMapping("/create")
    public String createPresentationForm(Model model) {
        List<Stage> stages = stageService.getAllStages();
        List<Conference> conferences = conferenceService.findAll();
        model.addAttribute("stages", stages);
        model.addAttribute("conferences", conferences);
        model.addAttribute("presentation", new Presentation());
        return "Admin/Presentations/AdminPresentationCreate";
    }

    // Handle presentation creation
    @PostMapping("/create")
    public String createPresentation(@ModelAttribute Presentation presentation, Model model) {
        try {
            presentationService.createPresentation(presentation);
            return "redirect:/MVC/admin/presentations"; // redirect to the list of presentations after creating
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "Admin/Presentations/AdminPresentationCreate"; // stay on the create page if error occurs
        }
    }

    // Update an existing presentation
    @GetMapping("/update/{id}")
    public String updatePresentationForm(@PathVariable("id") Long id, Model model) {
        Optional<Presentation> presentationOpt = presentationService.findById(id);
        if (presentationOpt.isPresent()) {
            Presentation presentation = presentationOpt.get();
            List<Stage> stages = stageService.getAllStages();
            List<Conference> conferences = conferenceService.findAll();
            model.addAttribute("presentation", presentation);
            model.addAttribute("stages", stages);
            model.addAttribute("conferences", conferences);
            return "Admin/Presentations/AdminPresentationUpdate";
        } else {
            model.addAttribute("error", "Presentation not found");
            return "redirect:/MVC/admin/presentations"; // redirect to the list if not found
        }
    }

    // Handle presentation update
    @PostMapping("/update/{id}")
    public String updatePresentation(@PathVariable("id") Long id, @ModelAttribute Presentation updatedPresentation, Model model) {
        Optional<Presentation> updatedOpt = presentationService.updatePresentation(id, updatedPresentation);
        if (updatedOpt.isPresent()) {
            return "redirect:/MVC/admin/presentations"; // redirect to the list after updating
        } else {
            model.addAttribute("error", "Error updating presentation");
            return "Admin/Presentations/AdminPresentationUpdate"; // stay on the update page if error occurs
        }
    }

    // Delete a presentation
    @GetMapping("/delete/{id}")
    public String deletePresentation(@PathVariable("id") Long id) {
        presentationService.deleteById(id);
        return "redirect:/MVC/admin/presentations"; // redirect to the list after deleting
    }
}
