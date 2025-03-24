package linkedlist;

import java.util.Iterator;
import java.util.LinkedList;

public class Iterazione {

    //l'iterazione in una linkedlist funziona tramite un for each o l'inizializzazione di un iteratore

    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();

        for(String str : linkedList){
            System.out.println(str);
        }


        Iterator it = linkedList.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }

}
