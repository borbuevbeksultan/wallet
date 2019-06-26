package kg.apps.endpoint.impl;

import kg.apps.Wallet;
import kg.apps.dto.BalancesDto;
import kg.apps.endpoint.WalletEndpoint;
import kg.apps.mapper.CurrencyMapper;
import kg.apps.model.Currency;
import kg.apps.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultWalletEndpoint implements WalletEndpoint {

    private final WalletService walletService;
    private final CurrencyMapper currencyMapper;

    @Override
    public Wallet.Response deposit(Wallet.Deposit request) {
        Currency currency;
        try {
            currency = currencyMapper.convertFromProtoMessage(request.getCurrency());
        } catch (IllegalArgumentException e) {
            return Wallet.Response.newBuilder().setMessage("Unknown currency").build();
        }

        walletService.deposit(
                (long) request.getUserId(),
                BigDecimal.valueOf(request.getAmount()),
                currency);

        return Wallet.Response.newBuilder().setMessage("Success").build();
    }

    @Override
    public Wallet.Response withdraw(Wallet.Withdraw request) {
        Currency currency;
        try {
            currency = currencyMapper.convertFromProtoMessage(request.getCurrency());
            walletService.withdraw(
                    (long) request.getUserId(),
                    BigDecimal.valueOf(request.getAmount()),
                    currency);
        } catch (IllegalArgumentException e) {
            return Wallet.Response.newBuilder().setMessage("Unknown currency").build();
        } catch (IllegalStateException e) {
            return Wallet.Response.newBuilder().setMessage("Insufficient funds").build();
        }


        return Wallet.Response.newBuilder().setMessage("Success").build();
    }

    @Override
    public Wallet.Balances balance(Wallet.User request) {
        long userId = (long) request.getUserId();
        BalancesDto balancesDto;
        try {
            balancesDto = walletService.balance(userId);
        } catch (EntityNotFoundException e) {
            return Wallet.Balances.newBuilder().build();
        }
        return convert(balancesDto);
    }

    private Wallet.Balances convert(BalancesDto balanceDto) {
        List<BalancesDto.BalanceDto> balanceDtos = balanceDto.getBalanceDtos();

        List<Wallet.Balance> balances = balanceDtos.stream()
                .map(balanceDto1 ->
                        Wallet.Balance
                                .newBuilder()
                                .setCurrency(currencyMapper.convertToProtoMessage(balanceDto1.getCurrency()))
                                .setAmount(balanceDto1.getAmount().doubleValue())
                                .build())

                .collect(Collectors.toList());

        return Wallet.Balances.newBuilder().addAllBalance(balances).build();
    }

}
