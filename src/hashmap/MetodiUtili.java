package hashmap;

import java.util.HashMap;

public class MetodiUtili {

    // V put(K key, V value) consente di inserire un nuovo valore in corrispondenza
    // di una chiave, se una chiave è già presente il vecchio valore viene sovrascritto con il nuovoù

    //V get(Object key) reperisce il valore per la chiave passata come parametro, null se tale chiaveù
    //non viene trovata all'interno di una mappa

    //boolean containsKey(Object key) restituisce true se la mappa contiene la chiave passata come
    //parametro, false altrimenti

    //int size() restituisce il numero di coppie <chiave valore> presenti nella struttura dati

    //nota su hashCode() e equals(): se due oggetti sono uguali devono avere lo stesso hashCode

    public static void main(String[] args) {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hello", "world");
        hashMap.put("java", "thread");
        hashMap.put("1", "one");


        //così sostituirà presso la chiave 'hello' il valore 'myworld'
        hashMap.put("hello", "myworld");
    }
}
