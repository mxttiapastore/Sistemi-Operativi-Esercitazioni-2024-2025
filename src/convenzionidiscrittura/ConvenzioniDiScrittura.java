package convenzionidiscrittura;

import java.util.ArrayList;
import java.util.List;

public class ConvenzioniDiScrittura {

    //i nomi dei pacchetti devono essere tutti in minuscolo (es. it.unical.dimes.sisop)

    //i nomi delle classi devono iniziare con la lettera maiuscola (ConvenzoniDiScrittura)

    //i nomi delle variabili e metodi devono iniziare con la lettera minuscola (es. camelCase)

    //i nomi delle costanti devono essere tutte in maiuscolo (es. EMBENDEED_UNDERSCORE)

    //le variabili con ampia visibilità devono avere nomi lunghi, mentre quelle con ridotta visibilità devono avere nomi brevi

    //esempio:
    class LibrettoUniversitario{
        private int numEsamiSostenuti; //OK
        private int n; //NO

        public List<Integer> getEsamiDaSostenere(){
            List<Integer> esami = new ArrayList<Integer>();
            for(int i = 0; i < numEsamiSostenuti; i++){} //OK
            return esami;
        }
    }

    //i nomi dei metodi e delle variabili non devono contenere il nome dell'oggetto a cui si riferiscono
    class Persona{
        private String nome;//OK
        private String nomePersona; //NO
    }


    //si usa il plurale per i nomi delle variebili che rappresentano un insieme di oggetti
    private Persona[] persone;
    // si utilizzano diversi prefissi
    // 'num' per variabili che rappresentano un numero di oggetti
    // 'id' per variabili che identificano un'entità
    // 'max' e 'min' per variabili che rappresentano un limite massimo o minimo

    private int numPersone;
    private String idPersona;
    private int maxPersone;

    //evitare di usare nomi negati per le variabili booleane
    private boolean trovato;

    //le variabili devono esssere inizializzate con la visibilità più ridotta possibile
    //(deve essere dichiarata nel blocco più interno che racchiude tutti i suoi possibili usi)

    //non usare più di una variabile per memorizzare la stessa informazione, almeno che
    //non ci sia vantaggio in termini di prestazioni

    class Contenitore{
        private int numElementi = 0;
        private boolean vuoto = true; //NO
    }

    //espressioni condizionali complesse devono essere evitate
    //introdurre al loro posto variabili booleane temporanee
    Persona persona = new Persona();
    List<Persona> persone1 = new ArrayList<Persona>();
    public boolean inserisciPersona(Persona persona){

        if(!persone1.contains(persona) && persone1.size() < maxPersone){
            persone1.add(persona); //NO
        }
        boolean inserito = false;
        boolean possoInserire = !persone1.contains(persona) && persone1.size() < maxPersone;
        if(possoInserire){ persone1.add(persona);inserito = true; } //SI CAZZO

        return inserito;
    }

    //usare sempre degli array per memorizzare variabili dello stesso tipo che contengono
    //lo stesso tipo di informazione

    //NO
    int punteggio1 = 0;
    int punteggio2 = 0;
    void stampaPunteggio(int idSquadra)
    {
        if(idSquadra == 0){System.out.println(punteggio1);}
        else{System.out.println(punteggio2);}
    }

    //SI
    private final int NUM_SQUADRE = 2;
    int[] punteggio = new int[NUM_SQUADRE];

    void stampaPunteggio2(int idSquadra){
        System.out.println(punteggio[idSquadra]);
    }

}



