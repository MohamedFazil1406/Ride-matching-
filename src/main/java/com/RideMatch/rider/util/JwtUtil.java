package com.RideMatch.rider.util;


import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {

    @Value("${JWT_SECRET_KEY}")
    private String SECRET;

    private Key getSignKey(){

        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(String username,Long id){

        return Jwts.builder()
                .subject(username)
                .claim("id", id)
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis() + 3600*1000)
                )
                .signWith(getSignKey())
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public Long extractId(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("id", Long.class);
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser()
                    .verifyWith((SecretKey) getSignKey())
                    .build()
                    .parseClaimsJws(token);
                    return true;

        }catch(Exception e){
            return false;
        }
    }


}
