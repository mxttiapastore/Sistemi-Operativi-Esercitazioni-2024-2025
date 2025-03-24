package provathreadstampante;

public class StampanteCurrentThreadTest {
    public static void main(String[] args) {

        Thread t = Thread.currentThread();
        System.out.println(t.getId() + " " + t.getName());
        StampanteT t1 = new StampanteT(1,10);
        t1.run();
    }
}
