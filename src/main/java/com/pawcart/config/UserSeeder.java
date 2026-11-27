package com.pawcart.config;

import com.pawcart.model.Role;
import com.pawcart.repository.CustomerRepository;
import com.pawcart.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/** Creates demo accounts at startup so passwords are BCrypt-encoded. */
@Component
@RequiredArgsConstructor
public class UserSeeder implements ApplicationRunner {
    private final CustomerRepository customers;
    private final CustomerService service;

    @Override
    public void run(ApplicationArguments args) {
        seed("admin", "admin@pawcart.test", "Alex Admin", "Admin123!", Role.ADMIN);
        seed("staff", "staff@pawcart.test", "Sam Staff", "Staff123!", Role.STAFF);
        seed("customer", "customer@pawcart.test", "Casey Customer", "Customer123!", Role.CUSTOMER);
    }

    private void seed(String username, String email, String name, String password, Role role) {
        if (!customers.existsByUsername(username)) {
            service.register(username, email, name, password, role);
        }
    }
}
