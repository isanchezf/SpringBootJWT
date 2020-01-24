package com.example.jwt.demojwt;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.example.jwt.demojwt.utils.ManagerToken;
import java.util.Optional;

public class DemoApplicationTests {

	@Test()
	public void GenerateTokenTest() {
		
		// Arrange
		String secret = "lazo123";
		
		// Action
		String token = ManagerToken.GenerateToken(secret);
		
		// Assertion
		assertTrue(token != null);
	}

}
