package fr.polytech.ApiGateway.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "users")
public class User {


    @Id
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean enabled; 

    @OneToMany()
    @JoinColumn(name = "username", referencedColumnName = "username")
    private List<Authority> authorities;


    public User() {
    }

    public User(String username, String password, boolean enabled, List<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }


    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public List<Authority> getAuthorities() {
        return this.authorities;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }


    
}
