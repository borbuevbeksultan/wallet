package kg.apps.emulation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientEmulator implements Runnable {

    private int threads;
    private int rounds;

    public ClientEmulator(int threads, int rounds) {
        this.threads = threads;
        this.rounds = rounds;
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(threads);

        for (int i = 0; i < threads; i++) {
            executorService.submit(new RequestEmulator(rounds));
        }

        executorService.shutdown();
    }

}
