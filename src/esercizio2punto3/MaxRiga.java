package esercizio2punto3;

class MaxRiga extends Thread {

    private int[] riga;
    private int indice_riga;
    private int valore_massimo;
    private int indice_massimo;

    public MaxRiga(int[] riga, int indice_riga) {
        this.riga = riga;
        this.indice_riga = indice_riga;
    }

    public void run(){
        valore_massimo = riga[0];
        indice_massimo = 0;
        for(int i = 1; i < riga.length; i++){
            if(riga[i] > valore_massimo){
                valore_massimo = riga[i];
                indice_massimo = i;
            }
        }
    }

    public int getValoreMassimo() { return valore_massimo; }
    public int getIndiceMassimo() { return indice_massimo; }
    public int getIndiceRiga() { return indice_riga; }
}

class MinColonna extends Thread {
    private int[][] matrice;
    private int indice_colonna;
    private int valore_minimo;
    private int indice_minimo;


    public MinColonna(int[][] matrice, int indice_colonna) {
        this.matrice = matrice;
        this.indice_colonna = indice_colonna;
    }

    public void run(){
        valore_minimo = matrice[0][indice_colonna];
        indice_minimo = 0;
        for(int i = 1; i < matrice.length; i++){
            if(matrice[i][indice_colonna] < valore_minimo){
                valore_minimo = matrice[i][indice_colonna];
                indice_minimo = i;
            }
        }
    }

    public int getValoreMinimo() { return valore_minimo; }
    public int getIndiceColonna() { return indice_colonna; }
    public int getIndiceMinimo() { return indice_minimo; }

}

