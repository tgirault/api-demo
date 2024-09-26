package fr.epsitek.api.demo.application.ports.input;

import fr.epsitek.api.demo.domain.core.exception.UserAlreadyExistsException;
import fr.epsitek.api.demo.domain.core.model.User;

public interface CreateUserUseCase {
    User register(User user) throws UserAlreadyExistsException;
}
