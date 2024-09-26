package fr.epsitek.api.demo.application.ports.output;

import fr.epsitek.api.demo.domain.core.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserOutputPort {
    Optional<User> getById(UUID id);
    Optional<User> findByEmail(String email);
    User save(User user);
}
