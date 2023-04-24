package com.jwt.jwt.controller;

import com.jwt.jwt.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/user")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173/")
public class UserController {

    @GetMapping("/home")
    public ResponseEntity<String> login(){
        return ResponseEntity.ok("Hello");
    }
}
