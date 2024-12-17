package sk.ukf.Zaverecna_praca.RESTcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.ukf.Zaverecna_praca.entity.Notes;
import sk.ukf.Zaverecna_praca.service.NotesService;

import java.util.List;

@RestController
@RequestMapping("api/notes")
public class NotesRestController {

    @Autowired
    private NotesService notesService;

    @GetMapping
    public List<Notes> getAllNotes() {
        return notesService.findAll();
    }
}
