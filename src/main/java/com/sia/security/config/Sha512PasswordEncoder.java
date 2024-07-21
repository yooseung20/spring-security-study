package com.sia.security.config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.password.PasswordEncoder;

public class Sha512PasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return hashWithSHA512(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		String hashPassword = encode(rawPassword);
		return encodedPassword.equals(hashPassword);
	}

	private String hashWithSHA512(String password) {
		StringBuilder stringBuilder = new StringBuilder();

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] digest = md.digest(password.getBytes());
			for (byte b : digest) {
				stringBuilder.append(Integer.toHexString(0xFF & b));
			}
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("invalid algorithm");
		}

		return stringBuilder.toString();
	}
}
