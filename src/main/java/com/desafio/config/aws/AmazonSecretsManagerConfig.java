package com.desafio.config.aws;

import com.desafio.config.aws.secrets.DesafioDB;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import java.util.Optional;

public class AmazonSecretsManagerConfig {

    public static final String DESAFIODB_SECRET = "desafiodb-secret";
    public static final String REGIAO_NAME = "sa-east-1";
    public static final Region REGIAO = Region.of(REGIAO_NAME);

    private static final Logger LOGGER = LoggerFactory.getLogger(AmazonSecretsManagerConfig.class);

    public static Optional<DesafioDB>  getSecretDesafioDb() {
        GetSecretValueResponse getSecretValueResponse;
        try (SecretsManagerClient client = SecretsManagerClient.builder()
                .region(REGIAO)
                .build()) {

            GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                    .secretId(DESAFIODB_SECRET)
                    .build();

            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
        }
        var secret = getSecretValueResponse.secretString();
        return jsonToDesafioDB(secret);
    }

    public static Optional<DesafioDB> jsonToDesafioDB(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return Optional.of(objectMapper.readValue(jsonString, DesafioDB.class));
        } catch (Exception e) {
            LOGGER.error("Error converting JSON to DesafioDB: {}", e.getMessage(), e);
            return Optional.empty();
        }
    }

}
