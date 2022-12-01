package org.example;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.codec.binary.Hex;
import org.example.di.coinbase.CbRequest;
import org.example.domain.enums.RequestMethods;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.management.RuntimeErrorException;
import java.io.IOException;
import java.net.http.HttpHeaders;
import java.security.InvalidKeyException;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.example.domain.enums.RequestMethods.GET;
import static org.example.domain.enums.RequestMethods.POST;

@Slf4j
public class Main {

    @SneakyThrows
    public static void main(String[] args) {

        //1669041379
        //1669041784559

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        Request request = chain.request();

                        String path = request.url().toString();

                        long startTs = System.currentTimeMillis();
                        Response response = chain.proceed(request);
                        return response;
                    }
                })

                .build();

        //51070926-3937-4da2-ad41-c06401d663b5

        MediaType mediaType = MediaType.parse("application/json");

        RequestBody body = RequestBody.create(mediaType, ""
        );

        try {
            Response response = okHttpClient.newCall(CbRequest.request("/accounts", GET,body)).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("[EXECUTE] NETWORK IO EXCEPTION " + e.getMessage());
        }
        
    }


}

