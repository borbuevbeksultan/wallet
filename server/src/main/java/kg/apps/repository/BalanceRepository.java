package kg.apps.repository;

import kg.apps.model.Balance;
import kg.apps.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BalanceRepository extends JpaRepository<Balance, Integer> {
    Balance getBalanceByCurrencyAndWallet_UserId(Currency currency, Integer userId);

    List<Balance> getAllByWallet_UserId(Integer userId);
}
