package com.tcellpair3.authserver.service.concretes;

import com.tcellpair3.authserver.core.dtos.requests.LoginRequest;
import com.tcellpair3.authserver.core.exception.type.UnauthorizedException;
import com.tcellpair3.authserver.core.mapper.AuthMapper;
import com.tcellpair3.authserver.entities.User;
import com.tcellpair3.authserver.service.abstracts.AuthService;
import com.tcellpair3.authserver.core.dtos.requests.RegisterRequest;
import com.tcellpair3.authserver.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    @Override
    public void register(RegisterRequest request) {
        User user = AuthMapper.INSTANCE.userFromRequest(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.add(user);

    }

    @Override
    public void login(LoginRequest loginRequest) {
        // TODO: Handle Exception.
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        if(!authentication.isAuthenticated())
            throw new UnauthorizedException("E-posta ya da şifre yanlış");
    }
}
