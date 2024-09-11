package fr.epsitek.api.demo.application.ports.input;

import fr.epsitek.api.demo.domain.core.model.User;

import java.util.Optional;

public interface FindUserUseCase {
    Optional<User> findByEmail(String email);
}
