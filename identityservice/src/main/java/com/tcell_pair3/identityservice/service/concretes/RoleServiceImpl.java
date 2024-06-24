package com.tcell_pair3.identityservice.service.concretes;

import com.tcell_pair3.identityservice.core.dtos.requests.RoleRequest;
import com.tcell_pair3.identityservice.core.mapper.RoleMapper;
import com.tcell_pair3.identityservice.entities.Role;
import com.tcell_pair3.identityservice.repositories.RoleRepository;
import com.tcell_pair3.identityservice.service.abstracts.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role addRole(RoleRequest request) {
        Role role = RoleMapper.INSTANCE.roleFromRequest(request);
        System.out.println("Mapped Role Name: " + role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(int id) {
    }

    @Override
    public Optional<Role> getRoleById(int id) {
        return roleRepository.findById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return List.of();
    }
}
