package it.exercise.spring_la_mia_pizzeria_security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()
            .requestMatchers("/pizzas/create").hasAuthority("ADMIN")
            .requestMatchers("/pizzas/edit/**").hasAuthority("ADMIN")
            .requestMatchers("/pizzas/delete/**").hasAuthority("ADMIN")
            .requestMatchers("/pizzas/{id}/promo").hasAuthority("ADMIN")
            .requestMatchers("/ingredients/**").hasAuthority("ADMIN")
            .requestMatchers("/promos/**").hasAuthority("ADMIN")
            .requestMatchers("/pizzas", "/pizzas/**").hasAnyAuthority("USER", "ADMIN")
            .requestMatchers("/**").permitAll()
            .and().formLogin()
            .and().logout()
            .and().exceptionHandling()
            .and().csrf().disable();

        return http.build();
    }

    @Bean
    DatabaseUserDetailsService userDetailsService() {
        return new DatabaseUserDetailsService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}   
