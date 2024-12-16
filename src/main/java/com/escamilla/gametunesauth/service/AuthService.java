/*
Creado por Alan David Escamilla Salas - 0222020025
Didier Martinez Gonzales - 0221810057
TUTORIAS: JUAN CARLOS GARCIA OJEDA
*/

package com.escamilla.gametunesauth.service;

import com.escamilla.gametunesauth.model.entity.User;
import com.escamilla.gametunesauth.payload.request.LoginRequest;
import com.escamilla.gametunesauth.payload.request.SignupRequest;
import com.escamilla.gametunesauth.payload.response.JwtResponse;
import com.escamilla.gametunesauth.payload.response.MessageResponse;
import com.escamilla.gametunesauth.repository.UserRepository;
import com.escamilla.gametunesauth.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    public ResponseEntity<?> registerUser(SignupRequest signupRequest) {
        // Validar si el usuario ya existe
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Crear nuevo usuario
        User user = new User(
                signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword())
        );

        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + loginRequest.getUsername()));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid Password");
        }

        String jwt = jwtUtils.generateJwtToken(user);

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().name()
        ));
    }

    public ResponseEntity<?> validateToken(String token) {
        if (token == null || token.isBlank()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Token is missing or blank"));
        }

        if (!token.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body(new MessageResponse("Token format is invalid"));
        }
        // Elimina el prefijo "Bearer " antes de pasarlo a jwtUtils
        token = token.substring(7);


        if (jwtUtils.validateJwtToken(token)) {
            return ResponseEntity.ok(new MessageResponse("Token is valid"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Token is invalid"));
        }
    }


}
