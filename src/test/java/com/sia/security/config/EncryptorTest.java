package com.sia.security.config;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

public class EncryptorTest {

	@Test
	void standardByteEncryptWithSalt() {
		String salt = KeyGenerators.string().generateKey();
		String password = "secret";
		String value = "HELLO";

		BytesEncryptor bytesEncryptor = Encryptors.standard(password, salt);
		byte[] encrypt = bytesEncryptor.encrypt(value.getBytes());
		byte[] decrypt = bytesEncryptor.decrypt(encrypt);

		assertThat(decrypt).isEqualTo(value.getBytes());
	}

	@Test
	void strongByteEncryptWithSalt() {
		String salt = KeyGenerators.string().generateKey();
		String password = "secret";
		String value = "HELLO";
		BytesEncryptor bytesEncryptor = Encryptors.stronger(password, salt);

		byte[] encrypted = bytesEncryptor.encrypt(value.getBytes());
		byte[] decrypted = bytesEncryptor.decrypt(encrypted);

		assertThat(decrypted).isEqualTo(value.getBytes());
	}

	@Test
	void standardTextEncryptWithSalt() {
		String salt = KeyGenerators.string().generateKey();
		String password = "secret";
		String value = "HELLO";

		TextEncryptor textEncryptor = Encryptors.text(password, salt);
		String encrypt = textEncryptor.encrypt(value);
		String decrypt = textEncryptor.decrypt(encrypt);

		assertThat(decrypt).isEqualTo(value);
	}

	@Test
	void strongTextEncryptWithSalt() {
		String salt = KeyGenerators.string().generateKey();
		String password = "secret";
		String value = "HELLO";

		TextEncryptor textEncryptor = Encryptors.delux(password, salt);
		String encrypt = textEncryptor.encrypt(value);
		String decrypt = textEncryptor.decrypt(encrypt);

		assertThat(decrypt).isEqualTo(value);
	}

	@Test
	void noOpTextEncryptWithSalt() {
		String salt = KeyGenerators.string().generateKey();
		String password = "secret";
		String value = "HELLO";

		TextEncryptor textEncryptor = Encryptors.noOpText();
		String encrypt = textEncryptor.encrypt(value);
		String decrypt = textEncryptor.decrypt(encrypt);

		assertThat(encrypt).isEqualTo(value);
		assertThat(encrypt).isEqualTo(decrypt);
	}


}
