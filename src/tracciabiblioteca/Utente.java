package tracciabiblioteca;

public class Utente extends Thread{

    private Biblioteca biblioteca;
    private String tipo;

    public Utente(Biblioteca b , String t){
        biblioteca = b;
        tipo = t;
    }

    public void run(){
        try{
            biblioteca.richiediPrestito();
            biblioteca.esci();
        }catch (InterruptedException e){}
    }

    public String getTipo(){
        return tipo;
    }
}
