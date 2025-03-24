package esercizio2punto2;

public class ProdottoScalare extends  Thread{

    private int[] a, b;
    private int inizio, fine;
    private int risultato = 0;

    public ProdottoScalare(int[] a, int[] b, int inizio, int fine) {
        this.a = a;
        this.b = b;
        this.inizio = inizio;
        this.fine = fine;
    }

    public void run() {
        for(int i = inizio ; i < fine ; i++){
            risultato += a[i] * b[i];
        }

    }

    public int getRisultato() {
        return risultato;
    }
}

