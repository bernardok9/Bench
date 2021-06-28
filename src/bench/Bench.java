package bench;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Bench {
    //Um "Executável" do teste para realizar os experimentos. Não sei se tem necessidade de mexer aqui.
    private static Runnable runzinho = new Runnable() { 

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
            } catch (Exception e) {
                System.out.println("error teste");
            }
        }
    };

    public static void main(String[] args) throws Exception {
        //Função para rodar o teste.
        benchmark();
        
    }

    private static void benchmark() throws InterruptedException {
        //Ele dá get na quantidade de núcleos do seu pc
        final int NUM_CORES = Runtime.getRuntime().availableProcessors();
        //Score para o benchmark
        int score = 0;
        //ExecutorService "Biblioteca de Threads" ;; "newWorkStealingPool" função para paralelismo com o nível desejado.
        ExecutorService executorService = Executors.newWorkStealingPool(NUM_CORES);
        //Get no horário do sistema inicial.
        long tinicial = System.nanoTime();
        //Função para rodar o teste
        for (int i = 0; i < 20000000; i++) {
            //Executa várias vezes o Runnable desejado, definindo as thread automaticamente baseado na função acima.
            executorService.submit(runzinho);
            score++;
        }
        //Encerra o executorService.
        executorService.shutdown();
        //Espera 30seg para finalizar as thread pendentes.
        executorService.awaitTermination(30, TimeUnit.SECONDS);
        //Get no horário do sistema final.
        long tfinal = System.nanoTime();
        long tempomedio = 0;
        //Calcula o tempo médio e converte para milissegundos.
        tempomedio += ((tfinal - tinicial)/1000000);
        
        //Reseta o garbage colletor do pc. Para não retornar sempre "pré-definido" o tempo médio de execução.
        System.gc();

        //Print dos resultados da run.
        System.out.println("Nucleos usados: " + NUM_CORES + "\nTempo: " + (float)tempomedio/1000 + " Segundos\n" + "Score: " + score);
    }
}

/*
Referências: 
https://www.baeldung.com/java-executor-service-tutorial
https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/concurrent/Executors.html#newWorkStealingPool()
https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Runtime.html#availableProcessors()
http://www.inf.ufsc.br/~bosco.sobral/ensino/ine5645/Formas_de_Escalonamento_de_Threads_Java_v3.pdf
https://www.devmedia.com.br/utilizando-threads-parte-1/4459
*/
