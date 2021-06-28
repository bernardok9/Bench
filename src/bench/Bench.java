package bench;

import java.util.ArrayList;
import java.util.Collection;

public class Bench {

    public static void main(String[] args) {
        final int NUM_THREADS = Runtime.getRuntime().availableProcessors();
        int loops = 10;
        int score = 0;
        long tinicial = System.currentTimeMillis();

        Collection<Thread> threads = new ArrayList<>();
        for (int j = 0; j < 10000; j++) {
            //Cria os Thread baseado nos núcleos do pc;
            for (int i = 1; i <= NUM_THREADS; i++) {
                Thread thread = new Thread(new Multithreading());
                thread.setName("Thread " + i);
                threads.add(thread);
            }

            //Inicia os Thread;
            for (Thread th : threads) {
                th.start();

            }

            //Starta threads já criados;
            for (Thread th : threads) {
                try {
                    th.join();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            threads.clear();
        }
        
        System.out.println("Tempo: " + (System.currentTimeMillis() - tinicial));

    }

}
