package kg.apps.emulation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientEmulator implements Runnable {

    private int threads;
    private int rounds;
    private int userId;

    public ClientEmulator(int userId, int threads, int rounds) {
        this.threads = threads;
        this.rounds = rounds;
        this.userId = userId;
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(threads);

        for (int i = 0; i < threads; i++) {
            executorService.submit(new RequestEmulator(userId, rounds));
        }

        executorService.shutdown();
    }

}
