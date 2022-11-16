package com.msucil.app.lomba.web.view.security;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.msucil.app.lomba.persistance.security.user.User;
import com.msucil.app.lomba.web.view.security.UserMapper;


class UserMapperTest {

	private UserMapper mapper = Mappers.getMapper(UserMapper.class);
	
	@Test
	void testMapToDto() {
		var u = new User();
		u.setId(1L);
		u.setFullName("fullName");
		
		var d = mapper.mapToDto(u);
		assertEquals(u.getFullName(), d.getFullName());
		assertEquals(u.getId(), d.getId());
		assertEquals(u.getCreatedAt(), d.getCreatedAt());
	}

}
