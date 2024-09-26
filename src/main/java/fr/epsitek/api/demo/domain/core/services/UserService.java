package fr.epsitek.api.demo.domain.core.services;

import fr.epsitek.api.demo.application.ports.input.CreateUserUseCase;
import fr.epsitek.api.demo.application.ports.input.FindUserUseCase;
import fr.epsitek.api.demo.application.ports.output.UserOutputPort;
import fr.epsitek.api.demo.domain.core.exception.UserAlreadyExistsException;
import fr.epsitek.api.demo.domain.core.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements FindUserUseCase, CreateUserUseCase {

    private final UserOutputPort userOutputPort;

    public Optional<User> findByEmail(String email) {
        // Logique métier
        return userOutputPort.findByEmail(email);
    }

    @Override
    public Optional<User> getById(UUID id) {
        return userOutputPort.getById(id);
    }

    @Override
    public User register(User user) throws UserAlreadyExistsException {
        if (userOutputPort.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }
        return userOutputPort.save(user);
    }
}
