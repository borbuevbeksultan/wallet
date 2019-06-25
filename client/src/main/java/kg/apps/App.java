package kg.apps;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Random;

public class App {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8888)
                .usePlaintext().build();

        WalletServiceGrpc.WalletServiceBlockingStub blockingStub = WalletServiceGrpc.newBlockingStub(channel);

        Wallet.Deposit deposit;
        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            deposit = Wallet.Deposit.newBuilder()
                    .setUserId(1)
                    .setAmount(random.nextDouble())
                    .setCurrency(Wallet.Currency.GBP)
                    .build();
            blockingStub.deposit(deposit);
        }
    }
}
