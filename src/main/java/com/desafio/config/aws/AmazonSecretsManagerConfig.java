package com.desafio.config.aws;

import com.desafio.config.aws.secrets.DesafioDB;
import com.desafio.exception.error.InternalServerError;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import java.util.Optional;

public class AmazonSecretsManagerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmazonSecretsManagerConfig.class);

    public static final String DESAFIODB_SECRET = "desafiodb-secret";
    public static final String REGIAO_NAME = "sa-east-1";
    public static final Region REGIAO = Region.of(REGIAO_NAME);

    public static Optional<DesafioDB> getSecretDesafioDb() {
        GetSecretValueResponse getSecretValueResponse;
        SecretsManagerClient client = SecretsManagerClient.builder()
                .region(REGIAO)
                .build();
        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(DESAFIODB_SECRET)
                .build();
        try {
            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
        } catch (SdkClientException e) {
            throw new InternalServerError("Error ao recuperar secret: {}", e.getCause());
        }
        return jsonToDesafioDB(getSecretValueResponse.secretString());
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
