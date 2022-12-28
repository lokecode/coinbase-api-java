package org.example.coinbase;

import okhttp3.Request;
import okhttp3.RequestBody;
import org.example.Configure;
import org.example.domain.enums.ApiMethods;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.management.RuntimeErrorException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;

public class CoinbaseSignatureHelper {
    private static String Sign(String requestPath, String method, String body, String timestamp) {
        String secret = Configure.CB_SECRET_KEY;
        try {
            String prehash = timestamp + method.toUpperCase() + requestPath + body;
            byte[] secretDecoded = Base64.getDecoder().decode(secret);
            SecretKeySpec keyspec = null;
            try {
                keyspec = new SecretKeySpec(secretDecoded,  Mac.getInstance("HmacSHA256").getAlgorithm());
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            Mac sha256 = null;
            try {
                sha256 = (Mac)  Mac.getInstance("HmacSHA256").clone();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            sha256.init(keyspec);
            return Base64.getEncoder().encodeToString(sha256.doFinal(prehash.getBytes()));
        } catch (CloneNotSupportedException | InvalidKeyException e) {
            e.printStackTrace();
            throw new RuntimeErrorException(new Error("Cannot set up authentication headers."));
        }
    }

    public static Request request(String endpoint, ApiMethods method, RequestBody body) {
        String timestamp = Instant.now().getEpochSecond() + "";
        System.out.println("https://api.pro.coinbase.com"+endpoint);
        Request request = null;
        try {
            if (body.contentLength() > 2) {
                request = new Request.Builder()
                        .url("https://api.pro.coinbase.com"+endpoint)
                        .post(body)
                        .addHeader("CB-ACCESS-KEY", Configure.CB_API_KEY)
                        .addHeader("CB-ACCESS-PASSPHRASE", Configure.CB_PHRASE)
                        .addHeader("CB-ACCESS-TIMESTAMP", timestamp)
                        .addHeader("CB-ACCESS-SIGN", Sign(endpoint, method.toString(), "", timestamp))
                        .addHeader("CB-VERSION", "2021-09-04")
                        .build();
            } else {
                request = new Request.Builder()
                        .url("https://api.pro.coinbase.com"+endpoint)
                        .addHeader("CB-ACCESS-KEY", Configure.CB_API_KEY)
                        .addHeader("CB-ACCESS-PASSPHRASE", Configure.CB_PHRASE)
                        .addHeader("CB-ACCESS-TIMESTAMP", timestamp)
                        .addHeader("CB-ACCESS-SIGN", Sign(endpoint, method.toString(), "", timestamp))
                        .addHeader("CB-VERSION", "2021-09-04")
                        .build();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return request;
    }
}
