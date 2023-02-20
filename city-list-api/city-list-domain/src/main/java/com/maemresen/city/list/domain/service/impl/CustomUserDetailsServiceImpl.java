package com.maemresen.city.list.domain.service.impl;

import com.maemresen.city.list.domain.service.model.UserDetailsImpl;
import com.maemresen.city.list.domain.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Custom implementation to tell Spring Security about how and where user's should be fetched to apply authentication.
 */
@RequiredArgsConstructor
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUserName(username)
			.map(UserDetailsImpl::new)
			.orElseThrow(() -> new UsernameNotFoundException("User with " + username + " not found "));
	}
}

