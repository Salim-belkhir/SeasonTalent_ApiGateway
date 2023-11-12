package fr.polytech.ApiGateway.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fr.polytech.ApiGateway.models.Authority;
import fr.polytech.ApiGateway.models.CustomUserDetails;
import fr.polytech.ApiGateway.models.Roles;
import fr.polytech.ApiGateway.models.User;
import fr.polytech.ApiGateway.repositories.AuthorityRepository;
import fr.polytech.ApiGateway.repositories.UserRepository;

@Service
public class UserDetailsServiceCustom implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;


    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }



    public void saveUser(String username, String password) {
        User user = new User();

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = bCryptPasswordEncoder.encode(password);

        Authority authority = authorityRepository.save(new Authority(username, Roles.ROLE_RECRUTEUR));
        List<Authority> authorities = new ArrayList<Authority>();
        authorities.add(authority);

        user.setUsername(username);
        user.setPassword(encryptedPassword);
        user.setAuthorities(authorities);
        userRepository.save(user);
        return;
    }





}
