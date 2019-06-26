package kg.apps.mapper.impl;

import kg.apps.Wallet;
import kg.apps.mapper.CurrencyMapper;
import kg.apps.model.Currency;
import org.springframework.stereotype.Component;

@Component
public class DefaultCurrencyMapper implements CurrencyMapper {

    @Override
    public Currency convertFromProtoMessage(Wallet.Currency currency) throws IllegalArgumentException {
        return Currency.valueOf(currency.name());
    }

    @Override
    public Wallet.Currency convertToProtoMessage(Currency currency) {
        return Wallet.Currency.valueOf(currency.name());
    }

}
