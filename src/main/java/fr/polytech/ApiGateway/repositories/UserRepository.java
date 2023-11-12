package fr.polytech.ApiGateway.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.polytech.ApiGateway.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
