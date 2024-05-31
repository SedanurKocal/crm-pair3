package com.tcell_pair3.identityservice.service.abstracts;

import com.tcell_pair3.identityservice.core.dtos.requests.RoleRequest;
import com.tcell_pair3.identityservice.entities.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role addRole(RoleRequest request);

    void deleteRole(int id);

    Optional<Role> getRoleById(int id);

    List<Role> getAllRoles();
}
