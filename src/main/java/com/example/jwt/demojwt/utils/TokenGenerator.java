package com.example.jwt.demojwt.utils;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Date;
import java.util.Base64;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenGenerator {

    PublicKey PUBLIC_KEY;
    PrivateKey PRIVATE_KEY;

    /**
     * @author Ivan Sanchez this method set the publickey and privatekey
     * @throws Exception     
     */
    public void setKeys() throws Exception {
            final KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
            keyGenerator.initialize(1024);
            final KeyPair kp = keyGenerator.genKeyPair();
            PUBLIC_KEY = (PublicKey) kp.getPublic();
            PRIVATE_KEY = (PrivateKey) kp.getPrivate();            
    }

    public String getPublicKey() {
        return Base64.getEncoder().encodeToString(PUBLIC_KEY.getEncoded());
    }

    public String getPrivateKey() {
        return Base64.getEncoder().encodeToString(PRIVATE_KEY.getEncoded());
    }

    private static PrivateKey GetPrivateKeyDecode(String privateKey)
            throws Exception, NoSuchAlgorithmException, InvalidKeySpecException {
        
        final byte[] buffer = Base64.getDecoder().decode(privateKey);
        final PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
        final KeyFactory kf = KeyFactory.getInstance("RSA");
        final PrivateKey privKey = kf.generatePrivate(keySpec);
        return privKey;
    }

    private static PublicKey GetPublicKeyDecode(String publicKey)
            throws Exception, NoSuchAlgorithmException, InvalidKeySpecException {

        final byte[] buffer = Base64.getDecoder().decode(publicKey);
        final X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(buffer);
        final KeyFactory kf = KeyFactory.getInstance("RSA");
        final PublicKey pubKey = kf.generatePublic(keySpecX509);
        return pubKey;
    }

    public static String generateJwtToken(final String privateKey)
            throws NoSuchAlgorithmException, InvalidKeySpecException, Exception {

        final long nowMillis = System.currentTimeMillis();
        final long expMillis = nowMillis + 5000000;
        final Date exp = new Date(expMillis);
        final PrivateKey privateKeyDecode = GetPrivateKeyDecode(privateKey);

        final String token = Jwts.builder().setSubject("admin").setExpiration(exp).setIssuer("info@terpel.com")
                .claim("groups", new String[] { "user", "admin" })
                // RS256 with privateKey
                .signWith(SignatureAlgorithm.RS256, privateKeyDecode).compact();
        return token;
    }

    public static Claims DecodeToken(final String jwtToken, final String publicKey) throws Exception {
        PublicKey pub = GetPublicKeyDecode(publicKey);
        return Jwts.parser().setSigningKey(pub).parseClaimsJws(jwtToken).getBody();
    }
}