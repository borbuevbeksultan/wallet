package kg.apps.service;

import kg.apps.dto.BalancesDto;
import kg.apps.model.Currency;

import java.math.BigDecimal;

public interface WalletService {
    void deposit(Integer userId, BigDecimal amount, Currency currency);

    void withdraw(Integer userId, BigDecimal amount, Currency currency) throws IllegalStateException;

    BalancesDto balance(Integer userId);
}
