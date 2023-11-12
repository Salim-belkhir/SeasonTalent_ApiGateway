package fr.polytech.ApiGateway.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.polytech.ApiGateway.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
 
    User findByUsername(String username);
}
