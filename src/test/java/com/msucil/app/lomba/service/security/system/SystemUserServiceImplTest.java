package com.msucil.app.lomba.service.security.system;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.msucil.app.lomba.core.persistance.CrudService;
import com.msucil.app.lomba.persistance.security.user.User;
import com.msucil.app.lomba.persistance.security.user.UserQueryRepository;

@ExtendWith(MockitoExtension.class)
class SystemUserServiceImplTest {

	@Mock
	CrudService<User, Long> crudService;

	@Mock
	UserQueryRepository queryRepo;

	@Mock
	PasswordEncoder passwordEncoder;

	@InjectMocks
	SystemUserServiceImpl userService;

	@Test
	void testRegisterSystemUserShouldReturnSavedUser() {
		
		var hashPassword = "hashpassword";
		var rawPassword = "password";
		
		var form = new User();
		form.setFullName("admin");
		form.setEmail("admin@gmail.com");
		form.setPassword(rawPassword);
		form.setUsername("admin");
		
		var savedUser = new User();
		savedUser.setId(1L);
		savedUser.setFullName(form.getFullName());
		savedUser.setUsername(form.getUsername());
		savedUser.setEmail(form.getEmail());
		savedUser.setPassword(hashPassword);
		
		when(passwordEncoder.encode(any(CharSequence.class))).thenReturn(hashPassword);
		when(crudService.save(any(User.class))).thenReturn(savedUser);
		
		var user = userService.registerSystemUser(form);
		
		assertThat(user).isNotNull();
		assertThat(user.getId()).isOne();
		assertThat(user.getFullName()).isEqualTo(form.getFullName());
		assertThat(user.getEmail()).isEqualTo(form.getEmail());
		assertThat(user.getPassword()).isEqualTo(hashPassword);
		assertThat(user.getAuthorities()).isEmpty();
		
		var passwordCaptor = ArgumentCaptor.forClass(String.class);
		
		verify(passwordEncoder, times(1)).encode(passwordCaptor.capture());
		assertThat(passwordCaptor.getValue()).isEqualTo(rawPassword);
		
		var userCaptor = ArgumentCaptor.forClass(User.class);
		
		verify(crudService, times(1)).save(userCaptor.capture());
		
		assertThat(userCaptor.getValue().getFullName()).isEqualTo(form.getFullName());
		assertThat(userCaptor.getValue().getId()).isNull();
		
	}

	@Test
	void testCanRegisterSystemUserShouldReturnTrue() {
		
		when(queryRepo.totalCount()).thenReturn(0L);
		
		assertThat(userService.canRegisterSystemUser()).isTrue();
		
		verify(queryRepo, times(1)).totalCount();
	}
	
	@Test
	void testCanRegisterSystemUserShouldReturnFalse() {
		
		when(queryRepo.totalCount()).thenReturn(1L);
		
		assertThat(userService.canRegisterSystemUser()).isFalse();
		
		verify(queryRepo, times(1)).totalCount();
	}

}
