package kg.apps.service.impl;

import kg.apps.dto.BalancesDto;
import kg.apps.model.Balance;
import kg.apps.model.Currency;
import kg.apps.repository.BalanceRepository;
import kg.apps.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultWalletService implements WalletService {

    private final BalanceRepository balanceRepository;

    @Override
    public void deposit(Integer userId, BigDecimal amount, Currency currency) {
        Balance balance = balanceRepository.getBalanceByCurrencyAndWallet_UserId(currency, userId);
        balance.setAmount(balance.getAmount().add(amount));
        balanceRepository.save(balance);
    }

    @Override
    public void withdraw(Integer userId, BigDecimal amount, Currency currency) throws IllegalStateException {
        Balance balance = balanceRepository.getBalanceByCurrencyAndWallet_UserId(currency, userId);
        if (balance.getAmount().subtract(amount).compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException();
        }
        balance.setAmount(balance.getAmount().subtract(amount));
        balanceRepository.save(balance);
    }

    @Override
    public BalancesDto balance(Integer userId) throws EntityNotFoundException {
        BalancesDto result = new BalancesDto();

        List<Balance> balances = balanceRepository.getAllByWallet_UserId(userId);
        balances.stream()
                .map(balance -> new BalancesDto.BalanceDto(balance.getCurrency(), balance.getAmount()))
                .forEach(result.getBalanceDtos()::add);

        return result;
    }

}
