package kg.apps.endpoint;

import kg.apps.Wallet;

public interface WalletEndpoint {
    Wallet.Response deposit(Wallet.Deposit request);
    Wallet.Response withdraw(Wallet.Withdraw request);
    Wallet.Balances balance(Wallet.User request);
}
