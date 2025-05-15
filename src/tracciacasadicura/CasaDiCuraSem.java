package tracciacasadicura;

import java.util.concurrent.Semaphore;

public class CasaDiCuraSem extends CasaDiCura{


    private Semaphore salaPreparazione = new Semaphore(CAPIENZA_SALA_PICCOLA);
    private Semaphore salaOperazione = new Semaphore(0 ,  true);
    private Semaphore uscitaPazienteConsentita = new Semaphore(0);



    @Override
    public void chiamaEIniziaOperazione() throws InterruptedException{
        salaOperazione.release();

    }

    @Override
    public void fineOperazione() throws InterruptedException{
        salaPreparazione.release();
        uscitaPazienteConsentita.release();
        System.out.println("Il medico sta preparando la sala");

    }

    @Override
    public void pazienteEntra() throws InterruptedException{
        System.out.println("Il paziente numero " + Thread.currentThread().getId() + " deve entrare in ospedale");
        salaPreparazione.acquire();
        System.out.println("Il paziente numero " + Thread.currentThread().getId() + " sta aspettando di essere chiamato in sala d'attesa");
        salaOperazione.acquire();
        System.out.println("Il paziente numero " + Thread.currentThread().getId() + " è entrato in sala operatoria");


    }

    @Override
    public void pazienteEsci() throws InterruptedException{
        uscitaPazienteConsentita.acquire();
        System.out.println("Il paziente numero " + Thread.currentThread().getId() + " è uscito dall'ospedale");
    }

    public static void main(String[] args) throws InterruptedException {
        CasaDiCura c = new CasaDiCuraSem();
        c.test();
    }
}
