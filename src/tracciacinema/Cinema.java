package tracciacinema;

public abstract class Cinema {

    protected final int N = 20, B_MIN = 1, B_MAX = 60;

    public abstract void acquistaBiglietto() throws InterruptedException;

    public abstract boolean consegnaBiglietto() throws InterruptedException;

    public abstract void vediFilm() throws InterruptedException;

    public abstract void chiudiCinema() throws InterruptedException;

    public void test(){
        new Addetto(this).start();
        for(int i = 0 ; i < N ; i++){
            new Cliente(this).start();
        }
    }



}
