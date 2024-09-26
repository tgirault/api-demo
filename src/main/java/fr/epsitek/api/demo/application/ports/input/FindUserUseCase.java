package fr.epsitek.api.demo.application.ports.input;

import fr.epsitek.api.demo.domain.core.model.User;

import java.util.Optional;
import java.util.UUID;

public interface FindUserUseCase {
    Optional<User> findByEmail(String email);
    Optional<User> getById(UUID id);
}
