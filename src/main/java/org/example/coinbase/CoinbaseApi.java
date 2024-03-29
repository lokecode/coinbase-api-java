package org.example.coinbase;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import okhttp3.Response;
import org.example.coinbase.dto.*;
import org.example.domain.enums.ApiMethods;
import org.example.coinbase.dto.GetCryptocurrenciesDto;

import static org.example.ok_http_client.OkHttpClient.okHttpClient;

public class CoinbaseApi {
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

    public static ListAddressesDto getListAddresses(String symbol) {
        try {
            Response response = okHttpClient.newCall(
                    CoinbaseSignatureHelper.request("/v2/accounts/"+symbol+"/addresses", ApiMethods.GET, "")
            ).execute();
            String responseString = response.body().string();
            System.out.println(responseString);
            if (response.code() == 200) {
                return new Gson().fromJson(responseString, ListAddressesDto.class);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static CreateAddressDto createAddress(String symbol) {
        try {
            Response response = okHttpClient.newCall(
                    CoinbaseSignatureHelper.request("/v2/accounts/"+symbol+"/addresses", ApiMethods.POST, "")
            ).execute();
            String responseString = response.body().string();
            System.out.println(responseString);
            if (response.code() == 200 || response.code() == 201) {
                return new Gson().fromJson(responseString, CreateAddressDto.class);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static GetTransactionsSummaryDto getTransactionsSummary() {
        try {
            Response response = okHttpClient.newCall(
                    CoinbaseSignatureHelper.request("/v3/brokerage/transaction_summary/", ApiMethods.GET, "")
            ).execute();
            String responseString = response.body().string();
            System.out.println(responseString);
            if (response.code() == 200) {
                return new Gson().fromJson(responseString, GetTransactionsSummaryDto.class);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static ListProductsDto listProducts() {
        try {
            Response response = okHttpClient.newCall(
                    CoinbaseSignatureHelper.request("/api/v3/brokerage/products/", ApiMethods.GET, "")
            ).execute();
            String responseString = response.body().string();
            System.out.println(responseString);
            if (response.code() == 200) {
                return new Gson().fromJson(responseString, ListProductsDto.class);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static GetCryptocurrenciesDto getCryptocurrencies() {
        try {
            Response response = okHttpClient.newCall(
                    CoinbaseSignatureHelper.request("/v2/currencies/crypto", ApiMethods.GET, "")
            ).execute();
            String responseString = response.body().string();
            System.out.println(responseString);
            if (response.code() == 200) {
                return new Gson().fromJson(responseString, GetCryptocurrenciesDto.class);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static GetProductDto getProduct(String coin) {
        try {
            Response response = okHttpClient.newCall(
                    CoinbaseSignatureHelper.request("/api/v3/brokerage/products/"+coin, ApiMethods.GET, "")
            ).execute();
            String responseString = response.body().string();
            System.out.println(responseString);
            if (response.code() == 200) {
                return new Gson().fromJson(responseString, GetProductDto.class);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static CreateOrderResponseDto createOrder(
            CreateOrderBuyRequestDto BuyRequest,
            CreateOrderSellRequestDto SellRequest
    ) {
        try {
            String body = (BuyRequest != null) ? JSON.toJSONString(BuyRequest) : JSON.toJSONString(SellRequest);
            System.out.println(body);
            Response response = okHttpClient.newCall(
                    CoinbaseSignatureHelper.request("/api/v3/brokerage/orders/", ApiMethods.POST, body)
            ).execute();
            String responseString = response.body().string();
            System.out.println(responseString);
            if (response.code() == 200) {
                return new Gson().fromJson(responseString, CreateOrderResponseDto.class);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String test() {
        try {
            Response response = okHttpClient.newCall(
                    CoinbaseSignatureHelper.request("/v2/accounts/SOL/buys", ApiMethods.POST, "{\"amount\": \"1\", \"currency\": \"SOL\"}")
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

    public static GetFeeEstimateResponseDto getFeeEstimate(GetFeeEstimateRequestDto requestDto) {
        try {
            Response response = okHttpClient.newCall(
                    CoinbaseSignatureHelper.request2(
                            "/withdrawals/fee-estimate?currency="+requestDto.getCurrency()+"&crypto_address="+requestDto.getCrypto_address(),
                            ApiMethods.GET, "")
            ).execute();
            String responseString = response.body().string();
            System.out.println(responseString);
            if (response.code() == 200) {
                return new Gson().fromJson(responseString, GetFeeEstimateResponseDto.class);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


}
