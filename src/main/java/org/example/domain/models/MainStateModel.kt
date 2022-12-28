package org.example.domain.models

import org.example.domain.enums.ExchangeEnums
import org.example.domain.enums.ExchangeStates
import java.math.BigDecimal

data class MainStateModel(
        val currentCoin: String,
        val statExchange: ExchangeEnums,
        val endExchange: ExchangeEnums,
        val statExchangeTradeId: String,
        val endExchangeTradeId: String,
        val statExchangeWithdrawId: String,
        val statExchangeTradeState: ExchangeStates,
        val endExchangeTradeState: ExchangeStates,
        val statExchangeWithdrawState: ExchangeStates,
        val exchangeData: Map<ExchangeEnums, ExchangeData>,
) {
    data class ExchangeData(
            val takerFee: Double,
            val coinsData: Map<String, CoinData>
    ) {
        data class CoinData(
                val symbol: String,
                val price: BigDecimal,
                val volume: Double,
                val balance: BigDecimal,
                val address: String,
                val minQty: BigDecimal,
                val blockchainsAndFees: Map<String, Blockchain>
        ) {
            data class Blockchain(
                    val blockchain: String,
                    val fee: BigDecimal,
                    val withdrawTime: Double
            )
        }
    }
}
