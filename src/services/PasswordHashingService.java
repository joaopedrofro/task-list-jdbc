package services;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordHashingService {
	private static final int SALT_LENGHT = 16;
	private static final int INTERATIONS = 65000;
	private static final int KEY_LENGHT = 256;

	public static String hashPassword(String password, byte[] salt) {
		try {
			PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, INTERATIONS, KEY_LENGHT);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = skf.generateSecret(spec).getEncoded();
			return Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] getSalt() {
		SecureRandom sr = new SecureRandom();
		byte[] salt = new byte[SALT_LENGHT];
		sr.nextBytes(salt);
		return salt;
	}
}
