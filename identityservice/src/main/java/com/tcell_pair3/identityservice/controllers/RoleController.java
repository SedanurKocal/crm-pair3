package com.tcell_pair3.identityservice.controllers;


import com.tcell_pair3.identityservice.core.dtos.requests.RoleRequest;
import com.tcell_pair3.identityservice.core.dtos.responses.RoleWithUserResponse;
import com.tcell_pair3.identityservice.entities.Role;
import com.tcell_pair3.identityservice.entities.User;
import com.tcell_pair3.identityservice.service.abstracts.RoleService;
import com.tcell_pair3.identityservice.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;
    private final UserService userService;

    @PostMapping("/createRoles")
    public ResponseEntity<Role> addRole(@RequestBody RoleRequest role) {
        Role newRole = roleService.addRole(role);
        return ResponseEntity.ok(newRole);
    }
    @PostMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<RoleWithUserResponse> assignRoleToUser(@PathVariable int userId, @PathVariable int roleId) {
        Optional<User> userOptional = userService.getUserById(userId);
        Optional<Role> roleOptional = roleService.getRoleById(roleId);

        if (userOptional.isPresent() && roleOptional.isPresent()) {
            User user = userOptional.get();
            Role role = roleOptional.get();

            user.getRoles().add(role);
            userService.add(user);

            RoleWithUserResponse roleWithUserResponse = new RoleWithUserResponse(userId, roleId);
            return ResponseEntity.ok(roleWithUserResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
