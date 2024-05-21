package com.tcellpair3.authserver.controllers;

import com.tcellpair3.authserver.core.dtos.requests.LoginRequest;
import com.tcellpair3.authserver.core.dtos.requests.RegisterRequest;
import com.tcellpair3.authserver.service.abstracts.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("register")
    public void register(@RequestBody RegisterRequest request)
    {
        authService.register(request);
    }
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void login(@RequestBody LoginRequest request)
    {
        authService.login(request);
    }
}
