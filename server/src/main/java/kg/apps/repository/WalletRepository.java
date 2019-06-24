package kg.apps.repository;

import kg.apps.model.Balance;
import kg.apps.model.Currency;
import kg.apps.model.Wallet;

import java.util.Optional;

public interface WalletRepository {
    void save(Wallet wallet);

    Optional<Balance> findByUserAndCurrency(Integer userId, Currency currency);
}
