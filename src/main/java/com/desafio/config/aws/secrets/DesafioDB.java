package com.desafio.config.aws.secrets;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DesafioDB(
        String username,
        String password,
        String engine,
        String host,
        String port,
        @JsonProperty("dbInstanceIdentifier")
        String database
) {
}
