package com.jwt.jwt.service;

import com.jwt.jwt.model.request.LoginRequest;
import com.jwt.jwt.entity.Role;
import com.jwt.jwt.entity.User;
import com.jwt.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public String authenticateLogin(LoginRequest loginRequest) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            if (authenticate.isAuthenticated()) {
                User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
                Role role = userRepository.findRoleByUser(user.getUsername());
                user.setRole(role);
                SimpleGrantedAuthority authorities = new SimpleGrantedAuthority(role.getName());
                return jwtService.generateToken(user, authorities);
            } else return "";
        } catch (AuthenticationException authenticationException){
            return "";
        }
    }
}