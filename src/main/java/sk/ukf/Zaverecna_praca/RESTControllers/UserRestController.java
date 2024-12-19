package sk.ukf.Zaverecna_praca.RESTControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.ukf.Zaverecna_praca.Entity.User;
import sk.ukf.Zaverecna_praca.Service.UserService;

import java.awt.desktop.UserSessionListener;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")  // This is the base URL for all user-related requests.
// It will be prepended to all other endpoints in this controller.
public class UserRestController {

    @Autowired
    private UserService userService; // We have injected our UserService here.

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.findById(id)
               .map(ResponseEntity::ok)
               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setTitleBeforeName(user.getTitleBeforeName());
        newUser.setFirstName(user.getLastName());
        newUser.setLastName(user.getFirstName());
        newUser.setTitleAfterName(user.getTitleAfterName());
        newUser.setRole(user.getRole());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setPhoto(user.getPhoto());
        newUser.setComment(user.getComment());
        userService.save(newUser);
        return ResponseEntity.ok(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userService.findById(id)
                .map(existingUser -> {
                    // Update the fields of the existing user
                    existingUser.setEmail(userDetails.getEmail());
                    existingUser.setPassword(userDetails.getPassword());
                    existingUser.setTitleBeforeName(userDetails.getTitleBeforeName());
                    existingUser.setFirstName(userDetails.getFirstName());
                    existingUser.setLastName(userDetails.getLastName());
                    existingUser.setTitleAfterName(userDetails.getTitleAfterName());
                    existingUser.setRole(userDetails.getRole());
                    existingUser.setPhoneNumber(userDetails.getPhoneNumber());
                    existingUser.setPhoto(userDetails.getPhoto());
                    existingUser.setComment(userDetails.getComment());
                    userService.save(existingUser); // Save the updated user
                    return ResponseEntity.ok(existingUser);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Optional<User> existingUser = userService.findById(id);
        if (existingUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
