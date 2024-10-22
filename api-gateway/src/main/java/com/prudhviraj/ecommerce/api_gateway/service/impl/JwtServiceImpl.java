package com.prudhviraj.ecommerce.api_gateway.service.impl;
import com.prudhviraj.ecommerce.api_gateway.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

/**
 * Implementation of the JwtService interface, responsible for handling
 * JWT-related operations such as extracting user information from tokens.
 */
@Service
public class JwtServiceImpl implements JwtService {

    // The secret key used for signing and verifying JWT tokens, injected from application properties.
    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    /**
     * Generates a SecretKey used for signing and verifying JWT tokens.
     * The key is derived from the secret string defined in the application properties.
     *
     * @return the SecretKey derived from the configured secret.
     */
    private SecretKey getSecretKey() {
        // Converts the secret key string to a SecretKey object using HMAC SHA algorithm.
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Extracts the user ID from the provided JWT token.
     *
     * @param token the JWT token to parse.
     * @return the user ID as a Long extracted from the token's subject.
     */
    @Override
    public Long getUserIdFromToken(String token) {
        // Parses the token and retrieves the claims (payload) to extract the user ID (subject).
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey()) // Uses the secret key to verify the token's signature.
                .build()
                .parseSignedClaims(token) // Parses the signed JWT and extracts the claims.
                .getPayload();
        return Long.valueOf(claims.getSubject()); // Retrieves the subject and converts it to Long.
    }

    /**
     * Extracts the user role from the provided JWT token.
     *
     * @param token the JWT token to parse.
     * @return the user role as a String extracted from the token's claims.
     */
    @Override
    public String getUserRoleFromToken(String token) {
        // Parses the token and retrieves the claims (payload) to extract the user role.
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey()) // Uses the secret key to verify the token's signature.
                .build()
                .parseSignedClaims(token) // Parses the signed JWT and extracts the claims.
                .getPayload();
        return claims.get("role", String.class); // Retrieves the "role" claim from the token.
    }
}



