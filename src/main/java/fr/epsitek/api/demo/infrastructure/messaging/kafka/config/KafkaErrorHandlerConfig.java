package fr.epsitek.api.demo.infrastructure.messaging.kafka.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaErrorHandlerConfig {
/*
    @Bean
    public KafkaListenerErrorHandler consumerErrorHandler() {
        return (message, exception) -> {
            System.err.println("Erreur lors de la consommation du message: " + message.getPayload());
            System.err.println("Exception : " + exception.getMessage());
            return null;
        };
    }
 */
}