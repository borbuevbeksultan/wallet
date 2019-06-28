package kg.apps.rounds;

import kg.apps.Wallet;
import kg.apps.client.WalletClient;

public class RoundB implements Round {

    private WalletClient walletClient;

    public RoundB(WalletClient walletClient) {
        this.walletClient = walletClient;
    }

    public void execute() {
        System.out.println("Thread: " + Thread.currentThread().getId());
        walletClient.withdraw(100., Wallet.Currency.GBP);
        walletClient.deposit(300., Wallet.Currency.GBP);
        walletClient.withdraw(100., Wallet.Currency.GBP);
        walletClient.withdraw(100., Wallet.Currency.GBP);
        walletClient.withdraw(100., Wallet.Currency.GBP);
    }

}

