package kg.apps.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter

@Entity
@NoArgsConstructor
@Table(name = "balance")
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "wallet")
    private Wallet wallet;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "currency")
    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    public Balance(Wallet wallet, BigDecimal amount, Currency currency) {
        this.wallet = wallet;
        this.amount = amount;
        this.currency = currency;
    }

}
