package com.desafio.config;

import com.desafio.config.aws.AmazonSecretsManagerConfig;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CustomConfigSource implements ConfigSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomConfigSource.class);

    private final Map<String, String> properties = new HashMap<>();

    public CustomConfigSource() {
        try {
            var secret = AmazonSecretsManagerConfig.getSecretDesafioDb();
            secret.ifPresent(desafioDB -> properties.put("DATASOURCE_PASSWORD", desafioDB.password()));
        } catch (Exception e) {
            LOGGER.error("Error ao recuperar secret: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, String> getProperties() {
        return properties;
    }

    @Override
    public Set<String> getPropertyNames() {
        return Set.of();
    }

    @Override
    public String getName() {
        return "CustomConfigSource";
    }

    @Override
    public String getValue(String propertyName) {
        return properties.get(propertyName);
    }

}
