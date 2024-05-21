package com.tcellpair3.authserver.core.mapper;

import com.tcellpair3.authserver.core.dtos.requests.LoginRequest;
import com.tcellpair3.authserver.core.dtos.requests.RegisterRequest;
import com.tcellpair3.authserver.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthMapper {
    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);


    User userFromRequest(RegisterRequest request);

}
