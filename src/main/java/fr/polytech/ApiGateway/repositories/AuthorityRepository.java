package fr.polytech.ApiGateway.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.polytech.ApiGateway.models.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long>{
    
}
