package com.tcellpair3.authserver.service.concretes;

import com.tcellpair3.authserver.core.exception.type.UnauthorizedException;
import com.tcellpair3.authserver.entities.User;
import com.tcellpair3.authserver.repositories.UserRepository;
import com.tcellpair3.authserver.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UnauthorizedException("User not found with email or password "));
    }
}
