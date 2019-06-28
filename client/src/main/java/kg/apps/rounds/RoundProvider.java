package kg.apps.rounds;

import kg.apps.client.WalletClient;

import java.util.Random;

public class RoundProvider {

    private static Random random = new Random();
    private static WalletClient walletClient = new WalletClient();
    private static Round[] rounds = {new RoundA(walletClient), new RoundB(walletClient), new RoundC(walletClient)};

    public static Round getRandomRound() {
        return rounds[random.nextInt(3)];
    }

}
