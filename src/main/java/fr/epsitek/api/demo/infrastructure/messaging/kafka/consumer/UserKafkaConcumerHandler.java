package fr.epsitek.api.demo.infrastructure.messaging.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserKafkaConcumerHandler {
/*
    @KafkaListener(topics = "user-registration-event", groupId = "console-consumer-34975", errorHandler = "consumerErrorHandler")
    public void consume(ConsumerRecord<String, String> record) {
        try {
            log.info("Message reçu : {}", record.value());

        } catch (Exception e) {
            throw new ListenerExecutionFailedException("Erreur lors du traitement du message.");
        }
    }
 */
}
