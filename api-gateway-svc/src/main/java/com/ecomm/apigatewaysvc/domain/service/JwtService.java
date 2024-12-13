package com.ecomm.apigatewaysvc.domain.service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.security.Key;

import static org.apache.logging.log4j.LogManager.getLogger;

@Component
public class JwtService {


    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    private final Logger logger = getLogger(JwtService.class);

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (ExpiredJwtException e) {
            logger.error("JWT is expired: " + e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT structure: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT token is null or empty: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return false;
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}