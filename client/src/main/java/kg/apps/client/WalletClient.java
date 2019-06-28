package kg.apps.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import kg.apps.Wallet;
import kg.apps.WalletServiceGrpc;

public class WalletClient {

    private ManagedChannel channel;
    private WalletServiceGrpc.WalletServiceBlockingStub blockingStub;

    public WalletClient() {
        channel = ManagedChannelBuilder.forAddress("localhost", 8888)
                .usePlaintext().build();
        blockingStub = WalletServiceGrpc.newBlockingStub(channel);
    }

    public Wallet.Response deposit(Double amount, Wallet.Currency currency) {
        Wallet.Deposit deposit = Wallet.Deposit.newBuilder()
                .setUserId(1)
                .setAmount(amount)
                .setCurrency(currency)
                .build();

        return blockingStub.deposit(deposit);
    }

    public Wallet.Response withdraw(Double amount, Wallet.Currency currency) {
        Wallet.Withdraw withdraw = Wallet.Withdraw.newBuilder()
                .setUserId(1)
                .setAmount(amount)
                .setCurrency(currency)
                .build();

        return blockingStub.withdraw(withdraw);
    }

    public Wallet.Balances balance(Integer userId) {
        Wallet.User user = Wallet.User.newBuilder().setUserId(userId).build();

        return blockingStub.balance(user);
    }

}
