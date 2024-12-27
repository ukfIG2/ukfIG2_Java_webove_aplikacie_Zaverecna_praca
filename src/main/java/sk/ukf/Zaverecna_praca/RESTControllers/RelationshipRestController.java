package sk.ukf.Zaverecna_praca.RESTControllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.ukf.Zaverecna_praca.Service.RelationshipService;

// Define this as a REST Controller
@RestController
@RequestMapping("api/relationships")
public class RelationshipRestController {

    @Autowired
    private RelationshipService relationshipService;

    @PostMapping("/add")
    public ResponseEntity<String> addUserToPresentation(@RequestBody @Valid AddUserToPresentationRequest request) {
        relationshipService.addUserToPresentation(request.getUserId(), request.getPresentationId());
        return ResponseEntity.ok("User added to presentation successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUserFromPresentation(@RequestBody @Valid AddUserToPresentationRequest request) {
        relationshipService.deleteUserFromPresentation(request.getUserId(), request.getPresentationId());
        return ResponseEntity.ok("User deleted from presentation successfully");
    }

    @GetMapping("/isRegistered")
    public ResponseEntity<Boolean> isUserRegistered(@RequestParam Long userId, @RequestParam Long presentationId) {
        boolean isRegistered = relationshipService.isAlreadyRegistered(presentationId, userId);
        return ResponseEntity.ok(isRegistered);
    }

}

// Request class to represent the JSON input structure
class AddUserToPresentationRequest {
    private Long userId;
    private Long presentationId;

    // Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPresentationId() {
        return presentationId;
    }

    public void setPresentationId(Long presentationId) {
        this.presentationId = presentationId;
    }
}