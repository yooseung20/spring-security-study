package com.sia.security.dto;

import com.sia.security.annotation.CustomEncryption;

public record HelloRequestRecord(
	String id,
	@CustomEncryption String password
) {
}
