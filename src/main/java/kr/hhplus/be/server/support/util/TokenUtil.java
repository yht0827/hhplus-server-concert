package kr.hhplus.be.server.support.util;

import org.jasypt.util.text.AES256TextEncryptor;

public class TokenUtil {

	private static final String SECRET_KEY = "MySecretKey"; // 반드시 안전하게 관리

	// 문자열 암호화
	public static String encrypt(String data) {
		AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
		textEncryptor.setPassword(SECRET_KEY);
		return textEncryptor.encrypt(data);
	}

	// 문자열 복호화
	public static String decrypt(String encryptedData) {
		AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
		textEncryptor.setPassword(SECRET_KEY);
		return textEncryptor.decrypt(encryptedData);
	}
}
