package com.tcell_pair3.identityservice.core.mapper;

import com.tcell_pair3.identityservice.core.dtos.requests.RegisterRequest;
import com.tcell_pair3.identityservice.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthMapper {

    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    User userFromRequest(RegisterRequest request);
}
