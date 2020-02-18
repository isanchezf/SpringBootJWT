package com.example.jwt.demojwt;

import static org.junit.Assert.assertTrue;
import com.example.jwt.demojwt.utils.TokenGenerator;

import org.junit.Test;

public class TokenGeneratorTests {

    TokenGenerator tokenGenerator;

    public TokenGeneratorTests() throws Exception {
        tokenGenerator = new TokenGenerator();
        tokenGenerator.setKeys();
    }


    @Test()
	public void GenerateTokenTest() throws Exception {
        // Arrange
        String privateKey = tokenGenerator.getPrivateKey();
        

		// Action
        String token = TokenGenerator.generateJwtToken(privateKey);

		// Assert
		assertTrue(token != null);
	}
    
    @Test()
	public void DecodeTokenMethodTwoTest() throws Exception {

        //Arrange
        String privateKey = tokenGenerator.getPrivateKey();
        String publicKey = tokenGenerator.getPublicKey();
		String token = TokenGenerator.generateJwtToken(privateKey);

		// Action
		Object resultado = tokenGenerator.DecodeToken(token, publicKey);

		// Assertion
		assertTrue(resultado != null);
	}
}