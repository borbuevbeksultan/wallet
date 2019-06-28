package kg.apps.emulation;

import kg.apps.rounds.RoundProvider;

public class RequestEmulator implements Runnable {

    private int numberOfRounds;

    RequestEmulator(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfRounds; i++) {
            RoundProvider.getRandomRound().execute();
        }
    }

}
