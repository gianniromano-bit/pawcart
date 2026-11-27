package com.pawcart.service;

import com.pawcart.model.Customer;
import com.pawcart.model.Role;
import com.pawcart.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService implements UserDetailsService {
    private final CustomerRepository customers;
    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return customers.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    public Customer register(String username, String email, String fullName, String rawPassword, Role role) {
        Customer c = new Customer();
        c.setUsername(username);
        c.setEmail(email);
        c.setFullName(fullName);
        c.setPassword(encoder.encode(rawPassword));
        c.setRole(role);
        return customers.save(c);
    }
}
