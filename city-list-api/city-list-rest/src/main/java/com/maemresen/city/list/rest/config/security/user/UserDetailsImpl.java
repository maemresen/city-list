package com.maemresen.city.list.rest.config.security.user;

import com.maemresen.city.list.domain.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * App specific implementation of SpringSecurity user representation.
 */
public class UserDetailsImpl implements UserDetails {

	private User user;

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
		return user.getUserName();
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
}
