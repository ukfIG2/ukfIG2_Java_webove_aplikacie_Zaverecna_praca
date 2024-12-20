package sk.ukf.Zaverecna_praca.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.ukf.Zaverecna_praca.Entity.Conference;
import sk.ukf.Zaverecna_praca.Entity.Note;
import sk.ukf.Zaverecna_praca.Repository.NoteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public Optional<Note> findById(Long id) {
        return noteRepository.findById(id);
    }

    public Note createNote(Note note, Conference conference) {
        Note newNote = new Note();
        newNote.setNameOfNote(note.getNameOfNote());
        newNote.setNote(note.getNote());
        newNote.setConference(conference);
        return noteRepository.save(newNote);
    }

    public Optional<Note> updateNote(Long id, Note updatedNote) {
        Optional<Note> noteOptional = noteRepository.findById(id);
        if (noteOptional.isEmpty()) {
            return Optional.empty();
        }

        Note note = noteOptional.get();
        note.setNameOfNote(updatedNote.getNameOfNote());
        note.setNote(updatedNote.getNote());
        return Optional.of(noteRepository.save(note));
    }

    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }
}