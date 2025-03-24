package esercizio3punto1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MatriceConcorrenteTest {

    //Parametri da consegna
    private static final int N = 5;
    private static final int M = 5;
    private static final int X = 1000;

    //Matrice THREAD_SAFE
    private static AtomicInteger[][] matriceTS = new AtomicInteger[N][M];

    //Matrice NON-THREAD_SAFE
    private static int[][] matriceNTS = new int[N][M];


    public static void main(String[] args) throws InterruptedException {
        System.out.println("---SOLUZIONE THREAD-SAFE---");
        runTS(); //inizializza la matrice a zero
        stampaMatriceTS(matriceTS);
        System.out.println("---SOLUZIONE NON THREAD-SAFE---");
        runNTS(); //inizializza la matrice a zero
        stampaMatriceNTS(matriceNTS);
    }

    private static void stampaMatriceTS(AtomicInteger[][] matriceTS) {
        for (int i = 0; i < matriceTS.length; i++) {
            for (int j = 0; j < matriceTS[i].length; j++) {
                System.out.print(matriceTS[i][j] + "\t");
            }
        }
        System.out.println();
    }

    private static void stampaMatriceNTS(int[][] matriceNTS) {
        for (int i = 0; i < matriceNTS.length; i++) {
            for (int j = 0; j < matriceNTS[i].length; j++) {
                System.out.print(matriceNTS[i][j] + "\t");
            }
        }
        System.out.println();
    }

    //metodo di inizializzazione della matrice THREAD-SAFE e modifica di essa
    private static void runTS() throws InterruptedException {
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                matriceTS[i][j] = new AtomicInteger(0);
            }
        }

        List<Thread> threads = new ArrayList<Thread>();

        for(int riga = 0; riga < N; riga++){
            threads.add(new decrementaRigaTS(riga));
        }

        for(int colonna = 0; colonna < M; colonna++){
            threads.add(new IncrementaColonnaTS(colonna));
        }

        for(Thread thread : threads){
            thread.start();
        }
        for(Thread thread : threads){
            thread.join();
        }

    }

    //metodo di inizializzazione della matrice NON THREAD-SAFE
    private static void runNTS() throws InterruptedException {
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                matriceNTS[i][j] = 0;
            }
        }
        List<Thread> threads = new ArrayList<Thread>();

        for(int riga = 0; riga < N; riga++){
            threads.add(new decrementaRigaNTS(riga));
        }

        for(int colonna = 0; colonna < M; colonna++){
            threads.add(new IncrementaColonnaNTS(colonna));
        }

        for(Thread thread : threads){
            thread.start();
        }
        for(Thread thread : threads){
            thread.join();
        }
    }

    //threads che decrementano la riga e incrementano la colonna THREAD-SAFE
    static class decrementaRigaTS extends Thread{
        private int indice_riga;

        public decrementaRigaTS(int riga){
            this.indice_riga = indice_riga;
        }

        public void run(){
            for(int ripetizione = 0; ripetizione < X; ripetizione++){
                for(int colonna = 0; colonna < M; colonna++){
                    matriceTS[indice_riga][colonna].decrementAndGet();
                }
            }
        }

    }
    static class IncrementaColonnaTS extends Thread{
        private int indice_colonna;

        public IncrementaColonnaTS(int indice_colonna){
            this.indice_colonna = indice_colonna;
        }

        public void run(){
            for(int ripetizione = 0; ripetizione < X; ripetizione++){
                for(int riga = 0; riga < N; riga++){
                    matriceTS[riga][indice_colonna].incrementAndGet();
                }
            }
        }
    }

    //threads che decrementano la riga e incrementano la colonna NON-THREAD-SAFE
    static class decrementaRigaNTS extends Thread{
        private int indice_riga;

        public decrementaRigaNTS(int indice_riga){
            this.indice_riga = indice_riga;
        }

        public void run(){
            for(int ripetizione = 0; ripetizione < X; ripetizione++){
                for(int colonna = 0; colonna < M; colonna++) {
                    matriceNTS[indice_riga][colonna]--;
                }
            }
        }
    }

    static class IncrementaColonnaNTS extends Thread{
        private int indice_colonna;

        public IncrementaColonnaNTS(int indice_colonna){
            this.indice_colonna = indice_colonna;
        }

        public void run(){
            for(int ripetizione = 0; ripetizione < X ; ripetizione++){
                for(int riga = 0; riga < N; riga++){
                    matriceNTS[riga][indice_colonna]++;
                }
            }
        }
    }



}