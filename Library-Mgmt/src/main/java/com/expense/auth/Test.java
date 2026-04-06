package com.expense.auth;

import java.security.SecureRandom;
import java.util.Base64;

public class Test {
	
	public class JwtKeyGenerator {
	    public static void main(String[] args) {
	        // Generate 32 random bytes (256 bits)
	        byte[] randomBytes = new byte[32];
	        new SecureRandom().nextBytes(randomBytes);
	        
	        // Encode to Base64 string for easy storage in .env or application.properties
	        String secretKey = Base64.getEncoder().encodeToString(randomBytes);
	        
	        System.out.println("Your Secure JWT Key: " + secretKey);
	    }

}
}
