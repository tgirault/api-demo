package fr.epsitek.api.demo.infrastructure.adapters;

import fr.epsitek.api.demo.domain.User;
import fr.epsitek.api.demo.domain.ports.UserPort;
import fr.epsitek.api.demo.infrastructure.converters.UserConverter;
import fr.epsitek.api.demo.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserAdapter implements UserPort {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email).map(userConverter::toUser);
    }
}
