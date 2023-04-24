package com.jwt.jwt.service;

import com.jwt.jwt.model.request.LoginRequest;
import com.jwt.jwt.entity.Role;
import com.jwt.jwt.entity.User;
import com.jwt.jwt.repository.RoleRepository;
import com.jwt.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public HashMap<String, String> authenticateLogin(LoginRequest loginRequest) {
        HashMap<String, String> response = new HashMap<>();
        String token = "";
        String roleName = "";
        String message = "";
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            if (authenticate.isAuthenticated()) {
                User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
                Role role = userRepository.findRoleByUser(user.getUsername());
                user.setRole(role);
                SimpleGrantedAuthority authorities = new SimpleGrantedAuthority(role.getName());
                token = jwtService.generateToken(user, authorities);
                roleName = role.getName();
            }
        } catch (AuthenticationException ignored){
        } finally {
            response.put("token", token);
            response.put("role", roleName);
        }
        return response;
    }
}