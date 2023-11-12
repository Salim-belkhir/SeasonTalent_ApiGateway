package fr.polytech.ApiGateway.models;

import jakarta.persistence.*;


@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long authority_id;

    private String username;

    @Enumerated(EnumType.STRING)
    private Roles authority;


    public Authority() {
    }

    public Authority(String username, Roles authority) {
        this.username = username;
        this.authority = authority;
    }


    public Long getAuthority_id() {
        return this.authority_id;
    }

    public String getUsername() {
        return this.username;
    }


    public Roles getAuthority() {
        return this.authority;
    }


    public void setAuthority_id(Long authority_id) {
        this.authority_id = authority_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setAuthority(Roles authority) {
        this.authority = authority;
    }
    
}
