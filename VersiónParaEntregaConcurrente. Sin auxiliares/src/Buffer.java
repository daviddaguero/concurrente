public class Buffer {
    private RedDePetri rdp;
    private int id;

    public Buffer(RedDePetri rdp, int id) {
        this.rdp=rdp;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getElementos() {
        if(id==1) {
            return rdp.getElementosBuffer1();
        }
        else if(id==2) {
            return rdp.getElementosBuffer2();
        }
        return -1;
    }

}
