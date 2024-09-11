package fr.epsitek.api.demo.domain.core.services;

import fr.epsitek.api.demo.application.ports.input.FindUserUseCase;
import fr.epsitek.api.demo.application.ports.output.UserOutputPort;
import fr.epsitek.api.demo.domain.core.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements FindUserUseCase {

    private final UserOutputPort userOutputPort;

    public Optional<User> findByEmail(String email) {
        // Logique métier
        return userOutputPort.findByEmail(email);
    }
}
