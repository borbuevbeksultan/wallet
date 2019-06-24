package kg.apps.repository;

import kg.apps.model.Balance;
import kg.apps.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Optional<Balance> findByUserAndCurrency(Integer userId, Currency currøency);
}
