public class Servicio implements Runnable{
    private Monitor monitor;
    private Transicion t1;
    private Transicion t2;
    private Administrador administrador;
    private int tareas;


    public Servicio(Monitor monitor, Transicion t1,Transicion t2, Administrador admin) {
        this.monitor=monitor;
        this.t1 = t1;
        this.t2 = t2;
        administrador = admin;
        tareas = 0;
    }

    @Override
    public void run() {
        while(!administrador.getEnd()) {
            monitor.disparar(t1);
            System.out.println(Thread.currentThread().getName() + " esta resolviendo una tarea");
            monitor.disparar(t2);
            administrador.tareaCompletada();
            tareas++;
            System.out.println(Thread.currentThread().getName() + " resolvio " + tareas + " tareas.");
            System.out.println("Tareas resueltas: " + administrador.getTareas());
        }
    }
}