package com.example.jwt.demojwt.utils;

import java.io.FileReader;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
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

    //private static final String PUBLIC_KEY = "9f70b47f8dbcd776421425f51f1918d06141b10f5e6ded0c38d827a7ec24b6ab5f913b9f99607fe17069915feda4dd7be13799c68ab27429d66abb7b8da62459ae94f3f00bb94c045e59deab1208708ac093efa7d3ca08789ca7eed76d7b94ba93a1bed9286facbfa20f1c9077783e7514930e608a1210307e9cd0912c2cf8d23f8b0fc9ac72a2eacc9481e6e91ebee96c500835d48036a7ecb8b8b58f76e39e48c5daa8bb48d8d40d53721b37abc7a4aa1733c72c2bb27b3760c1a4d6180ff08a7cf37d2a9c8db24b32547e2fe63cccdbbfd4ae2933a8042f14c6b9e1dc09087ec981a5f9ccad0712cb5febe6b0c0474d5e2e3440dd9b18103a767b51b8b82b";

    public static String GenerateToken() throws Exception {
        final long nowMillis = System.currentTimeMillis();
        final long expMillis = nowMillis + 5000000;
        final Date exp = new Date(expMillis);
        final Algorithm algorithm = GetSignigKeyRSA256();
        final String token = JWT.create().withExpiresAt(exp).sign(algorithm);
        return token;
    }

    private static Algorithm GetSignigKeyRSA256() throws Exception {
        final RSAPrivateKey privateKey = GetPrivateKey();
        return Algorithm.RSA256(null, privateKey);
    }

    private static RSAPrivateKey GetPrivateKey() throws Exception {
        try {
            final String privateKey = GetPrivateKeySA();
            final byte[] buffer = DatatypeConverter.parseBase64Binary(privateKey);
            final PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (final NoSuchAlgorithmException e) {
            throw new Exception("");
        } catch (final InvalidKeySpecException e) {
            throw new Exception("?");
        } catch (final NullPointerException e) {
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

    public static Claims DecodeTokenMethodOne(final String jwtToken)
            throws NoSuchAlgorithmException, InvalidKeySpecException, Exception {
                
        final byte[] modulus = Base64.getUrlDecoder().decode("wQUjJo0j4hxs7IQG6MNg2aeYbCKF_CIGT79DFRMXMFZKEqN0iTiBd0FAN8kQLy4J09YsHkz4hPmxUU-1mPreDr_2EjCRzvNhyKUAcaWI2A1-o8OYJNNuCjNZmpuAV5bcGUPsmpanIeMmhBjcpVbUqFLfhWrkFmtyxiJYKTmdSYCuhQBPb7DJYCFJymj4YRtNGFMwlWR7rlc8U-GIxZoSin7DRrA6GrXrpLg0rRgw2wC_VnNLdJCjGFC-ImzDNK_ZueByfH8sZ1-tJ56P9nrgFw9bmY1CArYpZw0CfjIRew0kPEr0WcBg9chdqn50a6n4zTWN2APvwUPP1iIcLmij_Q");
        final byte[] exponent = Base64.getUrlDecoder().decode("AQAB");

        
        final BigInteger modulusEncode = new BigInteger(toHexFromBytes(modulus), 16); // hex base
        final BigInteger exponentEncode = new BigInteger(toHexFromBytes(exponent), 16); // hex base  

        //"7eb4bd2e68038e536ca1b2dde0d8d8184fdcabd9358742be5751e1f902b10e083f5c2b77aa16b0df3e92df83073be139916c48e4f11b521909f78f3fa0fcbd02fa10103d7adac6c75ca59d161d5b096a5767ab295f0984ce5505caacbe3877a44020668a9ed0cb77f0fcbc0bda020a4b39f215974d77b81098ffcd31ca3ed699a88876b3a9a034d1b0f2b8dd03cc0b56d2ab2160e90031a40d3001dae3ceaaa43c471346b5760bfa271bdfeb86b412f153ea15c29e9b42d49181a41834a5f17163ff55c92e5699a20c1a7870a286e61d642d2786f78014e39fb426772503202107576041d20764d94b77d3b376aee577e4452b8e142077febb7c45aa1d89a81"

        final RSAPublicKeySpec spec = new RSAPublicKeySpec(modulusEncode, exponentEncode);
        final KeyFactory factory = KeyFactory.getInstance("RSA");
        final PublicKey pub = factory.generatePublic(spec);
        return Jwts.parser().setSigningKey(pub).parseClaimsJws(jwtToken).getBody();
        


        ///OJO NO BORRAR ESTE PARTE
        // String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArAqE+MhF51wJkPfOgdy/wkw+l7Hk2jFY5muYSqxSd79d0Tvbnh2Qlgtk+B0sv21+OZnttrtmx/nvqJ/vxrUha6UzKASFRIVOkuRvLT3HkRkN28KmwEezVp5KWJ6EqD4nUS9d3QZTDMPCNpvsCvVfq5bzVoZ62hlj6Dz+pgacKbEsTYnz2G49AgjYLumqEggQZor4+NxmncTMGQbQ3QrJtAMcgPaP2J5BoBeNzgSZdGiXqL+XzPlUpzHBOHnovbJs+rr7ix/yd6v6FtQbAxePlaXFsCkOoMK4pVhvVy4zPlRKHlJvEHMNLISSEWEJRwuzMWzAPg8UuioJ37By+n9jpwIDAQAB";
        // byte[] decode = Base64.getDecoder().decode(publicKey);
        // X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(decode);
        // KeyFactory factory = KeyFactory.getInstance("RSA");
        // PublicKey pubKey = factory.generatePublic(keySpecX509);
        
      
        //return Jwts.parser().setSigningKey(pubKey).parseClaimsJws(jwtToken).getBody();
        
    }

    private static final String[] HEX_TABLE = new String[]{
        "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0a", "0b", "0c", "0d", "0e", "0f",
        "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "1a", "1b", "1c", "1d", "1e", "1f",
        "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "2a", "2b", "2c", "2d", "2e", "2f",
        "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "3a", "3b", "3c", "3d", "3e", "3f",
        "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "4a", "4b", "4c", "4d", "4e", "4f",
        "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "5a", "5b", "5c", "5d", "5e", "5f",
        "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "6a", "6b", "6c", "6d", "6e", "6f",
        "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "7a", "7b", "7c", "7d", "7e", "7f",
        "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "8a", "8b", "8c", "8d", "8e", "8f",
        "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "9a", "9b", "9c", "9d", "9e", "9f",
        "a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9", "aa", "ab", "ac", "ad", "ae", "af",
        "b0", "b1", "b2", "b3", "b4", "b5", "b6", "b7", "b8", "b9", "ba", "bb", "bc", "bd", "be", "bf",
        "c0", "c1", "c2", "c3", "c4", "c5", "c6", "c7", "c8", "c9", "ca", "cb", "cc", "cd", "ce", "cf",
        "d0", "d1", "d2", "d3", "d4", "d5", "d6", "d7", "d8", "d9", "da", "db", "dc", "dd", "de", "df",
        "e0", "e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "ea", "eb", "ec", "ed", "ee", "ef",
        "f0", "f1", "f2", "f3", "f4", "f5", "f6", "f7", "f8", "f9", "fa", "fb", "fc", "fd", "fe", "ff",
    };

    public static String toHexFromBytes(final byte[] bytes) {
        final StringBuffer rc = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            rc.append(HEX_TABLE[0xFF & bytes[i]]);
        }
        return rc.toString();
    }

    public static Claims DecodeTokenMethodTwo(final String jwtToken) throws Exception {
        return Jwts.parser().setSigningKey(GetPrivateKeyMethodTwo()).parseClaimsJws(jwtToken).getBody();
    }

    private static PrivateKey GetPrivateKeyMethodTwo()
            throws Exception, NoSuchAlgorithmException, InvalidKeySpecException {
        final String secret = GetPrivateKeySA();
        final byte[] buffer = DatatypeConverter.parseBase64Binary(secret);
        final PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
        final KeyFactory kf = KeyFactory.getInstance("RSA");
        final PrivateKey privKey = kf.generatePrivate(keySpec);
        return privKey;
    }

    public static String GetPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException, Exception {
        //final PrivateKey privateKey = GetPrivateKeyMethodTwo();
        final RSAPrivateKey rsaPrivateKey = GetPrivateKey();
        final RSAPublicKeySpec rsaPublic = new RSAPublicKeySpec(rsaPrivateKey.getModulus(), rsaPrivateKey.getPrivateExponent());
        
        final PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(rsaPublic);
        
        return publicKey.getEncoded().toString();
    }
}