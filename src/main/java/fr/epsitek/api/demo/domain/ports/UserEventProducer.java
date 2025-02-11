package fr.epsitek.api.demo.domain.ports;

import fr.epsitek.api.demo.domain.core.model.User;

public interface UserEventProducer {
    void publish(User user);
}
