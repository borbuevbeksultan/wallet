package kg.apps;

import kg.apps.client.UserClient;
import kg.apps.emulation.ClientEmulator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WalletClient {
    public static void main(String[] args) {
        int concurrentUsers = Integer.valueOf(args[0]);
        int concurrentRequests = Integer.parseInt(args[1]);
        int rounds = Integer.valueOf(args[2]);

        UserClient userClient = new UserClient();

        ExecutorService usersExecutor = Executors.newFixedThreadPool(concurrentUsers);
        for (int i = 0; i < concurrentUsers; i++) {
            Wallet.UserCreationResponse userCreationResponse = userClient.create();
            usersExecutor.submit(new ClientEmulator(userCreationResponse.getUserId(), concurrentRequests, rounds));
        }

        usersExecutor.shutdown();

    }
}
