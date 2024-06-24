package com.tcell_pair3.identityservice.service.abstracts;

import com.tcell_pair3.identityservice.core.dtos.requests.LoginRequest;
import com.tcell_pair3.identityservice.core.dtos.requests.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest request);

    String login(LoginRequest loginRequest);
}
