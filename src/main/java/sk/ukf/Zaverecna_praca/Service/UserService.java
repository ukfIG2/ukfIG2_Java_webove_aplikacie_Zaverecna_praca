package sk.ukf.Zaverecna_praca.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.ukf.Zaverecna_praca.Entity.User;
import sk.ukf.Zaverecna_praca.Enums.Role;
import sk.ukf.Zaverecna_praca.Repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        if (user.getRole() == null) {
            user.setRole(Role.ROLE_registered_visitor);
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setTitleBeforeName(user.getTitleBeforeName());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setTitleAfterName(user.getTitleAfterName());
        newUser.setRole(user.getRole());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setPhoto(user.getPhoto());
        newUser.setComment(user.getComment());

        return userRepository.save(newUser);
    }

    public Optional<User> updateUser(Long id, User userDetails) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isEmpty()) {
            return Optional.empty();
        }

        User existingUser = existingUserOpt.get();
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setPassword(userDetails.getPassword());
        existingUser.setTitleBeforeName(userDetails.getTitleBeforeName());
        existingUser.setFirstName(userDetails.getFirstName());
        existingUser.setLastName(userDetails.getLastName());
        existingUser.setTitleAfterName(userDetails.getTitleAfterName());
        existingUser.setRole(userDetails.getRole() != null ? userDetails.getRole() : Role.ROLE_registered_visitor);
        existingUser.setPhoneNumber(userDetails.getPhoneNumber());
        existingUser.setPhoto(userDetails.getPhoto());
        existingUser.setComment(userDetails.getComment());

        return Optional.of(userRepository.save(existingUser));
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}

