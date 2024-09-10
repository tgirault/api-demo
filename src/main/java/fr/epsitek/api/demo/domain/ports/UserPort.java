package fr.epsitek.api.demo.domain.ports;

import fr.epsitek.api.demo.domain.User;

import java.util.Optional;

public interface UserPort {
    Optional<User> findByEmail(String email);
}
