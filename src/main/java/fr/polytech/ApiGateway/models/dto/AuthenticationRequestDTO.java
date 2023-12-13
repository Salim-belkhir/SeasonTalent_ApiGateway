package fr.polytech.ApiGateway.models.dto;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequestDTO {

    private String username;
    private String password;
    
}
