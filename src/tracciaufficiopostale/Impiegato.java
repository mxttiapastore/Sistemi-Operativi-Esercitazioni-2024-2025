package tracciaufficiopostale;

public class Impiegato extends Thread {

    private UfficioPostale ufficio;

    public Impiegato(UfficioPostale u){
        ufficio = u;
    }

    public void run(){
        try{
            ufficio.eseguiOperazione();
            ufficio.prossimoCliente();
        }catch (InterruptedException e){}

    }
}
