package org.example.coinbase.converter;

import org.example.coinbase.dto.GetFeeEstimateResponseDto;
import org.example.coinbase.model.WithdrawFeeModel;

import java.math.BigDecimal;

public class WithdrawFeeConverter {
    public static WithdrawFeeModel withdrawFeeConverter(GetFeeEstimateResponseDto feeEstimateResponseDto) {
        return new WithdrawFeeModel(new BigDecimal(feeEstimateResponseDto.getFee()));
    }
}
