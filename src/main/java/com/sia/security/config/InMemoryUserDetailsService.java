package com.sia.security.config;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class InMemoryUserDetailsService implements UserDetailsService {
	private final List<UserDetails> users;

	public InMemoryUserDetailsService(List<UserDetails> users) {
		this.users = users;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.users.stream()
			.filter(user -> username.equals(user.getUsername()))
			.findFirst()
			.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}
}
