package kg.apps.endpoint.impl;

import kg.apps.Wallet;
import kg.apps.endpoint.WalletEndpoint;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@Sql(value = "classpath:scenario_1.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@SpringBootTest
class DefaultWalletEndpointTest {

    private final static String INSUFFICIENT_FUNDS_MESSAGE = "Insufficient funds";
    private final static String OK_MESSAGE = "Ok";

    @Autowired
    private WalletEndpoint walletEndpoint;

    @Test
    void checkWalletEndpoint_scenario_1() {

        Wallet.Response response = withdrawalWith200Usd();
        assertThat(response.getMessage()).isEqualTo(INSUFFICIENT_FUNDS_MESSAGE);

        List<Wallet.Balance> balancesAfterFirstDeposit = depositWith100UsdAndReturnBalance();
        assertUsdBalanceForUserOne(balancesAfterFirstDeposit, 100);

        Wallet.Response responseAfterSecondWithdraw = withdrawalWith200Usd();
        assertThat(responseAfterSecondWithdraw.getMessage()).isEqualTo(INSUFFICIENT_FUNDS_MESSAGE);

        List<Wallet.Balance> balanceAfterSecondDeposit = depositWith100UsdAndReturnBalance();
        assertUsdBalanceForUserOne(balanceAfterSecondDeposit, 200);

        Wallet.Response responseAfterThirdWithdrawal = withdrawalWith200Usd();
        assertThat(responseAfterThirdWithdrawal.getMessage()).isEqualTo(INSUFFICIENT_FUNDS_MESSAGE);

        List<Wallet.Balance> responseAfterThirdDeposit = depositWith100UsdAndReturnBalance();
        assertUsdBalanceForUserOne(responseAfterThirdDeposit, 300);

        Wallet.Response responseAfterFourthWithdrawal = withdrawalWith200Usd();
        assertThat(responseAfterFourthWithdrawal.getMessage()).isEqualTo(OK_MESSAGE);

        List<Wallet.Balance> balanceAfterFourthWithdrawal = balanceForUserOne();
        assertUsdBalanceForUserOne(balanceAfterFourthWithdrawal, 100);

        Wallet.Response responseAfterFifthWithdrawal = withdrawalWith200Usd();
        assertThat(responseAfterFifthWithdrawal.getMessage()).isEqualTo(INSUFFICIENT_FUNDS_MESSAGE);
    }

    private List<Wallet.Balance> depositWith100UsdAndReturnBalance() {
        Wallet.Deposit deposit = Wallet.Deposit.newBuilder()
                .setUserId(1)
                .setAmount(100)
                .setCurrency(Wallet.Currency.USD)
                .build();

        walletEndpoint.deposit(deposit);

        return walletEndpoint.balance(Wallet.User.newBuilder()
                .setUserId(1)
                .build()).getBalanceList();
    }

    private Wallet.Response withdrawalWith200Usd() {
        Wallet.Withdraw withdraw = Wallet.Withdraw.newBuilder()
                .setUserId(1)
                .setAmount(200)
                .setCurrency(Wallet.Currency.USD)
                .build();

        return walletEndpoint.withdraw(withdraw);
    }

    private List<Wallet.Balance> balanceForUserOne() {
        return walletEndpoint.balance(Wallet.User.newBuilder()
                .setUserId(1)
                .build()).getBalanceList();
    }

    private void assertUsdBalanceForUserOne(List<Wallet.Balance> balances, double expectedAmount) {
        Optional<Wallet.Balance> usdBalance = balances.stream()
                .filter(balance -> balance.getCurrency().equals(Wallet.Currency.USD))
                .findFirst();

        assertThat(usdBalance.isPresent()).isTrue();
        assertThat(usdBalance.get().getAmount()).isEqualTo(expectedAmount);
    }

}