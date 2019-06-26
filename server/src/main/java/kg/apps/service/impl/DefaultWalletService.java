package kg.apps.service.impl;

import kg.apps.dto.BalancesDto;
import kg.apps.model.Balance;
import kg.apps.model.Currency;
import kg.apps.model.User;
import kg.apps.model.Wallet;
import kg.apps.repository.BalanceRepository;
import kg.apps.service.UserService;
import kg.apps.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultWalletService implements WalletService {

    private final UserService userService;
    private final BalanceRepository balanceRepository;

    @Override
    public void deposit(Long userId, BigDecimal amount, Currency currency) {
        User user = userService.get(userId);
        Wallet wallet = user.getWallet();
        List<Balance> balances = wallet.getBalances();

        //TODO: change to optimal request
        Optional<Balance> optionalBalance = balances
                .stream()
                .filter(balance -> currency.name().equalsIgnoreCase(balance.getCurrency().name()))
                .findAny();

        optionalBalance.ifPresent(balance -> {
            balance.setAmount(balance.getAmount().add(amount));
            balanceRepository.save(balance);
        });
    }

    @Override
    public void withdraw(Long userId, BigDecimal amount, Currency currency) throws IllegalStateException {
        User user = userService.get(userId);
        Wallet wallet = user.getWallet();
        List<Balance> balances = wallet.getBalances();

        //TODO: change to optimal request
        Optional<Balance> optionalBalance = balances
                .stream()
                .filter(balance -> currency.name().equalsIgnoreCase(balance.getCurrency().name()))
                .findAny();

        optionalBalance.ifPresent(balance -> {
            if (balance.getAmount().subtract(amount).compareTo(BigDecimal.ONE) < 0) {
                throw new IllegalStateException();
            }
            balance.setAmount(balance.getAmount().subtract(amount));
            balanceRepository.save(balance);
        });
    }

    @Override
    public BalancesDto balance(Long userId) throws EntityNotFoundException {
        BalancesDto result = new BalancesDto();
        User user = userService.get(userId);

        for (var currency : Currency.values()) {

            Optional<Balance> optionalBalance = user.getWallet().getBalances()
                    .stream()
                    .filter(balance -> currency.name().equalsIgnoreCase(balance.getCurrency().name()))
                    .findAny();

            result.getBalanceDtos().add(
                    new BalancesDto.BalanceDto(currency,
                            optionalBalance.isPresent()
                            ? optionalBalance.get().getAmount()
                            : BigDecimal.ZERO)
            );
        }

        return result;
    }

}
