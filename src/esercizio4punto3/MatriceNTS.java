package esercizio4punto3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatriceNTS {

    private static int N = 5;
    private static int M = 5;
    private static int X = 100;
    private static int[][] matrice = new int[N][M];

    public static void runNTS(){


        List<Thread> threads = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            threads.add(new decrementaRiga(i));
        }

        for(int i = 0 ; i < M ; i++){
            threads.add(new incrementaColonna(i));
        }

        for(Thread t : threads){ t.start();}

    }

    static class decrementaRiga extends Thread{
        private int indice_riga;

        public decrementaRiga(int indice_riga){
            this.indice_riga = indice_riga;
        }

        public void run(){
            for(int indice_colonna = 0; indice_colonna < M ; indice_colonna++){
                for(int rep = 0 ; rep < X ; rep++){
                    matrice[indice_riga][indice_colonna]--;
                }
            }
        }

    }

    static class incrementaColonna extends Thread{
        private int indice_colonna;

        public incrementaColonna(int indice_colonna){
            this.indice_colonna = indice_colonna;
        }

        public void run(){
            for(int indice_riga = 0 ; indice_riga < N ; indice_riga++){
                for(int rep = 0; rep < X ; rep++){
                    matrice[indice_riga][indice_colonna]++;
                }
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("Matrice iniziale: ");
        System.out.println();

        for(int i = 0; i < N ; i++){
            System.out.print("{ ");
            System.out.print(Arrays.toString(matrice[i]));
            System.out.print(" }");
            System.out.println();
        }

        runNTS();
        System.out.println();
        System.out.println("Matrice finale: ");
        System.out.println();

        for(int i = 0; i < N ; i++){
            System.out.print("{ ");
            System.out.print(Arrays.toString(matrice[i]));
            System.out.print(" }");
            System.out.println();
        }




    }

}
