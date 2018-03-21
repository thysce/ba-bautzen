package org.wi16.its.passwords;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class SafelyStoringPasswords {

	public static void main(final String... args) throws NoSuchAlgorithmException, InvalidKeySpecException,
			InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

		// This is not a 100% safe way of storing user data, but here are a few hints:
		// https://nakedsecurity.sophos.com/2013/11/20/serious-security-how-to-store-your-users-passwords-safely/
		/*
		 * Encrypt all user data with the password as a key (maybe except user-id), Do
		 * not store passwords: hash them using multiple iterations, a strong hashing
		 * algorithm and a long salt, store the hashed passwords, salt and iterations
		 */

		// Creating a user record
		final String user = "Max";
		final String password = "Mustermann";
		String userdata = "Meine Daten sind sicher";
		final byte[] salt = getSalt(64); // use at least 32 bytes !!!!
		final int iterations = 400000; // use at least 100,000 !!!!
		final int hashLength = 256; // use at least 64 bytes !!!!
		final byte[] hash = generateHash(password, iterations, salt, hashLength);
		final byte[] userdataToStore = crypt(true, userdata.getBytes(StandardCharsets.UTF_8), password); // always use
																											// the same
																											// Charset
																											// !!!!

		// store username, hashed password, salt, iteration count, hashing algorithm,
		// encrypted userdata and encryption parameters
		// FORGET the actual password and plain userdata !!!!!!!!!!!!!!!

		// Login-process (user-name is given and user record is loaded)
		final String passwordInput = "Test-Mustermann"; // wrong in this example
		final byte[] passwordInputHash = generateHash(passwordInput, iterations, salt, hashLength);
		final boolean passwordCorrect = Arrays.equals(hash, passwordInputHash);
		if (passwordCorrect) {
			final String loadedUserdata = new String(crypt(false, userdataToStore, passwordInput),
					StandardCharsets.UTF_8);

			assert loadedUserdata.equals(userdata);

			// re-generate the password hash with different parameters and overwrite the old
			// user record ONCE PER YEAR, month or week to prevent brute-force-attacks

			System.out.println("Welcome, " + user + ", your data is: \"" + loadedUserdata + "\"");
		} else {
			System.out.println("Access denied");
		}
	}

	private static byte[] generateHash(final String password, final int iterations, final byte[] salt, final int length)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		char[] chars = password.toCharArray();

		PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, length);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = skf.generateSecret(spec).getEncoded();
		return hash;
	}

	private static byte[] crypt(final boolean encrypt, final byte[] data, final String password)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException {
		final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); // use a strong encryption algorithm with
																			// padding !!!!
		cipher.init(encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE,
				new SecretKeySpec(password.getBytes(StandardCharsets.UTF_8), "AES"));
		return cipher.doFinal(data);
	}

	private static byte[] getSalt(final int length) throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[length];
		sr.nextBytes(salt);
		return salt;
	}
}
