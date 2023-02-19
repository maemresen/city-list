package com.maemresen.city.list.rest.config.security;

import com.maemresen.city.list.domain.entity.User;
import com.maemresen.city.list.domain.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

