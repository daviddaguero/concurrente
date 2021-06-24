public class Politica {
    private Buffer buffer1;
    private Buffer buffer2;
    private RedDePetri rdp;
    private int confli = 0;
    private int[] prioridades = {11, 11, 13,  13,  11,  11,  11,  11,  11, 13, 13, 11,  11, 11, 11, 11, 11, -2};//prioridades[i] = prioridad de la transición i.
    // Transiciones =              ( 0 - 1 - 10 - 11 - 12 - 13 - 14 - 15 - 16 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - nada)
    public Politica(Buffer buffer1, Buffer buffer2, RedDePetri rdp) {
        this.buffer1=buffer1;
        this.buffer2=buffer2;
        this.rdp = rdp;
    }

    public int resolverConflictoRandom(int[] sens) {
        int r = (int) (Math.random() * sens.length);
        return sens[r];
    }

    public int decidir(int[] sens) {
        int elegida = -1;

        if (sens[0] == 1 && sens[15] == 1) {
            confli = 1;
        }

        if (confli == 1) {
            confli = 0;
            double random = Math.random();
            int b1 = buffer1.getElementos();
            int b2 = buffer2.getElementos();
            if (b1 > b2) { elegida = 15;}
            if (b2 > b1) { elegida = 0;}
            if (b1 == b2) {
                if(random > 0.5) {
                    elegida = 15;
                }
                else {elegida = 0;}
            }
            return elegida;
        }
        else {
            int r = (int) (Math.random() * sens.length);
            while (sens[r] == 0) {
                r = (int) (Math.random() * sens.length);
            }
            return elegida = r;
        }
    }

    public int resolverConflicto(int[] sens) {
        int elegida = prioridades.length - 1; //transición con menos prioridad
        for(int i = 0; i < sens.length; i++) {
            double random = Math.random();
            if(prioridades[sens[i]] > prioridades[elegida]) {
                elegida = sens[i];
            } else if(prioridades[sens[i]] == prioridades[elegida]) {
                if(random > 0.5) {
                    elegida = sens[i];
                }
            }
        }
        if ((elegida == 0) || (elegida == 15)) {
            double random = Math.random();
            int b1 = buffer1.getElementos();
            int b2 = buffer2.getElementos();
            if (b1 > b2) { elegida = 15;}
            if (b2 > b1) { elegida = 0;}
            if (b1 == b2) {
                if(random > 0.5) {
                    elegida = 15;
                }
                else {elegida = 0;}
            }
        }
        return elegida;
    }
}