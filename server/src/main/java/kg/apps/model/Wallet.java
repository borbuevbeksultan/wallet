package kg.apps.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user")
    private User user;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
    private List<Balance> balances;

}
