package xyz.lannt.bittrex.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.lannt.bittrex.application.client.BittrexMarketClient;
import xyz.lannt.bittrex.application.exception.BittrexClientException;
import xyz.lannt.bittrex.domain.model.BittrexBalances;
import xyz.lannt.bittrex.presentation.dto.BalanceDto;

@Service
public class AccountService {

  @Autowired
  private BittrexMarketClient bittrexMarketClient;

  public List<BalanceDto> getBalances() {
    return BittrexBalances.fromResponse(bittrexMarketClient.getBalances())
        .removeEmpty()
        .toDtoes();
  }

  public BalanceDto getBalance(String currency) {
    return BittrexBalances.fromResponse(bittrexMarketClient.getBalances())
        .find(currency)
        .orElseThrow(() -> new BittrexClientException("currency not found!!"))
        .toDto();
  }
}