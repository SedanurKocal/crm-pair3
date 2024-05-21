package com.tcellpair3.authserver.service.abstracts;

import com.tcellpair3.authserver.core.dtos.requests.LoginRequest;
import com.tcellpair3.authserver.core.dtos.requests.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest request);
    void login(LoginRequest request);
}
