package com.sia.security.dto;

import com.sia.security.annotation.CustomEncryption;

import lombok.Getter;

@Getter
public class HelloRequest{
	private String id;
	@CustomEncryption
	private String password;

	public HelloRequest(String id, String password) {
		this.id = id;
		this.password = password;
	}
}
