package com.tcell_pair3.identityservice.repositories;

import com.tcell_pair3.identityservice.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
