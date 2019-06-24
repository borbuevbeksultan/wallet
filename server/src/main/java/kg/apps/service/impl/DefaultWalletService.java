package kg.apps.service.impl;

import kg.apps.model.Balance;
import kg.apps.model.Currency;
import kg.apps.model.User;
import kg.apps.repository.BalanceRepository;
import kg.apps.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultWalletService implements WalletService {

    private final BalanceRepository balanceRepository;

    @Override
    public void deposit(User user, BigDecimal amount, Currency currency) {
        Optional<Balance> balance = balanceRepository.findByUserAndCurrency(user.getId(), currency);
        balance.ifPresentOrElse(wallet1 -> {
            wallet1.setAmount(amount);

        }, () -> {

        });
    }

    @Override
    public Object withdraw(User user, BigDecimal amount, Currency currency) {
        return null;
    }

}
