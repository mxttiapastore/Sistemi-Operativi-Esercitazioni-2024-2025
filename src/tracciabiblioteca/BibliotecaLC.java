package tracciabiblioteca;

import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BibliotecaLC extends Biblioteca {

    protected final int CODICE_MIN = 1111, CODICE_MAX = 9999;
    protected final int TEMPO_MIN = 1, TEMPO_MAX = 5;

    private Random rand = new Random();

    private TreeMap<String, Integer> richiestaPrestito = new TreeMap<>();   //<NomeThread, codiceLibro>
    private TreeMap<String, Integer> registroPrestiti = new TreeMap<>();   //<NomeThread, codiceLibro>

    private Lock l = new ReentrantLock();

    private Condition filaTesserati = l.newCondition();
    private Condition filaEsterni = l.newCondition();
    private Condition registra = l.newCondition();
    private Condition uscita = l.newCondition();
    private Condition prossimo = l.newCondition();

    private int countTesserati = 0;
    private int countEsterni = 0;

    private boolean mioTurnoEsterno;
    private boolean mioTurnoTesserato;
    private boolean possoRegistrare;
    private boolean possoUscire;
    private boolean prossimoUtente;

    public BibliotecaLC() {
        // Consenti al primo cliente di qualsiasi tipo di presentarsi
        mioTurnoTesserato = true;
        mioTurnoEsterno = true;
    }

    @Override
    public void richiediPrestito() throws InterruptedException {
        l.lock();
        try {
            Utente me = (Utente) Thread.currentThread();
            String tipo = me.getTipo();
            // Genera codice libro correttamente
            int libro = rand.nextInt(CODICE_MAX - CODICE_MIN + 1) + CODICE_MIN;

            if (ESTERNO.equals(tipo)) {
                countEsterni++;
                System.out.println("Il cliente " + me.getName() + " si è messo in fila ESTERNI");
                // Attendi il tuo turno esterno
                while (!mioTurnoEsterno) {
                    filaEsterni.await();
                }
                mioTurnoEsterno = false;
                System.out.println("È il turno di " + me.getName() + " (ESTERNO)");

            } else {
                countTesserati++;
                System.out.println("Il cliente " + me.getName() + " si è messo in fila TESSERATI");
                // Attendi il tuo turno tesserato
                while (!mioTurnoTesserato) {
                    filaTesserati.await();
                }
                mioTurnoTesserato = false;
                System.out.println("È il turno di " + me.getName() + " (TESSERATO)");
            }

            // Registra la richiesta e sveglia il bibliotecario
            richiestaPrestito.put(me.getName(), libro);
            possoRegistrare = true;
            registra.signal();

        } finally {
            l.unlock();
        }
    }

    @Override
    public void registraPrestito() throws InterruptedException {
        l.lock();
        try {
            // Attendi che arrivi una richiesta
            while (!possoRegistrare) {
                registra.await();
            }

            // Se ci sono ancora tesserati in attesa, danno loro priorità
            if (countTesserati > 0) {
                mioTurnoTesserato = true;
                filaTesserati.signal();
                countTesserati--;
            } else if (countEsterni > 0) {
                mioTurnoEsterno = true;
                filaEsterni.signal();
                countEsterni--;
            }

            possoRegistrare = false;

            // Preleva la richiesta corrente
            String nome = richiestaPrestito.firstKey();
            int codice = richiestaPrestito.remove(nome);

            // Simula tempo di registrazione senza tenere il lock
            l.unlock();
            int attesa = rand.nextInt(TEMPO_MAX - TEMPO_MIN + 1) + TEMPO_MIN;
            TimeUnit.SECONDS.sleep(attesa);
            l.lock();

            // Registra e segnala al cliente che può uscire
            registroPrestiti.put(nome, codice);
            System.out.println("Il bibliotecario ha registrato il libro " + codice + " per il cliente " + nome);

            possoUscire = true;
            uscita.signal();

        } finally {
            l.unlock();
        }
    }

    @Override
    public void esci() throws InterruptedException {
        l.lock();
        try {
            while (!possoUscire) {
                uscita.await();
            }
            possoUscire = false;
            System.out.println("Il cliente " + Thread.currentThread().getName() + " è uscito dalla biblioteca.");

            prossimoUtente = true;
            prossimo.signal();

        } finally {
            l.unlock();
        }
    }

    @Override
    public void prossimoUtente() throws InterruptedException {
        l.lock();
        try {
            while (!prossimoUtente) {
                prossimo.await();
            }
            prossimoUtente = false;
            System.out.println("Prossimo cliente!");
        } finally {
            l.unlock();
        }
    }

    public static void main(String[] args) {
        Biblioteca b = new BibliotecaLC();
        b.test();
    }
}
