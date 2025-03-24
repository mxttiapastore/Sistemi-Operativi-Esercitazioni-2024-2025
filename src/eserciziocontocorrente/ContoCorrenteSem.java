package eserciziocontocorrente;

import java.util.concurrent.Semaphore;

public class ContoCorrenteSem extends ContoCorrente{

    private Semaphore mutex = new Semaphore(1);

    public ContoCorrenteSem(int deposito_iniziale) {
        super(deposito_iniziale);
    }

    public void deposita(int importo){
        try{
            mutex.acquire();
            //acquisizione di un permesso da mutex : ora num.permessi disponibili = 0
            deposito+= importo;
            mutex.release();
            //rilascio di un permesso a mutex: ora num.permessi disponibili = 1

        }catch (InterruptedException e){}
    }

    public void preleva(int importo){
        try{
            mutex.acquire();
            deposito -= importo;
            mutex.release();

        }catch (InterruptedException e){}
    }




}
