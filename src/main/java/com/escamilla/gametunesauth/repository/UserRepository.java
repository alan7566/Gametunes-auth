/*
Creado por Alan David Escamilla Salas - 0222020025
Didier Martinez Gonzales - 0221810057
TUTORIAS: JUAN CARLOS GARCIA OJEDA
*/

package com.escamilla.gametunesauth.repository;


import com.escamilla.gametunesauth.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
