package com.tcell_pair3.identityservice.service.concretes;

import com.tcell_pair3.identityservice.service.abstracts.UserService;
import com.tcell_pair3.identityservice.core.exception.type.UnauthorizedException;
import com.tcell_pair3.identityservice.entities.User;
import com.tcell_pair3.identityservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UnauthorizedException("User not found with email or password "));
    }
}
