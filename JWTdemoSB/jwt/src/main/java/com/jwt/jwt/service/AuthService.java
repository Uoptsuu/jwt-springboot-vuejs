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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public HashMap<String, Object> authenticateLogin(LoginRequest loginRequest) {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> data = new HashMap<>();
        String token = "";
        String roleName = "";
        String message = "";
        String responseCode = "";
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            if (authenticate.isAuthenticated()) {
                User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
                Role role = userRepository.findRoleByUser(user.getUsername());
                user.setRole(role);
                SimpleGrantedAuthority authorities = new SimpleGrantedAuthority(role.getName());
                token = jwtService.generateToken(user, authorities);
                roleName = role.getName();
                message = "Login successful.";
                responseCode = "00";
            } else {
                message = "Password invalid";
                responseCode = "08";
            }
        } catch (AuthenticationException authenticationException){
            message = authenticationException.getMessage();
            responseCode = "05";
        } finally {
            data.put("token",token);
            data.put("role", roleName);
            response.put("data", data);
            response.put("message",message);
            response.put("responseCode",responseCode);
        }
        return response;
    }

}