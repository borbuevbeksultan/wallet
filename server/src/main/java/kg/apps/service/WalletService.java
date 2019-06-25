package kg.apps.service;

import kg.apps.model.Currency;

import java.math.BigDecimal;

public interface WalletService {
    void deposit(Long userId, BigDecimal amount, Currency currency);
    void withdraw(Long userId, BigDecimal amount, Currency currency);
}
