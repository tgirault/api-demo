package fr.epsitek.api.demo.domain.services;

import fr.epsitek.api.demo.domain.User;
import fr.epsitek.api.demo.domain.ports.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserPort userPort;

    public Optional<User> findByEmail(String email) {
        // Logique métier
        return userPort.findByEmail(email);
    }
}
