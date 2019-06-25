package kg.apps.endpoint.impl;

import kg.apps.Wallet;
import kg.apps.endpoint.WalletEndpoint;
import kg.apps.model.Currency;
import kg.apps.service.CurrencyService;
import kg.apps.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DefaultWalletEndpoint implements WalletEndpoint {

    private final WalletService walletService;
    private final CurrencyService currencyService;

    @Override
    public void deposit(Wallet.Deposit request) {
        Currency currency = currencyService.getByName(request.getCurrency().name());

        walletService.deposit(
                (long) request.getUserId(),
                BigDecimal.valueOf(request.getAmount()),
                currency
        );
    }

    @Override
    public void withdraw(Wallet.Withdraw request) {
        Currency currency = currencyService.getByName(request.getCurrency().name());

        walletService.withdraw(
                (long) request.getUserId(),
                BigDecimal.valueOf(request.getAmount()),
                currency
        );
    }

    @Override
    public void balance(Wallet.User request) {
        int userId = request.getUserId();
    }

}
