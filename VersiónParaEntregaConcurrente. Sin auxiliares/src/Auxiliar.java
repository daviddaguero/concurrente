public class Auxiliar implements Runnable{
    private Monitor monitor;
    private Transicion t1;
    private Administrador administrador;

    public Auxiliar(Monitor monitor, Transicion t1, Administrador admin) {
        this.monitor=monitor;
        this.t1 = t1;
        administrador = admin;
    }

    @Override
    public void run() {
        while(!administrador.getEnd()) {
            monitor.disparar(t1);
            System.out.println(Thread.currentThread().getName() + " realizo un disparo");
        }
    }

}