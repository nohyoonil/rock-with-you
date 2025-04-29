package com.example.rock.jwt;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.issuer}")
    private String issuer;
    @Value("{${jwt.secretKey}")
    private String secretKey;

    public String generateToken(String socialId, String name, String pfp, Duration expiredAt) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(issuer)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expiredAt.toMillis()))
                .setSubject(socialId)
                .claim("name", name)
                .claim("pfp", pfp)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
