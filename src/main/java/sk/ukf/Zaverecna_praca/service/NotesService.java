package sk.ukf.Zaverecna_praca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.ukf.Zaverecna_praca.entity.Notes;
import sk.ukf.Zaverecna_praca.repository.NotesRepository;

import java.util.List;

@Service
public class NotesService {

    @Autowired
    private NotesRepository notesRepository;

    public List<Notes> findAll(){
        return notesRepository.findAll();
    }
}
