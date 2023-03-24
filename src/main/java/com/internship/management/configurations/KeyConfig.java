package com.internship.management.configurations;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class KeyConfig {

    private final long EXPIRATIONTIME = 86400000;
    private final SecretKey KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    //private String secret;


}
