package esercizio2punto2;

public class Main{
    public static void main(String[] args) throws InterruptedException {

        final int n = 8;  // Dimensione degli array (multiplo di m)
        final int m = 4;  // Numero di thread

        int[] a = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] b = {8, 7, 6, 5, 4, 3, 2, 1};

        ProdottoScalare[] threads = new ProdottoScalare[m];
        
        int risultato_totale = 0;

        for(int i = 0; i < m; i++){
            threads[i].join();
            risultato_totale += threads[i].getRisultato();
            
        }
        
        System.out.println("RISULTATO TOTALE: " + risultato_totale);

    }
}
