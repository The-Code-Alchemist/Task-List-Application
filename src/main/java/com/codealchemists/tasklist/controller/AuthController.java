package com.codealchemists.tasklist.controller;

import com.codealchemists.tasklist.dto.AuthRequest;
import com.codealchemists.tasklist.model.User;
import com.codealchemists.tasklist.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService auth;

    public AuthController(AuthService auth) {
        this.auth = auth;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest req) {
        String token = auth.login(req.username(), req.password());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody AuthRequest req) {
        User newUser = auth.register(req.username(), req.password());
        return ResponseEntity.ok(newUser);

    }
}
