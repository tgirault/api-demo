package fr.epsitek.api.demo.infrastructure.adapters.output.persistence;

import fr.epsitek.api.demo.application.ports.output.UserOutputPort;
import fr.epsitek.api.demo.domain.core.model.User;
import fr.epsitek.api.demo.infrastructure.adapters.output.persistence.entity.UserEntity;
import fr.epsitek.api.demo.infrastructure.adapters.output.persistence.mapper.UserPersistenceMapper;
import fr.epsitek.api.demo.infrastructure.adapters.output.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserOutputPersistenceAdapter implements UserOutputPort {

    private final UserRepository userRepository;
    private final UserPersistenceMapper userPersistenceMapper;

    @Override
    public Optional<User> getById(UUID id) {
        return userRepository.findById(id).map(userPersistenceMapper::toUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email).map(userPersistenceMapper::toUser);
    }

    @Override
    public User save(User user) {
        UserEntity savedUser = userRepository.save(userPersistenceMapper.toUserEntity(user));
        return userPersistenceMapper.toUser(savedUser);
    }
}
