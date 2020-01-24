package com.example.jwt.demojwt.utils;

import java.security.Key;
import java.sql.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;

public class ManagerToken {

    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    
    public static byte[] GetSignigKeyToDecode(String secret) {
        return DatatypeConverter.parseBase64Binary(secret);
    }

    public static String GenerateToken(String secret)
    {
        final Key signingKey = GetSignigKeyToEncode(secret);
        final long nowMillis = System.currentTimeMillis();
        final Date now = new Date(nowMillis);
        final long expMillis = nowMillis + 5000000;
        final Date exp = new Date(expMillis);

        final JwtBuilder builder = Jwts.builder()
            .setId("id_test")
            .setIssuedAt(now)
            .setSubject("token de prueba")
            .setIssuer("isanchez")
            .claim("identificadorAplicacion","12345678")
            .signWith(signatureAlgorithm, signingKey)
            .setExpiration(exp);
        return builder.compact();          
    }

    public static Claims DeserializeToken(String secret, String jwtToken){
        return Jwts.parser().setSigningKey(GetSignigKeyToDecode(secret)).parseClaimsJws(jwtToken).getBody();
    }
    
    private static Key GetSignigKeyToEncode(String secret) {
        final byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
        return new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    }
}