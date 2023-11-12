package fr.polytech.ApiGateway.models;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


@Getter
@Setter
@NoArgsConstructor
public class AuthenticationRequestDTO {

    private String username;
    private String password;
    
}
