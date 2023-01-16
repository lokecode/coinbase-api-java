package org.example.coinbase;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.example.coinbase.dto.ListAccountsDto;
import org.example.domain.enums.ApiMethods;

import static org.example.ok_http_client.OkHttpClient.okHttpClient;

public class CoinbaseApi {
    private static RequestBody BuildRequestBody(String body) {
        MediaType mediaType = MediaType.parse("application/json");
        return  RequestBody.create(mediaType, body);
    }
    public static ListAccountsDto getListAccounts() {
        try {
            Response response = okHttpClient.newCall(
                    CoinbaseSignatureHelper.request("/v2/accounts", ApiMethods.GET, "")
            ).execute();
            String responseString = response.body().string();
            System.out.println(responseString);
            if (response.code() == 200) {
                return new Gson().fromJson(responseString, ListAccountsDto.class);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String getListAddresses() {
        try {
            Response response = okHttpClient.newCall(
                    CoinbaseSignatureHelper.request("/v2/accounts/USDT/addresses", ApiMethods.GET, "")
            ).execute();
            String responseString = response.body().string();
            System.out.println(responseString);
            if (response.code() == 200) {
                return responseString;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
