package it.exercise.spring_la_mia_pizzeria_security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.exercise.spring_la_mia_pizzeria_security.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByUsername(String username);
}
