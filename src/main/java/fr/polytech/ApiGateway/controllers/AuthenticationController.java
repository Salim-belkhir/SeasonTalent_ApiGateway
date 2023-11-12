package fr.polytech.ApiGateway.controllers;

import fr.polytech.ApiGateway.models.Role;
import fr.polytech.ApiGateway.models.User;
import fr.polytech.ApiGateway.models.dto.ResponseDTO;
import fr.polytech.ApiGateway.models.dto.RegisterRequestDTO;
import fr.polytech.ApiGateway.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import fr.polytech.ApiGateway.config.JwtUtils;
import fr.polytech.ApiGateway.models.dto.AuthenticationRequestDTO;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;



@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository repository;
    private final JwtUtils jwtUtils;
    private final BCryptPasswordEncoder passwordEncoder;



    @PostMapping("/login")
    public ResponseEntity<Object> authenticate(@RequestBody AuthenticationRequestDTO request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword())
        );
        final User user = repository.findByUsername(request.getUsername()).orElse(null);
        if(user != null){
            ResponseDTO response = ResponseDTO.builder()
                    .token(jwtUtils.generateToken(user))
                    .build();
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body("Invalid credentials");
    }

    @PostMapping("/logon")
    public ResponseEntity<Object> register(@RequestBody RegisterRequestDTO request) {
        User user = repository.findByUsername(request.getUsername()).orElse(null);

        if(user != null){
            return ResponseEntity.badRequest().body("User already exists");
        }

        user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.fromValue(request.getRole()));
        user.setEnabled(true);

        repository.save(user);

        ResponseDTO response = ResponseDTO.builder()
                .token(jwtUtils.generateToken(user))
                .build();
        return ResponseEntity.ok(response);
    }
}
