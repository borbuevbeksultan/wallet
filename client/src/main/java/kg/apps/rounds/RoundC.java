package kg.apps.rounds;

import kg.apps.Wallet;
import kg.apps.client.WalletClient;

public class RoundC implements Round {

    private WalletClient walletClient;

    public RoundC(WalletClient walletClient) {
        this.walletClient = walletClient;
    }

    public void execute(int userId) {
        walletClient.balance(userId);
        walletClient.deposit(userId, 100., Wallet.Currency.USD);
        walletClient.deposit(userId, 100., Wallet.Currency.USD);
        walletClient.withdraw(userId, 200., Wallet.Currency.USD);
        walletClient.deposit(userId, 100., Wallet.Currency.USD);
        walletClient.balance(userId);
        walletClient.withdraw(userId, 200., Wallet.Currency.USD);
        walletClient.balance(userId);
    }

}
