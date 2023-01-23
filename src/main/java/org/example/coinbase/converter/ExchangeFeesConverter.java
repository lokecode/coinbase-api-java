package org.example.coinbase.converter;

import org.example.coinbase.dto.GetTransactionsSummaryDto;
import org.example.coinbase.model.ExchangeFeesModel;

public class ExchangeFeesConverter {
    public static ExchangeFeesModel exchangeFeesConverter(GetTransactionsSummaryDto response) {
        return new ExchangeFeesModel(
                response.getFee_tier().getTaker_fee_rate(),
                response.getFee_tier().getMaker_fee_rate()
        );
    }
}
