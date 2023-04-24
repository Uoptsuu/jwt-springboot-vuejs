package com.jwt.jwt.config;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwt.jwt.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import static java.time.LocalDateTime.*;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, @NonNull HttpServletResponse httpServletResponse, @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = httpServletRequest.getHeader(AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            try {
                String token = authHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256(jwtService.getKey().getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(token);
                LocalDateTime expireTime = Instant.ofEpochMilli(decodedJWT.getExpiresAt().getTime())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();
                LocalDateTime nbfTime = Instant.ofEpochMilli(decodedJWT.getNotBefore().getTime())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();
                if (!expireTime.isAfter(now()) || nbfTime.isAfter(now())){
                    throw new Exception("Token time invalid.");
                } else {
                    String username = decodedJWT.getAudience().toString();
                    String role = decodedJWT.getClaim("role").asString();
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority(role));
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    filterChain.doFilter(httpServletRequest, httpServletResponse);
                }
            } catch (Exception exception){
                httpServletResponse.setHeader("error", exception.getMessage());
                httpServletResponse.setStatus(FORBIDDEN.value());
                HashMap<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                httpServletResponse.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(httpServletResponse.getOutputStream(), error);
            }
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
