package fr.epsitek.api.demo.infrastructure.persistence;

import fr.epsitek.api.demo.domain.core.model.UserId;
import fr.epsitek.api.demo.domain.ports.UserRepository;
import fr.epsitek.api.demo.domain.core.model.User;
import fr.epsitek.api.demo.infrastructure.persistence.entity.UserEntity;
import fr.epsitek.api.demo.infrastructure.persistence.mapper.UserPersistenceMapper;
import fr.epsitek.api.demo.infrastructure.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserPersistenceMapper userPersistenceMapper;

    @Override
    public Optional<User> getById(UserId userId) {
        return userJpaRepository.findById(userId.id()).map(userPersistenceMapper::toUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email).map(userPersistenceMapper::toUser);
    }

    @Override
    public User save(User user) {
        UserEntity savedUser = userJpaRepository.save(userPersistenceMapper.toUserEntity(user));
        return userPersistenceMapper.toUser(savedUser);
    }
}
