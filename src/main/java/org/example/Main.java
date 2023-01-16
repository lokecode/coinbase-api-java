package org.example;

import lombok.SneakyThrows;
import org.example.coinbase.dto.ListAccountsDto;
import org.example.coinbase.model.BalanceModel;
import org.example.coinbase.repository.CoinbaseRepository;



public class Main {
    @SneakyThrows
    public static void main(String[] args) {


       for (BalanceModel ss : CoinbaseRepository.accounts().values().stream().toList()) {
           System.out.println(ss);
       }

       System.out.println(CoinbaseRepository.addresses());



    }
}

