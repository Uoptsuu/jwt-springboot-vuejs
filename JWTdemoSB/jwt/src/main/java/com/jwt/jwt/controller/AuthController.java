package com.jwt.jwt.controller;

import com.jwt.jwt.entity.Role;
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

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173/")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final RoleService roleService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest){
        HashMap<String, String> map = new HashMap<>();
        if (new ValidationInput().validation(loginRequest.getUsername()+loginRequest.getPassword())) {
            map = authService.authenticateLogin(loginRequest);
            if (map.get("token").equals("")) {
                return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(map);
            } else {
                return ResponseEntity.ok(map);
            }
        } else {
            map.put("token", "");
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(map);
        }
    }

}
