package com.tcellpair3.authserver.repositories;

import com.tcellpair3.authserver.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);

}
