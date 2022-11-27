package com.msucil.app.lomba.service.security.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.msucil.app.lomba.persistance.security.user.User;
import com.msucil.app.lomba.persistance.security.user.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserDetailServiceImplTest {

	@Mock
	private UserRepository repository;

	@InjectMocks
	private UserDetailServiceImpl service;

	@Test
	void testLoadUserByUsernameShouldFindUserByEmail() {
		
		 var username = "email@email.com";
		 
		 var validUser = new User();
		 validUser.setFullName("admin");
		 validUser.setEmail(username);
		 validUser.setUsername("admin");
		 validUser.setPassword("password");
		 
		 
		when(repository.findByEmail(any(String.class))).thenReturn(Optional.of(validUser));
		 
		UserDetails user = service.loadUserByUsername(username);
		
		assertThat(user).isNotNull();
		assertThat(user.getAuthorities()).isEmpty();
		assertThat(user.getPassword()).isEqualTo(validUser.getPassword());
		assertThat(user.getUsername()).isEqualTo(validUser.getUsername());
		
		var argCaptor =  ArgumentCaptor.forClass(String.class);
		
		verify(repository, times(1)).findByEmail(argCaptor.capture());
		
		assertThat(argCaptor.getValue()).isEqualTo(username);
		
		verify(repository, times(0)).findByUsername(argCaptor.capture());
		 
	}
	
	@Test
	void testLoadUserByUsernameShouldFindUserByUsername() {
		
		 var username = "admin";
		 
		 var validUser = new User();
		 validUser.setFullName("admin");
		 validUser.setEmail("admin@admin.com");
		 validUser.setUsername(username);
		 validUser.setPassword("password");
		 
		 
		when(repository.findByUsername(any(String.class))).thenReturn(Optional.of(validUser));
		 
		UserDetails user = service.loadUserByUsername(username);
		
		assertThat(user).isNotNull();
		assertThat(user.getAuthorities()).isEmpty();
		assertThat(user.getPassword()).isEqualTo(validUser.getPassword());
		assertThat(user.getUsername()).isEqualTo(validUser.getUsername());
		
		var argCaptor =  ArgumentCaptor.forClass(String.class);
		
		verify(repository, times(1)).findByUsername(argCaptor.capture());
		
		assertThat(argCaptor.getValue()).isEqualTo(username);
		
		verify(repository, times(0)).findByEmail(argCaptor.capture());
		 
	}
	
	@Test
	void testLoadUserByUsernameShouldThrowUsernameNotFoundException() {
		var username = "username";
		
		when(repository.findByUsername(any(String.class))).thenReturn(Optional.empty());
		
		UsernameNotFoundException ex = assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername(username));
		
		assertThat(ex.getMessage()).isEqualTo(username + " does not exist");
		
		
	}

}
