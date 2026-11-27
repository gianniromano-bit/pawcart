package com.pawcart.web;

import com.pawcart.model.Role;
import com.pawcart.repository.CustomerRepository;
import com.pawcart.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final CustomerService service;
    private final CustomerRepository customers;

    @GetMapping("/login")
    String login() {
        return "login";
    }

    @GetMapping("/register")
    String registerForm(Model m) {
        m.addAttribute("form", new RegistrationForm());
        return "register";
    }

    @PostMapping("/register")
    String register(@Valid @ModelAttribute("form") RegistrationForm f, BindingResult br) {
        if (f.getPassword() != null && !f.getPassword().equals(f.getConfirmPassword())) {
            br.rejectValue("confirmPassword", "mismatch", "Passwords do not match");
        }
        if (f.getUsername() != null && customers.existsByUsername(f.getUsername())) {
            br.rejectValue("username", "taken", "Username is already taken");
        }
        if (f.getEmail() != null && customers.existsByEmail(f.getEmail())) {
            br.rejectValue("email", "taken", "Email is already registered");
        }
        if (br.hasErrors()) return "register";

        // Self-registration always creates a CUSTOMER
        service.register(f.getUsername(), f.getEmail(), f.getFullName(), f.getPassword(), Role.CUSTOMER);
        return "redirect:/login?registered";
    }
}
