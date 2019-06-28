package kg.apps.rounds;

import kg.apps.Wallet;
import kg.apps.client.WalletClient;

public class RoundA implements Round {

    private WalletClient walletClient;

    public RoundA(WalletClient walletClient) {
        this.walletClient = walletClient;
    }

    @Override
    public void execute(int userId) {
        walletClient.deposit(userId,100., Wallet.Currency.USD);
        walletClient.withdraw(userId,200., Wallet.Currency.USD);
        walletClient.deposit(userId,100., Wallet.Currency.EUR);
        walletClient.balance(userId);
        walletClient.withdraw(userId,100., Wallet.Currency.USD);
        walletClient.balance(userId);
        walletClient.withdraw(userId,100., Wallet.Currency.USD);
    }
}
