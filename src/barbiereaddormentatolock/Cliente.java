package barbiereaddormentatolock;

public class Cliente extends Thread{

    private Sala sala;
    private int ID;

    public Cliente(Sala s, int i){
        sala = s;
        ID = i;
    }

    public void run(){
        try {
            System.out.println("Il cliente " + ID + " vuole tagliarsi i capelli");
            boolean ret = sala.attendiTaglio();
            if(ret)
                System.out.println("Il cliente " + ID + " è riuscito a tagliarsi i capelli");
            else
                System.out.println("Il cliente " + ID + " abbandona la sala");
        } catch (InterruptedException e ){e.printStackTrace();}
    }

    public int getID(){return ID;}

    public int hashCode() {return this.hashCode();}

    public boolean equals(Object o){
        if (o == null) return false;
        if (o == this) return true;

        Cliente c = (Cliente) o;
        return c.getID() == ((Cliente) o).getID();
    }
}
