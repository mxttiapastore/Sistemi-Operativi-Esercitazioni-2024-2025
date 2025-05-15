package tracciacasadicura;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CasaDiCuraLC extends CasaDiCura {

    private Lock lock = new ReentrantLock(true);
    private Condition salaPreparazione = lock.newCondition();
    private Condition salaOperatoria = lock.newCondition();
    private Condition uscita = lock.newCondition();
    private boolean esci = false;
    private boolean salaOperatoriaLibera = false;
    private int sala = 3;



    @Override
    public void chiamaEIniziaOperazione() throws InterruptedException {
        lock.lock();
        try{
            salaOperatoriaLibera = true;
            System.out.println("sala pronta");
            salaOperatoria.signal();
        }finally {
            lock.unlock();
        }

    }

    @Override
    public void fineOperazione() throws InterruptedException {
        lock.lock();
        try{
            sala--;
            esci = true;
            uscita.signal();
            System.out.println("Il medico sta preparando la sala");
            salaPreparazione.signalAll();

        }finally{
            lock.unlock();
        }

    }

    @Override
    public void pazienteEntra() throws InterruptedException {
        lock.lock();
        try {
            System.out.println("Il paziente numero " + Thread.currentThread().getId() + " deve entrare in ospedale");
            while(sala == 3){
                salaPreparazione.await();
            }
            sala++;
            System.out.println("Il paziente numero " + Thread.currentThread().getId() + " sta aspettando di essere chiamato in sala d'attesa");
            while(!salaOperatoriaLibera){
                salaOperatoria.await();
            }
            salaOperatoriaLibera = false;
            System.out.println("Il paziente numero " + Thread.currentThread().getId() + " è entrato in sala operatoria");
        }finally {
            lock.unlock();
        }

    }

    @Override
    public void pazienteEsci() throws InterruptedException {
        lock.lock();
        try{
            while(!esci){
                uscita.await();
            }
            System.out.println("Il paziente numero " + Thread.currentThread().getId() + " è uscito dall'ospedale");
            esci = false;
        }finally{
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CasaDiCura c = new CasaDiCuraLC();
        c.test();
    }


}
