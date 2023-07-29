package ru.panteleevya;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtProvider {
    private final String secret;

    public JwtProvider(
            @Value("${jwt.secret}") String secret
    ) {
        this.secret = secret;
    }

    public String createJwtString(String personId, long expirationAtMillis) {
        Claims claims = Jwts.claims()
                .setSubject(personId)
                .setExpiration(new Date(expirationAtMillis));
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
