package sk.ukf.Zaverecna_praca.RESTControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.ukf.Zaverecna_praca.Entity.Conference;
import sk.ukf.Zaverecna_praca.Entity.Note;
import sk.ukf.Zaverecna_praca.Service.ConferenceService;
import sk.ukf.Zaverecna_praca.Service.NoteService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/notes")
public class NoteRestController {

    @Autowired
    private NoteService noteService;
    @Autowired
    private ConferenceService conferenceService;

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes(){
        List<Note> notes = noteService.findAll();
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        return noteService.findById(id)
               .map(ResponseEntity::ok)
               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        if (note.getConference() == null || note.getConference().getId() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Optional<Conference> conferenceOptional = conferenceService.findById(note.getConference().getId());
        if (conferenceOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        note.setConference(conferenceOptional.get());

        Note newNote = new Note();
        newNote.setNameOfNote(note.getNameOfNote());
        newNote.setComment(note.getComment());
        newNote.setConference(conferenceOptional.get());
        noteService.save(newNote);
        return ResponseEntity.ok(newNote);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note updatedNote) {
        Optional<Note> noteOptional = noteService.findById(id);
        if (noteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Note note = noteOptional.get();

        note.setNameOfNote(updatedNote.getNameOfNote());
        note.setComment(updatedNote.getComment());

        noteService.save(note);
        return ResponseEntity.ok(note);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable Long id) {
        Optional<Note> noteOptional = noteService.findById(id);
        if (noteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        noteService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
