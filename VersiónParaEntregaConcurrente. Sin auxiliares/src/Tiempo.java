import java.util.List;
import java.util.ArrayList;


public class Tiempo {
    private int []tiempo = {0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, 10};
    private long []tiempoDeSensibilizado = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int []temporizada = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};


    public Tiempo() {
    }

    public List<Integer> getTemporales() {
        List <Integer> devolver = new ArrayList<Integer>();
        for (int i = 0; i < tiempo.length; i++) {
            if (tiempo[i] > 0) {
                devolver.add(i);
            }
        }
        return devolver;
    }


    public void setTemporizada (int t, int valor) {
        temporizada[t] = valor;
    }

    public boolean getTemporizada (int t) {
        return temporizada[t] == 1;
    }

    public void setTiempoDeSensibilizado (long tiempo, int transicion) {
        tiempoDeSensibilizado[transicion] = tiempo;
        return;
    }

    public long getTiempoDeSensibilizado (Transicion transicion) {
        return tiempoDeSensibilizado[transicion.getId()];
    }

    public boolean esTemporal (Transicion transicion) {
        boolean temp = false;
        if (tiempo[transicion.getId()] > 0) {
            temp = true;
        }
        return temp;
    }

    public long calcularTiempo(Transicion transicion) {
        long tiempodesensibilizado = tiempoDeSensibilizado[transicion.getId()];
        long tiempofinal = System.currentTimeMillis() - tiempodesensibilizado;
        long tiemporestante = tiempo[transicion.getId()] - tiempofinal;
        return tiemporestante;
    }

}