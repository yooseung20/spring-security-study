package com.sia.security.config;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;

public class KeyGeneratorTest {

	@Test
	@DisplayName("같은 byte key 생성")
	void bytesKeyGenerateWithSameKey() {
		BytesKeyGenerator generator = KeyGenerators.shared(16);
		byte[] key1 = generator.generateKey();
		byte[] key2 = generator.generateKey();

		assertThat(key1).isEqualTo(key2);
	}

	@Test
	@DisplayName("다른 byte key 생성")
	void bytesKeyGenerateWithDifferentKey() {
		BytesKeyGenerator generator = KeyGenerators.secureRandom(16);
		byte[] key1 = generator.generateKey();
		byte[] key2 = generator.generateKey();

		assertThat(key1).isNotEqualTo(key2);
	}

	@Test
	@DisplayName("문자열 key 생성")
	void stringKeyGenerateWithDifferentKey() {
		StringKeyGenerator generator = KeyGenerators.string();
		String key1 = generator.generateKey();
		String key2 = generator.generateKey();

		assertThat(key1).isNotEqualTo(key2);
	}

}
