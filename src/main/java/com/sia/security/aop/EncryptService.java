package com.sia.security.aop;

import org.springframework.stereotype.Service;

@Service
public class EncryptService {

	public String encrypt(String before) {
		return "encrypted_" + before;
	}
}
