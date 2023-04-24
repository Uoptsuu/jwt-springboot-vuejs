package com.jwt.jwt.controller;

import com.jwt.jwt.model.request.LoginRequest;
import com.jwt.jwt.service.AuthService;
import com.jwt.jwt.service.ValidationInput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173/")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest){
        HashMap<String, String> map = new HashMap<>();
        if (new ValidationInput().validation(loginRequest.getUsername()+loginRequest.getPassword())) {
            String token = authService.authenticateLogin(loginRequest);
            if (token.equals("")) {
                map.put("token", "");
                return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(map);
            } else {
                map.put("token", token);
                return ResponseEntity.ok(map);
            }
        } else {
            map.put("token", "");
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(map);
        }
    }

}
