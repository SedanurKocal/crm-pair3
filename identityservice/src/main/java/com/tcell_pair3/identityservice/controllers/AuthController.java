package com.tcell_pair3.identityservice.controllers;

import com.tcell_pair3.identityservice.core.dtos.requests.LoginRequest;
import com.tcell_pair3.identityservice.core.dtos.requests.RegisterRequest;
import com.tcell_pair3.identityservice.service.abstracts.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("register")
    public void register(@RequestBody RegisterRequest request)
    {
        authService.register(request);
    }

    @PostMapping("login")
    public String login(@RequestBody LoginRequest request)
    {
        return authService.login(request);
    }
}
