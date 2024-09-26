package fr.epsitek.api.demo.infrastructure.adapters.output.persistence.repository;

import fr.epsitek.api.demo.infrastructure.adapters.output.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findById(UUID id);
    Optional<UserEntity> findByEmail(String email);
}
