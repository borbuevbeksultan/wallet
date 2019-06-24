package kg.apps.service;

import kg.apps.model.Currency;
import kg.apps.model.User;

import java.math.BigDecimal;

public interface WalletService {
    void deposit(User user, BigDecimal amount, Currency currency);
    Object withdraw(User user, BigDecimal amount, Currency currency);
}
