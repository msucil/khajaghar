package com.msucil.app.lomba.web.view.security.account.user;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.msucil.app.lomba.persistance.security.user.User;


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
