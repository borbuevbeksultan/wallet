package kg.apps.rounds;

import kg.apps.Wallet;
import kg.apps.client.WalletClient;

public class RoundC implements Round {

    private WalletClient walletClient;

    public RoundC(WalletClient walletClient) {
        this.walletClient = walletClient;
    }

    public void execute() {
        System.out.println("Thread: " + Thread.currentThread().getId());
        walletClient.balance(1);
        walletClient.deposit(100., Wallet.Currency.USD);
        walletClient.deposit(100., Wallet.Currency.USD);
        walletClient.withdraw(200., Wallet.Currency.USD);
        walletClient.deposit(100., Wallet.Currency.USD);
        walletClient.balance(1);
        walletClient.withdraw(200., Wallet.Currency.USD);
        walletClient.balance(1);
    }
}
