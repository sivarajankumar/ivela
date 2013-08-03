package org.ivela.offline.tests;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.ivela.offline.commons.Constants;
import org.junit.Test;

public class MainTester {

	@Test
	public void test() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		System.out.println(getHash("jd.comment@gmail.com", "laika2004"));
	}

	
	public String getHash(String email, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException
	  {
	    byte[] salt = email.getBytes();

	    MessageDigest digest = MessageDigest.getInstance("SHA-1");
	    digest.reset();
	    digest.update(salt);
	    byte[] input = digest.digest(password.getBytes("UTF-8"));
	    for (int i = 0; i < 1000; i++) {
	      digest.reset();
	      input = digest.digest(input);
	    }
	    BigInteger bigInt = new BigInteger(1, input);
	    String hashtext = bigInt.toString(16).toUpperCase();

	    return hashtext;
	  }
	
}
