package com.tcell_pair3.identityservice.core.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleWithUserResponse {

    private int userId;

    private int roleId;
}
