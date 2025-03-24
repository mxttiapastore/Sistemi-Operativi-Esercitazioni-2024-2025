package provathreadstampante;

public class StampanteT extends Thread{
    private int da;
    private int a;


    public StampanteT(int da, int a) {
        this.da = da;
        this.a = a;
    }

    public void run() {
        for(int i = da; i <= a; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        Thread t = Thread.currentThread();
        System.out.println(t.getId() + " " + t.getName());
    }


}
