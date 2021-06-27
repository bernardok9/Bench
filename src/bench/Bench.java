package bench;

import java.util.ArrayList;
import java.util.Collection;

class Multithreading extends Thread {

    public void run() {
        double var;
        try {

            var = Math.log10(37.71);
            var = Math.sin(2.46);
            var = Math.cos(0.27);
            var = Math.sqrt(7.35);
            var = 2.57 / 7.77;
            var = Math.exp(3.95);
            System.out.println(var);
        } catch (Exception e) {
            System.out.println("Exception is caught");
        }

    }
}

public class Bench {

    public static void main(String[] args) {
        final int NUM_THREADS = Runtime.getRuntime().availableProcessors();
        long tinicial = System.currentTimeMillis();
        int loops = 10;
        int score = 0;

        Collection<Multithreading> threads = new ArrayList<>();

        for (int i = 1; i <= NUM_THREADS; i++) {
            Multithreading thread = new Multithreading();
            thread.setName("Thread " + i);
            threads.add(thread);
        }

        for (Multithreading mt : threads) {
            mt.start();
        }

        for (Multithreading mt : threads) {
            try {
                mt.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        System.out.println("Tempo: " + (System.currentTimeMillis() - tinicial));

    }

}
