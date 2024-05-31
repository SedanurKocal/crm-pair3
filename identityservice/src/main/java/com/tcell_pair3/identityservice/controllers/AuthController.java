package com.tcell_pair3.identityservice.controllers;

import com.tcell_pair3.identityservice.core.dtos.requests.LoginRequest;
import com.tcell_pair3.identityservice.core.dtos.requests.RegisterRequest;
import com.tcell_pair3.identityservice.core.dtos.requests.RoleRequest;
import com.tcell_pair3.identityservice.entities.Role;
import com.tcell_pair3.identityservice.service.abstracts.AuthService;
import com.tcell_pair3.identityservice.service.abstracts.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RoleService roleService;
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
