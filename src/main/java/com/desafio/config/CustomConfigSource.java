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
        if(secret.isPresent()){
            System.out.println(secret.get().password());
            properties.put("DATASOURCE_PASSWORD", secret.get().password());
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
