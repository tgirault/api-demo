package fr.epsitek.api.demo.infrastructure.adapters.output.persistence;

import fr.epsitek.api.demo.application.ports.output.UserOutputPort;
import fr.epsitek.api.demo.domain.core.model.User;
import fr.epsitek.api.demo.infrastructure.adapters.output.persistence.mapper.UserPersistenceMapper;
import fr.epsitek.api.demo.infrastructure.adapters.output.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserOutputPersistenceAdapter implements UserOutputPort {

    private final UserRepository userRepository;
    private final UserPersistenceMapper userPersistenceMapper;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email).map(userPersistenceMapper::toUser);
    }
}
