package eu.servicemix.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String rawPassword = "temp";
		String encodedPassword = encoder.encode(rawPassword);
		
		System.out.println(encodedPassword);
		System.out.println(encodedPassword.length());
	}

}
