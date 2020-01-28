package com.example.jwt.demojwt;

import static org.junit.Assert.assertTrue;

import com.example.jwt.demojwt.utils.ManagerToken;

import org.junit.jupiter.api.Test;

public class DemoApplicationTests {

	@Test()
	public void GenerateTokenTest(){
		//Arrange
		//Action
		String token = ManagerToken.GenerateToken("IvanSecret");
		//Assert
		assertTrue(token != null);
	}



	@Test()
	public void DecodeTokenTest() throws Exception {
		
		// Arrange
		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJjbGllbnRlLWRlLWRlc2Fycm9sbG8iLCJzdWIiOiJzYy1hcGlzcnVtYm8tZW5kcG9pbnQtZGV2QHRlcnBlbC1ndGljLW9wZW5zaGlmdC5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsImlkZW50aWZpY2Fkb3JBcGxpY2FjaW9uIjoiMTIzNDU2Nzg5IiwiaWRDbGllbnRlIjoiNjQxMzEiLCJpc3MiOiJzYy1hcGlzcnVtYm8tZW5kcG9pbnQtZGV2QHRlcnBlbC1ndGljLW9wZW5zaGlmdC5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsInVzdWFyaW8iOiJwaWxvdG8ubGF6byIsImV4cCI6MTU4MDMwNzk1MywiaWF0IjoxNTgwMjIxNTUzLCJlbWFpbCI6InNjLWFwaXNydW1iby1lbmRwb2ludC1kZXZAdGVycGVsLWd0aWMtb3BlbnNoaWZ0LmlhbS5nc2VydmljZWFjY291bnQuY29tIn0.AQ44oPZn1HW4EUhZP6sfAvzu96UB7LUn773mAbYhhGPSiD1gSQLPTYZL-5sDLy01xIueuupNqDUD_lKY55Iy_nvzOI-sruGWaTfaAiBCiYScC7oYHxt4W-1dqzJ20KfoibonMEt0GQ4y8XTQRmTZa3S7ZY5tFraUKXJg5TC1HTWrE9kgfWE3iDuYHhtDzkWJz7vhh3to-8PhCvAdbGxxgianoj-oFGK5QpNi1iZcUclLeV1LvA2WNqOrS73p7jHuBBS-qKnW_ZzKXZVhsUzTDa4ZVeRDmpSNNZ2s6e4TCVqJuRtHY134KZcBt1OfEyQ-ZCGaQQD0Tw6DjrwjITUrWQ";

		// Action
		Object resultado = ManagerToken.DecodeToken(token);
		
		// Assertion
		assertTrue(resultado!=null);
	}

}
