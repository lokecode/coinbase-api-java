package org.example.viewmodel;

import org.example.coinbase.model.*;
import org.example.coinbase.repository.CoinbaseRepository;
import org.example.domain.models.MainStateModel;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CoinbaseViewModel {
    public static MainStateModel.ExchangeData initialExchangeData() {
        ExchangeFeesModel tradeFee = CoinbaseRepository.exchangeFees();
        if (tradeFee != null) {
            return new MainStateModel.ExchangeData(
                    tradeFee.getTakerFee(),
                    initialCoinData()
            );
        }
        return null;
    }
    private static LinkedHashMap<String, MainStateModel.ExchangeData.CoinData> initialCoinData() {
        LinkedHashMap<String, MainStateModel.ExchangeData.CoinData> initialCoinDataList = new LinkedHashMap<>();

        //data needed
        Map<String, BalanceModel> balances = CoinbaseRepository.accounts();
        Map<String, TickerModel> coins = CoinbaseRepository.tickers();
        Map<String, CoinInformationModel> allCoins = CoinbaseRepository.coinInformation();

        for (CoinInformationModel coin : allCoins.values().stream().toList()) {
            //var
            WithdrawFeeModel withdrawFee = null;
            AddressModel address = CoinbaseRepository.address(coin.getSymbol());
            //val
            List<TickerModel> tickers = coins.values().stream().filter(currency -> currency.getSymbol().split("-")[0].equals(coin.getSymbol())).toList();
            BigDecimal balance = balances.get(coin.getSymbol()) != null ? balances.get(coin.getSymbol()).getAmount() : new BigDecimal("0.0");
            if(address == null) {address = CoinbaseRepository.newAddress(coin.getSymbol());}
            if(address != null) {withdrawFee = CoinbaseRepository.withdrawFee(coin.getSymbol(), address.getAddress());}

            for (TickerModel ticker : tickers) {
                if (address != null && withdrawFee != null) {
                    AddressModel finalAddress = address;
                    List<TickerModel> blockchains = coins.values().stream().filter(s-> s.getCurrencyName().equals(finalAddress.getBlockchain())).toList();
                    String blockchain = blockchains.isEmpty() ? "" : blockchains.get(0).getSymbol().split("-")[0];

                    initialCoinDataList.put(
                            ticker.getSymbol().replace("-", "_"),
                            new MainStateModel.ExchangeData.CoinData(
                                    ticker.getSymbol().replace("-", "_"),
                                    ticker.getPrice(),
                                    ticker.getVolume().doubleValue(),
                                    balance,
                                    ticker.getMinQty(),
                                    Map.of(blockchain,
                                            new MainStateModel.ExchangeData.CoinData.Blockchain(
                                                    blockchain,
                                                    address.getAddress(),
                                                    address.getTag(),
                                                    withdrawFee.getWithdrawFee(),
                                                    20
                                            )
                                    )
                            )
                    );
                } else {
                    initialCoinDataList.put(
                            ticker.getSymbol().replace("-", "_"),
                            new MainStateModel.ExchangeData.CoinData(
                                    ticker.getSymbol().replace("-", "_"),
                                    ticker.getPrice(),
                                    ticker.getVolume().doubleValue(),
                                    balance,
                                    ticker.getMinQty(),
                                    Map.of()
                            )
                    );
                }
            }

        }

        return initialCoinDataList;
    }
}
