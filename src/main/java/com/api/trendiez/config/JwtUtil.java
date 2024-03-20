package com.api.trendiez.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtUtil {

   // @Value("${jwt.secret}")  NEED TO PUT THIS BACK
    private String secret = "TRENDIEZ";

    public String extractUserId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        Logger logger = LoggerFactory.getLogger(JwtUtil.class);
        logger.info("Received token: " + token);
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
}

