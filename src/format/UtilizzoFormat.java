package format;

public class UtilizzoFormat {

    //format è un metodo della classe String che, appunto, 'formatta' una strina tramite
    //dei termini % che si differenziano in base al tipo di oggetto che voglio returnare

    /*

    %c per i caratteri unicode
    %d per gli integer decimali (incluse byte, short, int, long, bigint)
    %f per i numeri decimali (float)
    %n per serparare una linea
    %s per qualsiasi valore String
    %t per i formati Data/Ora





     */

    public static void main(String[] args) {

        String name = "pippo";
        int eta = 35;

        //utilizzando format
        String output = String.format("mi chiamo %s. Ho %d anni", name, eta);
        //senza utilizzare format
        String output2 = "Mi chiamo " + name + " e ho: " + eta + " anni";
    }
}
