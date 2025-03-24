package hashmap;

import java.util.HashMap;
import java.util.Map;

public class Iterazione {

    //l'iterazione nelle hasmap si può fare utilizzando un for each e sfruttando le Entry
    //le Entry sono singole coppie chiave-valore e tramite l'utilizzo di entrySet()
    //richiamato sulla mappa che mi interessa genera un set di tutte le Entry in mappa

    public static void main(String[] args) {

        HashMap<String , Integer> map = new HashMap<String, Integer>();

        for(Map.Entry<String , Integer> entry : map.entrySet()){
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("key = " + key + " value = " + value);
        }

        for(String key : map.keySet()){
            System.out.println(key);
        }
        for(Integer value : map.values()) {
            System.out.println(value);
        }

    }
}
