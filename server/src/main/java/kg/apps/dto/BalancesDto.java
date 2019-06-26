package kg.apps.dto;

import kg.apps.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BalancesDto {

    List<BalanceDto> balanceDtos = new ArrayList<>();

    @Getter
    @AllArgsConstructor
    public static class BalanceDto {
        private Currency currency;
        private BigDecimal amount;
    }
}
