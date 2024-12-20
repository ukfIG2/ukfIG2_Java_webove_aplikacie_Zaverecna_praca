package sk.ukf.Zaverecna_praca.RESTControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.ukf.Zaverecna_praca.Entity.PresentationsHasSpeakers;
import sk.ukf.Zaverecna_praca.Service.PresentationsHasSpeakersService;

import java.util.List;

@RestController
@RequestMapping("api/presentations_has_speakers")
public class PresentationsHasSpeakersRestController {

    @Autowired
    private PresentationsHasSpeakersService presentationService;

    @GetMapping
    public ResponseEntity<List<PresentationsHasSpeakers>> getAllPresentations(){
        List<PresentationsHasSpeakers> presentations = presentationService.findaAll();
        return ResponseEntity.ok(presentations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PresentationsHasSpeakers> getPresentationById(@PathVariable Long id){
        return presentationService.findById(id).map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
    }
}
