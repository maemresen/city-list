package com.maemresen.city.list.domain.service.model;

import com.maemresen.city.list.domain.entity.User;
import com.maemresen.city.list.domain.entity.enums.RoleEnum;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

/**
 * App specific implementation of SpringSecurity user representation.
 */
public class UserDetailsImpl implements UserDetails {

	@Getter
	private final User user;

	@Getter
	private final Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(User user) {
		this.user = user;
		this.authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName().name()));
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public UUID getUuid(){
		return this.user.getUuid();
	}

	public RoleEnum getRoleName(){
		return this.user.getRole().getName();
	}
}
