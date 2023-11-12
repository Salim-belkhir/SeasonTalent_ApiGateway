package fr.polytech.ApiGateway.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import fr.polytech.ApiGateway.config.JwtUtils;
import fr.polytech.ApiGateway.models.AuthenticationRequestDTO;
import fr.polytech.ApiGateway.services.UserDetailsServiceCustom;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;



@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {


    private final AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceCustom userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;
    

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequestDTO request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        final UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
        if(user != null){
            return ResponseEntity.ok(jwtUtils.generateToken(user));
        }
        return ResponseEntity.badRequest().body("Invalid credentials");
    }

    @PostMapping("/logon")
    public ResponseEntity<String> logon(@RequestBody AuthenticationRequestDTO request) {
        final UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
        if(user != null){
            return ResponseEntity.badRequest().body("User already exists");
        }
        userDetailsService.saveUser(request.getUsername(), request.getPassword());
        return ResponseEntity.ok("User created");
    }
}
