/*
Creado por Alan David Escamilla Salas - 0222020025
Didier Martinez Gonzales - 0221810057
TUTORIAS: JUAN CARLOS GARCIA OJEDA
*/

package com.escamilla.gametunesauth.payload.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String id;
    private String username;
    private String email;
    private String role;

    public JwtResponse(String token, String id, String username, String email, String role) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    // Getters y setters
}
