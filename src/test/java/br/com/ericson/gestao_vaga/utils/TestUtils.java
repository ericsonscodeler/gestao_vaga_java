package br.com.ericson.gestao_vaga.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {

    public static String objectToJSON(Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateToken(UUID idCompany, String secret) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create().withIssuer("java")
                .withExpiresAt(expiresIn)
                .withSubject(idCompany.toString())
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);

        System.out.println(token);

        return token;
    }
}
