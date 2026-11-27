package com.pawcart.config;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain chain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(a -> a
                // public
                .requestMatchers(antMatcher("/"), antMatcher("/about"), antMatcher("/brands"),
        antMatcher("/register"), antMatcher("/login"),
        antMatcher("/error"), antMatcher("/css/**"), antMatcher("/js/**"),
        antMatcher("/images/**")).permitAll()
                // ADMIN + STAFF: create and edit items
                .requestMatchers(antMatcher("/items/new"),
                        antMatcher(HttpMethod.POST, "/items")).hasAnyRole("ADMIN", "STAFF")
                .requestMatchers(antMatcher("/items/*/edit")).hasAnyRole("ADMIN", "STAFF")
                // ADMIN only: delete
                .requestMatchers(antMatcher(HttpMethod.POST, "/items/*/delete")).hasRole("ADMIN")
                // anyone can browse items
                .requestMatchers(antMatcher(HttpMethod.GET, "/items"),
                        antMatcher(HttpMethod.GET, "/items/*")).permitAll()
                // ADMIN only: admin area, REST API, H2 console
                .requestMatchers(antMatcher("/admin/**"), antMatcher("/api/**"),
                        antMatcher("/h2-console/**")).hasRole("ADMIN")
                .anyRequest().authenticated())
            .formLogin(f -> f.loginPage("/login").permitAll())
            .logout(l -> l.logoutSuccessUrl("/login?logout").permitAll())
            .csrf(c -> c.ignoringRequestMatchers(antMatcher("/h2-console/**")))
            .headers(h -> h.frameOptions(f -> f.sameOrigin()));
        return http.build();
    }
}
