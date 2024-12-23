package sk.ukf.Zaverecna_praca;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sk.ukf.Zaverecna_praca.Entity.User;
import sk.ukf.Zaverecna_praca.Enums.Role;
import sk.ukf.Zaverecna_praca.Service.UserService;


@Controller
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User()); // Empty User object for the form
        return "auth/register";
    }
/*
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid User user,
                               BindingResult result,
                               Model model) {
        System.out.println(user.getPassword());
        // Custom password validation
        String password = user.getPassword();
        if (password.length() < 8 || !password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,255}$") || !password.matches(".*[0-9].*")) {
            result.rejectValue("password", "error.user", "Password must be at least 8 characters long, contain letters and numbers.");
        }
        if (result.hasErrors()) {
            result.getFieldErrors().forEach(error ->
                    System.out.println("Field: " + error.getField() + " Error: " + error.getDefaultMessage()));
            return "auth/register";
        }

        // Check if email is already in use
        if (userService.existsByEmail(user.getEmail())) {
            model.addAttribute("emailError", "Email is already in use.");
            return "auth/register";
        }

        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Set default role
        user.setRole(Role.ROLE_registered_visitor);

        // Save the user
        userService.createUser(user);

        // Redirect to login page after successful registration
        return "redirect:/login?success";
    }*/
    /*
@PostMapping("/register")
public String registerUser(@ModelAttribute("user") @Valid User user,
                           BindingResult result,
                           Model model) {
    // Print the submitted password
    String password = user.getPassword();
    System.out.println("Password submitted: " + password);

    // Custom password validation
    boolean isShort = password.length() < 8;
    boolean hasUppercase = password.matches(".*[A-Z].*");
    boolean hasLowercase = password.matches(".*[a-z].*");
    boolean hasNumber = password.matches(".*\\d.*");
    boolean matchesRegex = password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,255}$");

    // Print detailed validation information
    if (isShort) {
        System.out.println("Validation Error: Password is shorter than 8 characters.");
    } else{System.out.println("Password has at leasts 8.");}
    if (!hasUppercase) {
        System.out.println("Validation Error: Password does not contain an uppercase letter.");
    }else{System.out.println("Password has uppercase");}
    if (!hasLowercase) {
        System.out.println("Validation Error: Password does not contain a lowercase letter.");
    }else{{System.out.println("password has lowercaasw");}}
    if (!hasNumber) {
        System.out.println("Validation Error: Password does not contain a number.");
    }else System.out.println("password has number");
    if (!matchesRegex) {
        System.out.println("Validation Error: Password does not fully match the required pattern.");
    }else{System.out.println("Password is good");}

    // Accumulate errors in BindingResult
    if (isShort || !hasUppercase || !hasLowercase || !hasNumber || !matchesRegex) {
        result.rejectValue("password", "error.user",
                "Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, and one number.");
        System.out.println("Password validation failed. Issues found.");
    }

    // Handle errors
    if (result.hasErrors()) {
        result.getFieldErrors().forEach(error ->
                System.out.println("Field: " + error.getField() + " Error: " + error.getDefaultMessage()));
        return "auth/register";
    }

    // Proceed with registration logic
    return "redirect:/success";
}*/
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid User user,
                               BindingResult result,
                               Model model) {
        System.out.println(user.getPassword());

        // Custom password validation
        String password = user.getPassword();

        // Validation logic
        boolean isShort = password.length() < 8;
        boolean hasUppercase = password.matches(".*[A-Z].*");
        boolean hasLowercase = password.matches(".*[a-z].*");
        boolean hasNumber = password.matches(".*\\d.*");
        boolean matchesRegex = password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,255}$");

        // Debugging: Print the validation status
        System.out.println("Password validation details:");
        if (isShort) {
            System.out.println("Validation Error: Password is shorter than 8 characters.");
        } else{System.out.println("Password has at leasts 8.");}
        if (!hasUppercase) {
            System.out.println("Validation Error: Password does not contain an uppercase letter.");
        }else{System.out.println("Password has uppercase");}
        if (!hasLowercase) {
            System.out.println("Validation Error: Password does not contain a lowercase letter.");
        }else{{System.out.println("password has lowercaasw");}}
        if (!hasNumber) {
            System.out.println("Validation Error: Password does not contain a number.");
        }else System.out.println("password has number");
        if (!matchesRegex) {
            System.out.println("Validation Error: Password does not fully match the required pattern.");
        }else{System.out.println("Password is good");}

        // Accumulate errors in BindingResult
        if (isShort || !hasUppercase || !hasLowercase || !hasNumber || !matchesRegex) {
            result.rejectValue("password", "error.user",
                    "Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, and one number.");
            System.out.println("Password validation failed. Issues found.");
        }
        else{System.out.println("Password validation passed");}

        // Handle errors if any exist
        System.out.println("before result");
        if (result.hasErrors()) {
            System.out.println(result.hasErrors());
            result.getFieldErrors().forEach(error ->
                    System.out.println("Field: " + error.getField() + " Error: " + error.getDefaultMessage()));
            return "auth/register"; // Return to registration form if errors
        }

        // Check if email is already in use
        System.out.println("Befoore uservecie email check");
        if (userService.existsByEmail(user.getEmail())) {
            model.addAttribute("emailError", "Email is already in use.");
            return "auth/register"; // Return to registration form if email already taken
        }

        // Encode the password
        System.out.println("encoding password");
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Set default role
        System.out.println("setting role");
        user.setRole(Role.ROLE_registered_visitor);

        // Save the user
        System.out.println("Psaving user");
        userService.createUser(user);

        // Redirect to login page after successful registration
        System.out.println("redirecting");
        return "redirect:/login?success";
    }

}
