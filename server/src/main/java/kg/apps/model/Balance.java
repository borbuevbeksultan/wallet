package kg.apps.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter

@Entity
@Table(name = "balance")
public class Balance {

    @Id
    private Long id;
    private Wallet wallet;
    private BigDecimal amount;
    private Currency currency;
}
