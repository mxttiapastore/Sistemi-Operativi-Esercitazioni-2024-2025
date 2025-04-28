package esercizi3;

public class Es3punto2 {

    public static void main(String[] args) throws InterruptedException {
        BW t1 = new BW(true);
        t1.start();
        Thread.State s0 = Thread.currentThread().getState();
        Thread.State s1 = t1.getState();
        System.out.println("State 0: " + s0 + "; " + " State 1: " + s1);
        t1.busy_waiting = s0.equals(s1);
    }

    static class BW extends Thread{
        public boolean busy_waiting;
        public BW(boolean bw){
            busy_waiting = bw;
        }

        public void run(){
            do{
                try{
                    Thread.sleep(10);
                }catch (Exception e){}
            }while(busy_waiting);
            System.out.println("sisop-corsoB");
        }
    }
}
