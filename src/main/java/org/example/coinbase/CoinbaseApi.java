package org.example.coinbase;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.example.domain.enums.ApiMethods;
import org.example.exchanges.coinbase.dto.*;

import java.util.List;

import static org.example.ok_http_client.OkHttpClient.okHttpClient;

public class CoinbaseApi {
    private static RequestBody BuildRequestBody(String body) {
        MediaType mediaType = MediaType.parse("application/json");
        return  RequestBody.create(mediaType, body);
    }
    public static List<GetAllAccountsDto> GetAllAccounts() {
        try {
            Response response = okHttpClient.newCall(CoinbaseSignatureHelper.request("/accounts", ApiMethods.GET, BuildRequestBody(""))).execute();
            return List.of(new Gson().fromJson(response.body().string(), GetAllAccountsDto[].class));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static GetFeesDto GetFees() {
        try {
            Response response = okHttpClient.newCall(CoinbaseSignatureHelper.request("/fees", ApiMethods.GET, BuildRequestBody(""))).execute();
            return new Gson().fromJson(response.body().string(), GetFeesDto.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static List<GetTradingPairDto> GetAllTradingPairs() {
        try {
            Response response = okHttpClient.newCall(CoinbaseSignatureHelper.request("/products", ApiMethods.GET, BuildRequestBody(""))).execute();
            return List.of(new Gson().fromJson(response.body().string(), GetTradingPairDto[].class));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static GetProductTickerResponseDto GetProductTicker(GetProductTickerRequestDto tickerRequestDto) {
        try {
            Response response = okHttpClient.newCall(CoinbaseSignatureHelper.request("/products/"+tickerRequestDto.getProduct_id()+"/ticker/", ApiMethods.GET, BuildRequestBody(""))).execute();
            return new Gson().fromJson(response.body().string(), GetProductTickerResponseDto.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    // get all trades insert here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    public static CreateNewOrderResponseDto CreateNewOrder(CreateNewOrderRequestDto orderRequestDto) {
        try {
            Response response = okHttpClient.newCall(CoinbaseSignatureHelper.request("/orders", ApiMethods.POST, BuildRequestBody(JSON.toJSONString(orderRequestDto)))).execute();
            return new Gson().fromJson(response.body().string(), CreateNewOrderResponseDto.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static CreateNewOrderResponseDto WithdrawToAddress(WithdrawToAddressRequestDto WithdrawRequestDto) {
        try {
            Response response = okHttpClient.newCall(CoinbaseSignatureHelper.request("/withdrawals/crypto/", ApiMethods.POST, BuildRequestBody(JSON.toJSONString(WithdrawRequestDto)))).execute();
            return new Gson().fromJson(response.body().string(), CreateNewOrderResponseDto.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static GetAllTransfersResponseDto GetAllTransfers(GetAllTransfersRequestDto TransfersRequest) {
        try {
            Response response = okHttpClient.newCall(
                    CoinbaseSignatureHelper.request("/transfers?profile_id="+TransfersRequest.getProfile_id()+
                            "&before="+TransfersRequest.getBefore()+
                            "&after="+TransfersRequest.getAfter()+
                            "&limit="+TransfersRequest.getLimit()+
                            "&type="+TransfersRequest.getType(),
                            ApiMethods.POST, BuildRequestBody(JSON.toJSONString(TransfersRequest)))
            ).execute();
            return new Gson().fromJson(response.body().string(), GetAllTransfersResponseDto.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
