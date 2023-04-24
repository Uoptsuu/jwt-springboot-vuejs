package com.jwt.jwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;
import java.util.UUID;

import com.jwt.jwt.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private static final String keyEncode = "ma_hoa_123" ;

    public String generateToken(User user, SimpleGrantedAuthority authorities) {
        Algorithm algorithm = Algorithm.HMAC256(keyEncode.getBytes());
        return JWT
                .create()
                .withJWTId(UUID.randomUUID().toString())
                .withAudience(user.getUsername())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 60*60*60*1000))
                .withClaim("role", authorities.toString())
                .sign(algorithm);
    }

    public String getKey() {
        return keyEncode;
    }
}
