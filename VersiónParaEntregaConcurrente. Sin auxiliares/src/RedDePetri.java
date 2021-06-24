import java.util.ArrayList;
import java.util.List;

public class RedDePetri {
    private int[][] matrizIncidencia = { { -1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, -1, 0 },
            { 1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 0, 0, 1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, -1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 0, 0, 0, 0, 0, 1, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 1, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 0, 1, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, -1, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 1, 0, 0 },
            { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, -1, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, -1, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, -1 }, };
    private int[][] backwardsIncidenceMatrix = { { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
            { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, };
    private int[] marcado = { 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0 };
    private int[][] h = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
    private int[] e = new int[17];
    private int[] q = new int[18];
    private int[] b = new int[17];
    private int[] ex = new int[17];
    private Tiempo tiempo;


    public RedDePetri(Tiempo tiempo) {
        calcularE();
        calcularQ();
        calcularB();
        ex = calcularConjuncion(e, b);
        this.tiempo = tiempo;
    }

    public boolean evaluarDisparo(Transicion t) {
        boolean aviso = false;
        int mult[] = new int[marcado.length];
        int suma[] = new int[marcado.length];
        int conjuncion[] = calcularConjuncion(t.getTransicion(), ex);
        for (int i = 0; i < marcado.length; i++) {
            for (int j = 0; j < 1; j++) {
                for (int k = 0; k < 17; k++) {
                    mult[i] += matrizIncidencia[i][k] * conjuncion[k];
                }
            }
        }
        for (int i = 0; i < marcado.length; i++) {
            if (mult[i] != 0) {
                aviso = true;
                break;
            }
        }
        if (!aviso) {
            return false; // return false
        }
        for (int i = 0; i < marcado.length; i++) {
            suma[i] = marcado[i] + mult[i];
            if (suma[i] < 0) {
                return false; // return false
            }
        }
        // Si es temporal
        if (tiempo.esTemporal(t)) {
            long time = tiempo.calcularTiempo(t);
            if (time > 0) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    public int disparar(Transicion t) {
        boolean aviso = false;
        int mult[] = new int[marcado.length];
        int suma[] = new int[marcado.length];
        int conjuncion[] = calcularConjuncion(t.getTransicion(), ex);
        for (int i = 0; i < marcado.length; i++) {
            for (int j = 0; j < 1; j++) {
                for (int k = 0; k < 17; k++) {
                    mult[i] += matrizIncidencia[i][k] * conjuncion[k];
                }
            }
        }
        for (int i = 0; i < marcado.length; i++) {
            if (mult[i] != 0) {
                aviso = true;
                break;
            }
        }
        if (!aviso) {
            return 0;
        }
        for (int i = 0; i < marcado.length; i++) {
            suma[i] = marcado[i] + mult[i];
            if (suma[i] < 0) {
                return 0;
            }
        }
        marcado = suma;
        calcularE();
        calcularQ();
        calcularB();
        ex = calcularConjuncion(e, b);
        SetearTiempos(t);
        return 1;
    }

    private void SetearTiempos (Transicion t) {
        //aviso que ya disparo, entonces ya se puede setear el tiempo de vuelta
        if(tiempo.esTemporal(t)) {
            tiempo.setTemporizada(t.getId(), 0);
        }

        //Obtengo las transiciones sensibilizadas por el disparo y las temporales
        int [] sensibilizadas = getTransicionesSensibilizadas();
        List<Integer> sensibilizadasTraducidas = traductor(sensibilizadas);
        List<Integer> temporales = tiempo.getTemporales();
        //y me fijo si alguna temporal fue sensibilizada por el disparo para marcar el tiempo
        for (int i = 0; i < temporales.size(); i++) {
            int temp = temporales.get(i);
            for (int j = 0; j < sensibilizadasTraducidas.size(); j++) {
                if(sensibilizadasTraducidas.get(j) == temp) {
                    if(!tiempo.getTemporizada(temp)){
                        tiempo.setTiempoDeSensibilizado(System.currentTimeMillis(), temp);
                        tiempo.setTemporizada(temp, 1);
                    }
                }
            }
        }
    }



    private void calcularE() {
        for (int i = 0; i < 17; i++) {
            e[i] = 1;
            for (int j = 0; j < marcado.length; j++) {
                if (backwardsIncidenceMatrix[j][i] == 1) {
                    if (marcado[j] == 0) {
                        e[i] = 0;
                        break;
                    }
                }
            }
        }
    }

    private void calcularQ() {
        for (int i = 0; i < marcado.length; i++) {
            q[i] = 1;
            if (marcado[i] != 0) {
                q[i] = 0;
            }
        }
    }

    private void calcularB() {
        for (int i = 0; i < 17; i++) {
            b[i] = 1;
            for (int j = 0; j < marcado.length; j++) {
                if (h[j][i] == 1) {
                    if (marcado[j] > 0) {
                        b[i] = 0;
                        break;
                    }
                }
            }
        }
    }

    public int[] calcularConjuncion(int[] t1, int[] t2) {
        int[] t3 = new int[17];
        for (int i = 0; i < 17; i++) {
            if (t1[i] == 1 && t2[i] == 1) {
                t3[i] = 1;
            } else {
                t3[i] = 0;
            }
        }
        return t3;
    }

    public int getElementosBuffer1() {
        return marcado[10];
    }

    public int getElementosBuffer2() {
        return marcado[2];
    }

    public int getMP0() {
        return marcado[0];
    }

    public int getMP1() {
        return marcado[1];
    }

    public int getMP9() {
        return marcado[17];
    }

    public int getMP3() {
        return marcado[11];
    }

    public int getMP4() {
        return marcado[12];
    }

    public int getMP5() {
        return marcado[13];
    }

    public int getMP7() {
        return marcado[15];
    }

    public int getMP8() {
        return marcado[16];
    }

    public int getMP11() {
        return marcado[3];
    }

    public int getMP12() {
        return marcado[4];
    }

    public int getMP13() {
        return marcado[5];
    }

    public int getMP14() {
        return marcado[6];
    }

    public int getMP16() {
        return marcado[8];
    }

    public int getMP17() {
        return marcado[9];
    }

    public int[] getTransicionesSensibilizadas() {
        int contador = 0;
        for (int i = 0; i < ex.length; i++) {
            if (ex[i] > 0) {
                contador++;
            }
        }
        int aux = 0;
        int[] tSensibilizadas = new int[contador];
        for (int i = 0; i < ex.length; i++) {
            if (ex[i] > 0) {
                tSensibilizadas[aux] = i;
                aux++;
            }
        }
        return tSensibilizadas;
    }

    public List<Integer> traductor(int []sensibilizadas) {
        int [] traduc = {0, 1, 10, 11, 12, 13, 14, 15, 16, 2, 3, 4, 5, 6, 7, 8, 9};
        List <Integer> devolver = new ArrayList<Integer>();
        for (int i = 0; i < sensibilizadas.length; i++) {
            devolver.add(traduc[sensibilizadas[i]]);
        }
        return devolver;
    }

    public int[] getEx() {
        return ex;
    }


}