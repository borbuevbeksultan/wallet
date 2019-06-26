package kg.apps.mapper;

import kg.apps.Wallet;
import kg.apps.model.Currency;

public interface CurrencyMapper {
    Currency convertFromProtoMessage(Wallet.Currency currency);
    Wallet.Currency convertToProtoMessage(Currency currency);
}
