package kg.apps.endpoint;

import kg.apps.Wallet;

public interface WalletEndpoint {
    void deposit(Wallet.Deposit request);
    void withdraw(Wallet.Withdraw request);
}
