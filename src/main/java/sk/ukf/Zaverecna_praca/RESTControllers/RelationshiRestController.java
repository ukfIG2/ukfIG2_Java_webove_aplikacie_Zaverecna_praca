package sk.ukf.Zaverecna_praca.RESTControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.ukf.Zaverecna_praca.Service.RelationshipService;

// Define this as a REST Controller
@RestController
@RequestMapping("api/relationships")
public class RelationshiRestController {

    @Autowired
    private RelationshipService relationshipService;

    // POST endpoint to add user to presentation
    @PostMapping("/add")
    public ResponseEntity<String> addUserToPresentation(@RequestBody AddUserToPresentationRequest request) {
        relationshipService.addUserToPresentation(request.getUserId(), request.getPresentationId());
        return ResponseEntity.ok("User added to presentation successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUserFromPresentation(@RequestBody AddUserToPresentationRequest request) {
        relationshipService.deleteUserFromPresentation(request.getUserId(), request.getPresentationId());
        return ResponseEntity.ok("User deleted from presentation successfully");
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
