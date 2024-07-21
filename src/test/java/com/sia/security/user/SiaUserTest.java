package com.sia.security.user;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

class SiaUserTest {

	@Test
	void siaUserTest() {
		// given
		SiaUser user = new SiaUser();

		// then
		assertThat(user.getUsername()).isEqualTo("sia.yoo");
		assertThat(user.getPassword()).isEqualTo("12345");
		assertThat(user.getAuthorities()).hasSize(1);

		Optional<? extends GrantedAuthority> read = user.getAuthorities()
			.stream()
			.filter(authority -> authority.equals("READ"))
			.findFirst();

		read.ifPresent(authority -> assertThat(authority.getAuthority()).isEqualTo("READ"));

	}

}