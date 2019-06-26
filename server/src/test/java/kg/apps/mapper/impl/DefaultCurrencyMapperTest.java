package kg.apps.mapper.impl;

import kg.apps.Wallet;
import kg.apps.mapper.CurrencyMapper;
import kg.apps.model.Currency;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DefaultCurrencyMapperTest {

    private CurrencyMapper currencyMapper;

    @BeforeEach
    void setUp() {
        currencyMapper = new DefaultCurrencyMapper();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void convertFromProtoMessage() {

        //act
        Currency currency = currencyMapper.convertFromProtoMessage(Wallet.Currency.UNRECOGNIZED);

        //assert

    }

    @Test
    void convertToProtoMessage() {
    }
}