package com.sia.security.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Sha512PasswordEncoderTest {

	@Test
	void sha512Test() {
		String password = "sia.yoo";
		Sha512PasswordEncoder encoder = new Sha512PasswordEncoder();
		encoder.encode(password);
	}

}