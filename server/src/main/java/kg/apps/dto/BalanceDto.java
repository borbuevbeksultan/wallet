package kg.apps.dto;

import kg.apps.model.Currency;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Getter
public class BalanceDto {
    Map<Currency, BigDecimal> amountPerCurrency = new HashMap<>();
}
