package kg.apps.service.impl;

import kg.apps.model.Balance;
import kg.apps.model.Currency;
import kg.apps.model.User;
import kg.apps.model.Wallet;
import kg.apps.repository.UserRepository;
import kg.apps.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    @Override
    public User get(Integer id) {
        return userRepository.getOne(id);
    }

    public User createWholeUser(String email) {
        User user = new User();
        user.setEmail(email);
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        List<Balance> balances = List.of(
                new Balance(wallet, BigDecimal.ZERO, Currency.GBP),
                new Balance(wallet, BigDecimal.ZERO, Currency.USD),
                new Balance(wallet, BigDecimal.ZERO, Currency.EUR)
        );
        wallet.setBalances(balances);
        user.setWallet(wallet);
        wallet.setUser(user);

        return userRepository.save(user);
    }

}
