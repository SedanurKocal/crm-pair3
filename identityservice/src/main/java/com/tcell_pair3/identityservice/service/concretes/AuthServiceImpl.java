package com.tcell_pair3.identityservice.service.concretes;

import com.tcell_pair3.identityservice.core.exception.type.UnauthorizedException;
import com.tcell_pair3.identityservice.core.mapper.AuthMapper;
import com.tcell_pair3.identityservice.core.service.abstracts.ValidateEmailService;
import com.tcell_pair3.identityservice.entities.User;
import com.tcell_pair3.identityservice.service.abstracts.AuthService;
import com.tcell_pair3.identityservice.service.abstracts.UserService;
import com.tcell_pair3.identityservice.core.dtos.requests.LoginRequest;
import com.tcell_pair3.identityservice.core.dtos.requests.RegisterRequest;
import com.turkcell.tcell.core.security.BaseJwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final BaseJwtService baseJwtService;
    private final ValidateEmailService validateEmailService;

    @Override
    public void register(RegisterRequest request) {
        validateEmailService.validateEmail(request.getEmail());
        User user = AuthMapper.INSTANCE.userFromRequest(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.add(user);

    }

    @Override
    public String login(LoginRequest loginRequest) {
        validateEmailService.validateEmail(loginRequest.getEmail());
        // TODO: Handle Exception.
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        if(!authentication.isAuthenticated())
            throw new UnauthorizedException("E-posta ya da şifre yanlış");

        UserDetails user = userService.loadUserByUsername(loginRequest.getEmail());
        // TODO: Refactor
        Map<String,Object> claims = new HashMap<>();
        List<String> roles = user
                .getAuthorities()
                .stream()
                .map((role) -> role.getAuthority())
                .toList();
        claims.put("roles", roles);
        return baseJwtService.generateToken(loginRequest.getEmail(), claims);
    }
}
