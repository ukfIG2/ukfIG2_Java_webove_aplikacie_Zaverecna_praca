package sk.ukf.Zaverecna_praca.MVCControllers.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sk.ukf.Zaverecna_praca.Entity.Conference;
import sk.ukf.Zaverecna_praca.Entity.Stage;
import sk.ukf.Zaverecna_praca.Service.ConferenceService;
import sk.ukf.Zaverecna_praca.Service.StageService;

import java.util.List;

@Controller
@RequestMapping("/MVC/admin/stages")
public class Admin_StageMVCController {

    @Autowired
    private StageService stageService;

    @Autowired
    private ConferenceService conferenceService;

    // Show all stages
    @GetMapping
    public String showAllStages(Model model) {
        List<Stage> stages = stageService.getAllStages();
        model.addAttribute("stages", stages);
        return "Admin/Stages/AdminStages";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("stage", new Stage());
        model.addAttribute("conferences", conferenceService.findAll());
        System.out.println(conferenceService.findAll());
        return "Admin/Stages/AdminStageCreate";
    }

    // Handle creation of a new stage
    @PostMapping("/create")
    public String createStage(@ModelAttribute Stage stage) {
        stageService.createStage(stage);
        return "redirect:/MVC/admin/stages";
    }

    // Show form for updating an existing stage
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        stageService.findStageById(id).ifPresent(stage -> model.addAttribute("stage", stage));
        model.addAttribute("conferences", conferenceService.findAll());
        return "Admin/Stages/AdminStageUpdate";
    }

    // Handle updating of an existing stage
    @PostMapping("/update/{id}")
    public String updateStage(@PathVariable Long id, @ModelAttribute Stage stage) {
        stageService.updateStage(id, stage);
        return "redirect:/MVC/admin/stages";
    }

    // Handle deletion of a stage
    @GetMapping("/delete/{id}")
    public String deleteStage(@PathVariable Long id) {
        stageService.deleteById(id);
        return "redirect:/MVC/admin/stages";
    }

}
