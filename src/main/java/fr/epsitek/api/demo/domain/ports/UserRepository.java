package fr.epsitek.api.demo.domain.ports;

import fr.epsitek.api.demo.domain.core.model.User;
import fr.epsitek.api.demo.domain.core.model.UserId;

import java.util.Optional;

public interface UserRepository {
    Optional<User> getById(UserId id);
    Optional<User> findByEmail(String email);
    User save(User user);
}
