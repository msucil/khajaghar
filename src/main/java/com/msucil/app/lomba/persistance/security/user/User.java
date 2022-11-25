package com.msucil.app.lomba.persistance.security.user;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import com.msucil.app.lomba.core.persistance.AbstractAuditableEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractAuditableEntity<Long, User>{

	private static final long serialVersionUID = 1L;
	
	private String fullName;
	private String username;
	private String email;
	private String password;
	
	@Transient
	private Set<GrantedAuthority> authorities;

	private boolean accountNonExpired = true;

	private boolean accountNonLocked = true;

	private boolean credentialsNonExpired = true;

	private boolean enabled = true;
}
