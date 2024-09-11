package fr.epsitek.api.demo.application.ports.output;

import fr.epsitek.api.demo.domain.core.model.User;

import java.util.Optional;

public interface UserOutputPort {
    Optional<User> findByEmail(String email);
}
