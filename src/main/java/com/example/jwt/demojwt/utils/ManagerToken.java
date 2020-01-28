package com.example.jwt.demojwt.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.RSAPrivateKeySpec;
import java.util.Date;
import java.util.Map;
import java.util.Base64;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
//import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
//import com.google.api.client.json.Json;
//import com.google.api.client.util.Base64;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class ManagerToken {

    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private static final String CRED_ENDPOINT_CONFIG_MAP_DEV_YAML = "src/main/java/com/example/jwt/demojwt/ServiceAccountCredentials/cred-endpoint.json";

    public static Map<String, Claim> DecodeToken(final String jwtToken) {
         return JWT.decode(jwtToken).getClaims();
    }

    public static Boolean TokenExpired(final String token)
    {
        final Date expirationToken = JWT.decode(token).getExpiresAt();
        final long nowMillis = System.currentTimeMillis();
        final Date now = new Date(nowMillis);
        return (expirationToken.getTime() - now.getTime()) <= 0;
    }

    public static String GetPrivateKeySA() throws Exception {
        final JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(CRED_ENDPOINT_CONFIG_MAP_DEV_YAML)) {
            // Read JSON file
            final Object obj = jsonParser.parse(reader);
            final JSONObject info = (JSONObject) obj;
            return (String) info.get("private_key");

        } catch (final Exception e) {
            throw e;
        }
    }


    // public static byte[] GetSignigKeyToDecode(final String secret) {
    //     return DatatypeConverter.parseBase64Binary(secret);
    // }

    public static String GenerateToken(final String secret) {
        final Key signingKey = GetSignigKeyToEncode(secret);
        final long nowMillis = System.currentTimeMillis();
        final Date now = new Date(nowMillis);
        final long expMillis = nowMillis + 500;
        final Date exp = new Date(expMillis);

        final JwtBuilder builder = Jwts.builder()
                .setId("id_test")
                .setIssuedAt(now)
                .setSubject("token de prueba")
                .setIssuer("isanchez")
                .claim("identificadorAplicacion", "12345678")
                .signWith(signatureAlgorithm, signingKey)
                .setExpiration(exp);
        return builder.compact();
    }

    // public static Claims DeserializeToken(final String secret, final String jwtToken) throws IOException {
    //     return Jwts.parser().setSigningKey(GetSignigKeyToDecode(secret)).parseClaimsJws(jwtToken).getBody();
    // }

    // public static void DeserializeToken(final String jwtToken) throws Exception {

    //     try {
    //         final Algorithm signingAlgorithm = GetSignigAlgorithmHMAC256();
    //         final JWTVerifier verifier = JWT.require(signingAlgorithm)
    //                 .withIssuer("sc-apisrumbo-endpoint-dev@terpel-gtic-openshift.iam.gserviceaccount.com").build();
    //         final DecodedJWT jwtDecode = verifier.verify(jwtToken);
    //         final Map<String, Claim> data = jwtDecode.getClaims();

    //     } catch (final JWTVerificationException exception) {
    //         // Invalid signature/claims
    //         throw exception;
    //     }
    // }

    private static Key GetSignigKeyToEncode(final String secret) {
        final byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
        return new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    }
}