package fr.epsitek.api.demo.infrastructure.persistence.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "fr.epsitek.api.demo.infrastructure.persistence.repository")
@EnableJpaAuditing
public class PersistenceConfig {
}
