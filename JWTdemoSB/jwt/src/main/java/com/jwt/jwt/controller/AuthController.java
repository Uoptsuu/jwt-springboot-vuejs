package com.jwt.jwt.controller;

import com.jwt.jwt.model.request.LoginRequest;
import com.jwt.jwt.service.AuthService;
import com.jwt.jwt.service.RoleService;
import com.jwt.jwt.service.UserService;
import com.jwt.jwt.service.ValidationInput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173/")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest){
        HashMap<String, Object> response = new HashMap<>();
        if (!Objects.equals(loginRequest.getUsername(), "") && !Objects.equals(loginRequest.getPassword(), "")) {
            response = authService.authenticateLogin(loginRequest);
            HashMap<String, Object> data = (HashMap<String, Object>) response.get("data");
            if (data.get("token").equals("")) {
                return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(response);
            } else {
                return ResponseEntity.ok(response);
            }
        } else {
            response.put("responseCode", "02");
            response.put("message", "Input empty.");
            response.put("data", "");
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(response);
        }
    }

}
