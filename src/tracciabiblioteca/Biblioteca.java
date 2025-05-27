package tracciabiblioteca;

import java.util.Random;

public abstract class Biblioteca {

    protected final String ESTERNO = "ESTERNO";
    protected final String TESSERATO = "TESSERATO";
    private String[] tipi = {ESTERNO , TESSERATO};
    private Random rand = new Random();

    protected final int M = 25;

    public abstract void richiediPrestito() throws InterruptedException;

    public abstract void registraPrestito() throws InterruptedException;

    public abstract void esci() throws InterruptedException;

    public abstract void prossimoUtente() throws InterruptedException;


    public void test(){
        new Bibliotecario(this).start();
        for(int i = 0 ; i < M ; i++){
            int scelta = rand.nextInt(2);
            new Utente(this , tipi[scelta]);
        }


    }




}
