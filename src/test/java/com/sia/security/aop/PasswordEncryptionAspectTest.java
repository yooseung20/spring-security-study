package com.sia.security.aop;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sia.security.dto.HelloRequest;
import com.sia.security.dto.HelloRequestRecord;

@ExtendWith(MockitoExtension.class)
class PasswordEncryptionAspectTest {

	PasswordEncryptionAspect passwordEncryptionAspect;
	@Mock
	EncryptService encryptService;

	@BeforeEach
	void setUp() {
		passwordEncryptionAspect = new PasswordEncryptionAspect(encryptService);

	}

	@Test
	void test() {
		//given
		HelloRequest request = new HelloRequest("id", "password");
		given(encryptService.encrypt(anyString())).willReturn("encrypted_password");

		//when
		passwordEncryptionAspect.fieldEncryption(request);

		//then
		assertThat(request.getPassword()).isEqualTo("encrypted_password");
	}

	@Test
	@DisplayName("final, static 필드는 값을 재정의 할 수 없어서 exception 발생")
	void testFinalField() {
		//given
		HelloRequestRecord request = new HelloRequestRecord("id", "password");
		given(encryptService.encrypt(anyString())).willReturn("encrypted_password");

		assertThrows(RuntimeException.class, () -> {
			passwordEncryptionAspect.fieldEncryption(request);
		});
	}



}