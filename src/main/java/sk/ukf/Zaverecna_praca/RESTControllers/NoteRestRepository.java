package sk.ukf.Zaverecna_praca.RESTControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.ukf.Zaverecna_praca.Entity.Note;
import sk.ukf.Zaverecna_praca.Service.NoteService;

import java.util.List;

@RestController
@RequestMapping("api/notes")
public class NoteRestRepository {

    @Autowired
    private NoteService noteService;

    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.findAll();
    }
}
