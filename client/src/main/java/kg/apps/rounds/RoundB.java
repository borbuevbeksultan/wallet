package kg.apps.rounds;

import kg.apps.Wallet;
import kg.apps.client.WalletClient;

public class RoundB implements Round {

    private WalletClient walletClient;

    public RoundB(WalletClient walletClient) {
        this.walletClient = walletClient;
    }

    public void execute(int userId) {
        walletClient.withdraw(userId, 100., Wallet.Currency.GBP);
        walletClient.deposit(userId, 300., Wallet.Currency.GBP);
        walletClient.withdraw(userId, 100., Wallet.Currency.GBP);
        walletClient.withdraw(userId, 100., Wallet.Currency.GBP);
        walletClient.withdraw(userId, 100., Wallet.Currency.GBP);
    }

}

