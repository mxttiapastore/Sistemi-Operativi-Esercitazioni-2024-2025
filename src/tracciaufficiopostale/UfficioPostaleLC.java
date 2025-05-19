package tracciaufficiopostale;

import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UfficioPostaleLC extends UfficioPostale {

    private TreeMap<String , Integer> tickets = new TreeMap<>();
    {
        tickets.put("A", 50);
        tickets.put("B" ,50);
        tickets.put("C" ,50);
    }


    private Lock l = new ReentrantLock();

    private Condition sportello = l.newCondition();
    private Condition vattene = l.newCondition();

    private boolean mioTurno = false;
    private boolean sportelloOccupato = false;
    private String operazioneCorrente;



    public boolean ritiraTicket(String operazione) throws InterruptedException {
        l.lock();
        try{
            if(tickets.get(operazione) == 0){
                System.out.println("Il cliente numero " + Thread.currentThread().getId() + " non può più riritare il ticket per l'operazione "
                        + operazione + ": ticket esauriti.");
                return false;
            }else{
                tickets.put(operazione , tickets.get(operazione)-1);
                System.out.println("Il cliente numero " + Thread.currentThread().getId() + " ha ritirato un ticket per un operazione di tipo " + operazione);
                TimeUnit.MILLISECONDS.sleep(500);
                return true;
            }
        }finally{
            l.unlock();
        }
    }

    @Override
    public void attendiSportello(String operazione) throws InterruptedException {
        l.lock();
        try{
            operazioneCorrente = operazione;

            while (!mioTurno){
                sportello.await();
            }
            mioTurno = false;


        }finally{

        }

    }

    @Override
    public void prossimoCliente() throws InterruptedException {
        l.lock();
        try{

            while(!sportelloOccupato){
                vattene.await();
            }
            sportelloOccupato = false;
            System.out.println("PROSSIMO CLIENTE!");


        }finally {
            l.unlock();
        }

    }

    @Override
    public void eseguiOperazione() throws InterruptedException {
        l.lock();
        try{
            switch (operazioneCorrente){
                case "A" : TimeUnit.SECONDS.sleep(3);
                case "B" : TimeUnit.SECONDS.sleep(5);
                case "C" : TimeUnit.SECONDS.sleep(7);
            }

            mioTurno = true;
            sportello.signal();
            sportelloOccupato = true;
            vattene.signal();
            System.out.println("Operazione di tipo "+operazioneCorrente+ " eseguita");

        }finally {
            l.unlock();
        }
    }

    public static void main(String[] args) {
        UfficioPostale u = new UfficioPostaleLC();
        u.test();
    }
}
