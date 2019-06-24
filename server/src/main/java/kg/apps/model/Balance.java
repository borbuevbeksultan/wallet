package kg.apps.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Balance {
    private Wallet wallet;
    private BigDecimal amount;
    private Currency currency;
}
