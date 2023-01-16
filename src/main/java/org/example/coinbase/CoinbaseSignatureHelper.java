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
}
