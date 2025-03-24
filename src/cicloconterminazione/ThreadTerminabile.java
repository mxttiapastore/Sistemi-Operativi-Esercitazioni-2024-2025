package cicloconterminazione;

public class ThreadTerminabile extends  Thread{

    private boolean continua = true;
    public void termina(){
        continua = false;
    }

    public void run(){
        while(continua){
            // ...
            // istruzioni
            // ...
            termina();
        }
    }
}
