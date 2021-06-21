package bench;

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
        } catch (Exception e) {
            System.out.println("Exception is caught");
        }
    }
}

public class Bench {

    public static void main(String[] args) {
        final int NUM_THREADS = Runtime.getRuntime().availableProcessors() + 1;
        int loops = 30000;
        int score = 0;
        // Esse for era para executar infinitamente eles, até o tempo limite. Mas não pode utilizar time. a
        for (int y = 0; y < loops; y++) {
            // Esse loop é para executar elas todas, porém tem que arrumar um meio de executar elas independentes.
            for (int i = 0; i < NUM_THREADS; i++) {
                Multithreading object = new Multithreading();
                object.start();
                score++;
            }
        }
        System.out.println();
        System.out.println(score);
    }

}
