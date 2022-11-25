package com.msucil.app.lomba.web.view.security;

import org.mapstruct.Mapper;

import com.msucil.app.lomba.core.web.AbstractMapper;
import com.msucil.app.lomba.persistance.security.user.User;


@Mapper(componentModel = "spring")
public interface UserMapper extends AbstractMapper<User, UserDto, Long>{
	
}

