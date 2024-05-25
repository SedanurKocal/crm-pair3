package com.tcell_pair3.identityservice.service.abstracts;

import com.tcell_pair3.identityservice.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void add(User user);
}
