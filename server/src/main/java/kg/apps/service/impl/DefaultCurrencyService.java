package kg.apps.service.impl;

import kg.apps.model.Currency;
import kg.apps.service.CurrencyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DefaultCurrencyService implements CurrencyService {

    @Override
    public Currency getByName(String name) {
        return Currency.valueOf(name);
    }

}
