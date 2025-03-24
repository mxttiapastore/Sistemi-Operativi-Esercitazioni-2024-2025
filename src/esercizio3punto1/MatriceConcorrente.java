package esercizio3punto1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MatriceConcorrente {

    private static final int N = 5;
    private static final int M = 5;
    private static final int X = 20;

    private static final AtomicInteger[][] matriceTS = new AtomicInteger[N][M];

    private static final int[][] matriceNTS = new int[N][M];

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Soluzione non thread safe");
        runNTS();
        stampaMatriceNTS(matriceNTS);

        System.out.println("Soluzione thread safe");
        runTS();
        stampaMatriceTS(matriceTS);

    }

    private static void stampaMatriceTS(AtomicInteger[][] matriceTS) {
        for(int i = 0; i < matriceTS.length; i++) {
            for(int j = 0; j < matriceTS[i].length; j++) {
                System.out.print(matriceTS[i][j] + " ");
            }
        }
    }
    private static void stampaMatriceNTS(int[][] matriceNTS) {
        for(int i = 0; i < matriceNTS.length; i++) {
            for(int j = 0; j < matriceNTS[i].length; j++) {
                System.out.print(matriceNTS[i][j] + " ");
            }
        }
    }

    private static void runTS() throws InterruptedException{
        for(int i = 0; i < matriceTS.length; i++) {
            for(int j = 0; j < matriceTS[i].length; j++) {
                matriceTS[i][j] = new AtomicInteger(0);
            }
        }

        List<Thread> threads = new ArrayList<Thread>();

        for(int i = 0; i < N; i++) {
            threads.add(new decrementaRigaTS(i));
        }

        for(int i = 0; i < M; i++){
            threads.add(new incrementaColonnaTS(i));
        }

        for(Thread thread : threads) {
            thread.start();
        }

        for(Thread thread : threads) {
            thread.join();
        }


    }

    private static void runNTS() throws InterruptedException{
        for(int i = 0; i < matriceNTS.length; i++) {
            for(int j = 0; j < matriceNTS[i].length; j++) {
                matriceNTS[i][j] = 0;
            }
        }

        List<Thread> threads = new ArrayList<>();

        for(int i = 0; i<N; i++) {
            threads.add(new decrementaRiga(i));
        }

        for(int j = 0; j<M; j++) {
            threads.add(new incrementaColonna(j));
        }

        for(Thread t : threads){
            t.start();
        }

        for(Thread t : threads){
            t.join();
        }


    }

    static class decrementaRigaTS extends Thread{

        private int indiceRiga;

        public decrementaRigaTS(int indiceRiga){
            this.indiceRiga= indiceRiga;
        }

        public void run(){
            for(int indiceColonna = 0; indiceColonna<M; indiceColonna++){
                for(int numeroVolte = 0; numeroVolte<X; numeroVolte++){
                    matriceTS[indiceRiga][indiceColonna].decrementAndGet();
                }
            }
        }
    }

    static class incrementaColonnaTS extends Thread{

        private int indiceColonna;

        public incrementaColonnaTS(int indiceColonna){
            this.indiceColonna = indiceColonna;
        }

        public void run(){
            for(int indiceRiga = 0 ; indiceRiga < N ; indiceRiga++){
                for(int numeroVolte = 0 ; numeroVolte < X ; numeroVolte++){
                    matriceTS[indiceRiga][indiceColonna].incrementAndGet();
                }
            }
        }


    }

    static class decrementaRiga extends Thread{

        private int indiceRiga;

        public decrementaRiga(int indiceRiga){
            this.indiceRiga= indiceRiga;
        }

        public void run(){
            for(int indiceColonna = 0; indiceColonna<M; indiceColonna++){
                for(int numeroVolte = 0; numeroVolte<X; numeroVolte++){
                    matriceNTS[indiceRiga][indiceColonna]--;
                }
            }
        }
    }

    static class incrementaColonna extends Thread{

        private int indiceColonna;

        public incrementaColonna(int indiceColonna){
            this.indiceColonna = indiceColonna;
        }

        public void run(){
            for(int indiceRiga = 0 ; indiceRiga < N ; indiceRiga++){
                for(int numeroVolte = 0 ; numeroVolte < X ; numeroVolte++){
                    matriceNTS[indiceRiga][indiceColonna]++;
                }
            }
        }


    }






}
