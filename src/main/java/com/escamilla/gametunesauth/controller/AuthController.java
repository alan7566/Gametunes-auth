/*
Creado por Alan David Escamilla Salas - 0222020025
Didier Martinez Gonzales - 0221810057
TUTORIAS: JUAN CARLOS GARCIA OJEDA
*/

package com.escamilla.gametunesauth.controller;

import com.escamilla.gametunesauth.payload.request.LoginRequest;
import com.escamilla.gametunesauth.payload.request.SignupRequest;
import com.escamilla.gametunesauth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return authService.registerUser(signUpRequest);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validate(@Valid @RequestHeader("Authorization") String token) {
        return authService.validateToken(token);
    }
}
