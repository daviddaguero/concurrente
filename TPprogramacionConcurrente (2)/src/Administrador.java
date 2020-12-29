public class Administrador {
    private boolean end;
    private int tareas;

    private static final int TAREAS_COMPLETADAS = 1000;

    public Administrador() {
        end = false;
        tareas = 0;
    }

    synchronized public int getTareas() {
        return tareas;
    }

    synchronized public void tareaCompetada() {
        if (tareas == TAREAS_COMPLETADAS) {
            setEnd();
        } else {
            tareas++;
        }
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
}
