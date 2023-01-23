package org.example.coinbase;

import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.example.Configure;
import org.example.domain.enums.ApiMethods;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.management.RuntimeErrorException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.time.Instant;
import java.util.Base64;
import java.util.Formatter;

public class CoinbaseSignatureHelper {
    private static final String HMAC_SHA256 = "HmacSHA256";
    private static String toHexString(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }

    public static String calculateHMAC(String data, String key) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), HMAC_SHA256);
            Mac mac = Mac.getInstance(HMAC_SHA256);
            mac.init(secretKeySpec);
            return toHexString(mac.doFinal(data.getBytes()));
        } catch (Exception e) {
            System.out.println(e);
            return "";
        }
    }

    @SneakyThrows
    public static Request request(String endpoint, ApiMethods method, String body) {
        String timestamp = Instant.now().getEpochSecond() + "";
        String message = timestamp + method.toString().toUpperCase() + endpoint + body;

        Request request = new Request.Builder()
                .url("https://api.coinbase.com"+endpoint)
                .addHeader("CB-ACCESS-SIGN", calculateHMAC(message, Configure.CB_SECRET_KEY))
                .addHeader("CB-ACCESS-TIMESTAMP", timestamp)
                .addHeader("CB-ACCESS-KEY", Configure.CB_API_KEY)
                .addHeader("CB-VERSION", "2015-07-22")
                .build();

        if (method.equals(ApiMethods.POST)) {
            request = request.newBuilder().post(RequestBody.create(MediaType.parse("application/json"), body)).build();
        }

        System.out.println("https://api.coinbase.com"+endpoint);

        return request;
    }

















    private static String Sign(String requestPath, String method, String body, String timestamp) {
        String secret = Configure.CB2_SECRET_KEY;
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

    public static Request request2(String endpoint, ApiMethods method, String message) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), message);
        String timestamp = Instant.now().getEpochSecond() + "";
        System.out.println("https://api.pro.coinbase.com"+endpoint);
        Request request = null;
        try {
            if (body.contentLength() > 2) {
                request = new Request.Builder()
                        .url("https://api.pro.coinbase.com"+endpoint)
                        .post(body)
                        .addHeader("CB-ACCESS-KEY", Configure.CB2_API_KEY)
                        .addHeader("CB-ACCESS-PASSPHRASE", Configure.CB2_PHRASE)
                        .addHeader("CB-ACCESS-TIMESTAMP", timestamp)
                        .addHeader("CB-ACCESS-SIGN", Sign(endpoint, method.toString(), "", timestamp))
                        .addHeader("CB-VERSION", "2021-09-04")
                        .build();
            } else {
                request = new Request.Builder()
                        .url("https://api.pro.coinbase.com"+endpoint)
                        .addHeader("CB-ACCESS-KEY", Configure.CB2_API_KEY)
                        .addHeader("CB-ACCESS-PASSPHRASE", Configure.CB2_PHRASE)
                        .addHeader("CB-ACCESS-TIMESTAMP", timestamp)
                        .addHeader("CB-ACCESS-SIGN", Sign(endpoint, method.toString(), "", timestamp))
                        .addHeader("CB-VERSION", "2021-09-04")
                        .build();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return request;
    }
}
