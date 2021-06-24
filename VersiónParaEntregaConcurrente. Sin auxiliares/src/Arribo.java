public class Arribo implements Runnable {
    private Monitor monitor;
    private Transicion t;
    private Administrador administrador;


    public Arribo(Monitor monitor, Transicion t, Administrador admin) {
        this.monitor=monitor;
        this.t = t;
        administrador = admin;
    }

    @Override
    public void run() {
        while(!administrador.getEnd()) {
            monitor.disparar(t);
            System.out.println("Arribo una tarea");
        }
    }
}