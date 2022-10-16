package com.nesstraining.simpletokendemo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenUtil {
    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    public String generateToken(String username) {
       long currentMillis=System.currentTimeMillis();
       long expiryMillis=currentMillis+(60*60*1000*24);
        Date expiryDate=new Date(expiryMillis);
        String token= Jwts.builder()
                .signWith(KEY)
                .setSubject(username)
               .setExpiration(expiryDate)
                .compact();
       return token;
    }

    public String decodeToken(String token) {
        Jws<Claims>claimsHolder =Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token);
               Claims claims=claimsHolder.getBody();
              String username= claims.getSubject();
              return username;

    }



}
