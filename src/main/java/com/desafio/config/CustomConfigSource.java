package com.desafio.config;

import com.desafio.config.aws.AmazonSecretsManagerConfig;
import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CustomConfigSource implements ConfigSource {

    private final Map<String, String> properties = new HashMap<>();

    public CustomConfigSource() {
        var secret = AmazonSecretsManagerConfig.getSecretDesafioDb();
        secret.ifPresent(desafioDB -> {
            properties.put("DATASOURCE_USERNAME", desafioDB.username());
            properties.put("DATASOURCE_PASSWORD", desafioDB.password());
            properties.put("DATASOURCE_HOST", desafioDB.host());
            properties.put("DATASOURCE_PORT", desafioDB.port());
            properties.put("DATASOURCE_SCHEME", desafioDB.database());
        });
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
