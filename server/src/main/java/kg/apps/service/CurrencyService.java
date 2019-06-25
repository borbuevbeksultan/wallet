package kg.apps.service;

import kg.apps.model.Currency;

public interface CurrencyService {
    Currency getByName(String name);
}
