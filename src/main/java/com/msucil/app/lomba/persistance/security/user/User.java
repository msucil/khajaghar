package com.msucil.app.lomba.persistance.security.user;

import java.util.Collections;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.msucil.app.lomba.core.persistance.AbstractAuditableEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractAuditableEntity<Long, User> implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String fullName;
	private String username;
	private String email;
	private String password;

	@Transient
	@Getter(AccessLevel.NONE)
	private Set<GrantedAuthority> authorities;

	private boolean accountNonExpired = true;

	private boolean accountNonLocked = true;

	private boolean credentialsNonExpired = true;

	private boolean enabled = true;

	public Set<GrantedAuthority> getAuthorities() {
		return Collections.emptySet();
	}
}
