package com.jwt.jwt.controller;

import com.jwt.jwt.model.request.LoginRequest;
import com.jwt.jwt.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest authRequest){
        String token = authService.authenticateLogin(authRequest);
        HashMap<String, String> map = new HashMap<>();
        if (token.equals("")){
            map.put("token", "");
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(map);
        } else {
            map.put("token", token);
            return ResponseEntity.ok(map);
        }
    }

}
