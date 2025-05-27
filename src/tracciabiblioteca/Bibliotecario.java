package tracciabiblioteca;

public class Bibliotecario extends Thread{

    private Biblioteca biblioteca;

    public Bibliotecario(Biblioteca b){
        biblioteca = b;
    }

    public void run(){
        try {
            while (true) {
                biblioteca.registraPrestito();
                biblioteca.prossimoUtente();
            }
        }catch (InterruptedException e){}
    }
}
