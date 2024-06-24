package com.tcell_pair3.identityservice.service.abstracts;

import com.tcell_pair3.identityservice.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    void add(User user);

    Optional<User> getUserById(int id);
}
