package bench;

public class Multithreading implements Runnable {

    @Override
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
