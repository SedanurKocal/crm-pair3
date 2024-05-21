package com.tcellpair3.authserver.service.abstracts;

import com.tcellpair3.authserver.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void add(User user);
}
