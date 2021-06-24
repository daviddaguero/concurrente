import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Administrador {
    private boolean end;
    private int tareas;
    private ReentrantReadWriteLock locki;
    private Boolean nucleoEncendido1 = false;
    private Boolean nucleoEncendido2 = false;

    private static final int TAREAS_COMPLETADAS = 1000;

    public Administrador() {
        end = false;
        tareas = 0;
        locki = new ReentrantReadWriteLock();
    }

    public int getTareas() {
        locki.readLock().lock();
        int ret = tareas;
        locki.readLock().unlock();
        return ret;
    }

    public void tareaCompletada() {
        locki.writeLock().lock();
        if (tareas == TAREAS_COMPLETADAS) {
                setEnd();
        } else {
            tareas++;
        }
        locki.writeLock().unlock();
    }

    public void setEnd() {
        end = true;
    }

    public int getTareasCompletadas() {
        return TAREAS_COMPLETADAS;
    }

    public boolean getEnd() {
        return end;
    }

    public boolean nucleoEncendido1() {
        return nucleoEncendido1;
    }

    public void setNucleoEncendido1(boolean estado) {
        nucleoEncendido1 = estado;
        return;
    }

    public boolean nucleoEncendido2() {
        return nucleoEncendido2;
    }

    public void setNucleoEncendido2(boolean estado) {
        nucleoEncendido2 = estado;
        return;
    }
}