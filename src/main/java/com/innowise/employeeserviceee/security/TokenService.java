package com.innowise.employeeserviceee.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.Stateless;

import java.security.Key;
import java.time.Duration;
import java.util.Date;

@Stateless
public class TokenService {

    private Key key;

    @PostConstruct
    public void init() {
        key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
    }

    public String generateToken(String username, String authority) {
        Date expiration = new Date(System.currentTimeMillis() + Duration.ofHours(1).toMillis());
        return Jwts.builder()
                .setSubject(username)
                .claim("authority", authority)
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    public Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }
}
