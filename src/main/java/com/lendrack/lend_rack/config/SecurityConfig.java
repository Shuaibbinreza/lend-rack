package com.lendrack.lend_rack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .securityMatcher("/**")
//                .authorizeHttpRequests(requests -> requests
//                        .anyRequest().authenticated() // all requests need auth
//                )
//                .formLogin(Customizer.withDefaults()) // use default login page
//                .logout(logout -> logout.permitAll());
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/manage-books/**").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin(Customizer.withDefaults())
                .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("admin")
                .password("{noop}password") // {noop} means no password encoder (plain text)
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
