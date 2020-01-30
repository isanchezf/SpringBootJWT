package com.example.jwt.demojwt.utils;

import java.io.FileReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class ManagerToken {

    private static final String CRED_ENDPOINT_CONFIG_MAP_DEV_YAML = "C:/@GithubIS/ServiceAccountCredentials/cred-endpoint.json";

   
    public static String GenerateToken() throws Exception {
        final long nowMillis = System.currentTimeMillis();
        final long expMillis = nowMillis + 5000000;
        final Date exp = new Date(expMillis);
        Algorithm algorithm = GetSignigKeyRSA256();
        String token = JWT.create()
                .withExpiresAt(exp).sign(algorithm);
        return token;
    }

    private static Algorithm GetSignigKeyRSA256() throws Exception {
        RSAPrivateKey privateKey = GetPrivateKey();
        return Algorithm.RSA256(null, privateKey);
    }

    private static RSAPrivateKey GetPrivateKey() throws Exception {
        try {
            String privateKey = GetPrivateKeySA();
            byte[] buffer = DatatypeConverter.parseBase64Binary(privateKey);
            // byte[] buffer = ENCODED ? DatatypeConverter.parseBase64Binary(privateKey) :
            // GetDecodeBase64(privateKey);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("");
        } catch (InvalidKeySpecException e) {
            throw new Exception("?");
        } catch (NullPointerException e) {
            throw new Exception("?");
        }
    }

    private static String GetPrivateKeySA() throws Exception {
        final JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(CRED_ENDPOINT_CONFIG_MAP_DEV_YAML)) {
            // Read JSON file
            final Object obj = jsonParser.parse(reader);
            final JSONObject info = (JSONObject) obj;
            final String privateKey = (String) info.get("private_key");

            return privateKey.replaceAll("-----END PRIVATE KEY-----", "").replaceAll("-----BEGIN PRIVATE KEY-----", "")
                    .replaceAll("\n", "");

        } catch (final Exception e) {
            throw e;
        }
    }
   
    public static Claims DecodeTokenMethodTwo(final String jwtToken) throws Exception {
        return Jwts.parser().setSigningKey(GetPrivateKeyMethodTwo()).parseClaimsJws(jwtToken).getBody();
    }

    private static PrivateKey GetPrivateKeyMethodTwo() throws Exception, NoSuchAlgorithmException, InvalidKeySpecException {
        final String secret = GetPrivateKeySA();
        byte[] buffer = DatatypeConverter.parseBase64Binary(secret);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey privKey = kf.generatePrivate(keySpec);
        return privKey;
    }
}