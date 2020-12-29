public class Tiempo {
    private int []tiempo = {0, 0, 20, 0, 0, 0, 0, 0, 10, 20, 0, 0, 0, 0, 0, 0, 0};
    private long []tiempoInicial = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    // Transiciones = ( 0 - 1 - 10 - 11 - 12 - 13 - 14 - 15 - 16 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - nada)
    public Tiempo() {

    }

    public void setTiempoActual (long tiempo, int []transicion) {
        for (int i = 0; i <transicion.length ; i++) {
            if (transicion[i] == 1) {
                tiempoInicial[i] = tiempo;
            }
        }
        return;
    }

    public boolean esTemporal (int []transicion) {
        boolean temp = false;
        for (int i = 0; i <transicion.length ; i++) {
            if (transicion[i] == 1) {
                if (tiempo[i]>0) {
                temp = true;
                break;
                }
            }
        }
        return temp;
    }

    public long calcularTiempo(int []transicion) {
        int t = 0;
        if (Thread.currentThread().getName().equals("ARRIBO")) {
            t = 8;
        }
        if (Thread.currentThread().getName().equals("SERVICIO 1")) {
            t = 9;
        }
        if (Thread.currentThread().getName().equals("SERVICIO 2")) {
            t = 2;
        }
        long tiempoinicial = tiempoInicial[t];
        long tiempofinal = System.currentTimeMillis() - tiempoinicial;
        long tiemporestante = tiempo[t] - tiempofinal;
        return tiemporestante;
    }

    public void displayTiempo() {
        System.out.println("Tiempo iniciales");
        for (int i = 0; i < tiempoInicial.length; i++) {
            System.out.print(tiempoInicial[i] + " ");
        }
    }
}
