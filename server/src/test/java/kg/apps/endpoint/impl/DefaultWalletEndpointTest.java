package kg.apps.endpoint.impl;

import kg.apps.Wallet;
import kg.apps.endpoint.WalletEndpoint;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")

@SpringBootTest
class DefaultWalletEndpointTest {

    @Autowired
    private WalletEndpoint walletEndpoint;

    @Test
    void checkWalletEndpoint_givenUserWithEmptyBalance_shouldResponseForAllAsserts() {
        Wallet.Response response = withdrawalWith200Usd();//1
        assertThat(response.getMessage()).isEqualTo("Insufficient funds");

        List<Wallet.Balance> balances = depositWith100Usd();//2

        assertUsdBalanceForUser(balances, 100);//3

        Wallet.Response responseAfterSecondWithdraw = withdrawalWith200Usd();//4
        assertThat(responseAfterSecondWithdraw.getMessage()).isEqualTo("Insufficient funds");

        List<Wallet.Balance> balanceAfterSecondDeposit = depositWith100Usd();//5

        assertUsdBalanceForUser(balanceAfterSecondDeposit, 200);//6

        Wallet.Response responseAfterThirdWithdrawal = withdrawalWith200Usd();//7
        assertThat(responseAfterThirdWithdrawal.getMessage()).isEqualTo("Insufficient funds");

        List<Wallet.Balance> responseAfterThirdDeposit = depositWith100Usd();//8

        assertUsdBalanceForUser(responseAfterThirdDeposit, 300);//9

        Wallet.Response responseAfterFourthWithdrawal = withdrawalWith200Usd();//10
        assertThat(responseAfterFourthWithdrawal.getMessage()).isEqualTo("Ok");

        List<Wallet.Balance> balanceAfterFourthWithdrawal = balanceForUserOne();
        assertUsdBalanceForUser(balanceAfterFourthWithdrawal, 100);//11

        Wallet.Response responseAfterFifthWithdrawal = withdrawalWith200Usd();//12
        assertThat(responseAfterFifthWithdrawal.getMessage()).isEqualTo("Insufficient funds");
    }

    private List<Wallet.Balance> depositWith100Usd() {
        //arrange
        Wallet.Deposit deposit = Wallet.Deposit.newBuilder()
                .setUserId(1)
                .setAmount((double) 100)
                .setCurrency(Wallet.Currency.USD)
                .build();
        //act
        walletEndpoint.deposit(deposit);

        //assert
        return walletEndpoint.balance(Wallet.User.newBuilder()
                .setUserId(1)
                .build()).getBalanceList();
    }

    private Wallet.Response withdrawalWith200Usd() {
        //arrange
        Wallet.Withdraw withdraw = Wallet.Withdraw.newBuilder()
                .setUserId(1)
                .setAmount((double) 200)
                .setCurrency(Wallet.Currency.USD)
                .build();

        //act
        return walletEndpoint.withdraw(withdraw);
    }

    private List<Wallet.Balance> balanceForUserOne() {
        return walletEndpoint.balance(Wallet.User.newBuilder()
                .setUserId(1)
                .build()).getBalanceList();
    }

    private void assertUsdBalanceForUser(List<Wallet.Balance> balances, double expectedAmount) {
        Optional<Wallet.Balance> usdBalance = balances.stream()
                .filter(balance -> balance.getCurrency().equals(Wallet.Currency.USD))
                .findFirst();

        assertThat(usdBalance.isPresent()).isTrue();
        assertThat(usdBalance.get().getAmount()).isEqualTo(expectedAmount);
    }

}