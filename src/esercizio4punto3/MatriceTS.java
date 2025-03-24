package esercizio4punto3;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;

 public class MatriceTS {
    private static int N = 5;
    private static int M = 5;
    private static int X = 100;
    private static int[][] matrice = new int[N][M];
    private static Semaphore[][] semafori = new Semaphore[N][M];

    static {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                semafori[i][j] = new Semaphore(1);
            }
        }
    }

    public static void runTS() {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            threads.add(new decrementaRigaTS(i));
        }
        for (int j = 0; j < M; j++) {
            threads.add(new incrementaColonnaTS(j));
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class decrementaRigaTS extends Thread {
        private int indice_riga;
        public decrementaRigaTS(int indice_riga) {
            this.indice_riga = indice_riga;
        }
        public void run() {
            for (int indice_colonna = 0; indice_colonna < M; indice_colonna++) {
                for (int rep = 0; rep < X; rep++) {
                    try {
                        semafori[indice_riga][indice_colonna].acquire();
                        matrice[indice_riga][indice_colonna]--;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semafori[indice_riga][indice_colonna].release();
                    }
                }
            }
        }
    }
    static class incrementaColonnaTS extends Thread {
        private int indice_colonna;
        public incrementaColonnaTS(int indice_colonna) {
            this.indice_colonna = indice_colonna;
        }
        public void run() {
            for (int indice_riga = 0; indice_riga < N; indice_riga++) {
                for (int rep = 0; rep < X; rep++) {
                    try {
                        semafori[indice_riga][indice_colonna].acquire();
                        matrice[indice_riga][indice_colonna]++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semafori[indice_riga][indice_colonna].release();
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("Matrice iniziale: ");
        System.out.println();
        for (int i = 0; i < N; i++) {
            System.out.print("{ ");
            System.out.print(Arrays.toString(matrice[i]));
            System.out.print(" }");
            System.out.println();
        }
        runTS();
        System.out.println();
        System.out.println("Matrice finale: ");
        System.out.println();
        for (int i = 0; i < N; i++) {
            System.out.print("{ ");
            System.out.print(Arrays.toString(matrice[i]));
            System.out.print(" }");
            System.out.println();
        }
    }
}

