package fr.epsitek.api.demo.domain.core.services;

import fr.epsitek.api.demo.domain.core.exception.UserAlreadyExistsException;
import fr.epsitek.api.demo.domain.core.model.User;
import fr.epsitek.api.demo.domain.core.model.UserId;
import fr.epsitek.api.demo.domain.ports.UserEventProducer;
import fr.epsitek.api.demo.domain.ports.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserEventProducer userEventProducer;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getById(UserId id) {
        return userRepository.getById(id);
    }

    public User register(User user) throws UserAlreadyExistsException {
        if (userRepository.findByEmail(user.email()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }
        User registeredUser = userRepository.save(user);
        userEventProducer.publish(registeredUser);
        return registeredUser;
    }
}
