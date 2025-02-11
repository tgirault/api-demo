package fr.epsitek.api.demo.infrastructure.messaging.kafka.producer;

import fr.epsitek.api.demo.domain.core.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserKafkaProducerAdapter {
//public class UserKafkaProducerAdapter implements UserEventProducer {

//    private static final String TOPIC = "user-registration-event";

//    private final KafkaTemplate<String, String> kafkaTemplate;

    public void publish(User user) {
        log.error("La publication dans un topic Kafka n'est pas implémentée.");
       /* CompletableFuture<SendResult<String, String>> publicationAck = kafkaTemplate.send(TOPIC, user.toString());

        publicationAck.thenAccept(result -> {
            RecordMetadata metadata = result.getRecordMetadata();
            log.info("Message publié avec succès dans le topic {}, partition {}, offset {}.",
                    metadata.topic(), metadata.partition(), metadata.offset());

        }).exceptionally(ex -> {
            log.error("Erreur de publication dans le topic : {}", ex.getMessage());
            return null;
        });*/
    }
}