public class aBuffer implements Runnable {
    
    private Monitor monitor;
    private Transicion t1;
    private Transicion t2;
    //private String buffer;
    private Administrador administrador;
    private Buffer buffer;
    
    public aBuffer(Transicion t1, Transicion t2, Monitor monitor, Administrador admin, Buffer buffer) {
        this.t1 = t1;
        this.t2 = t2;
        this.monitor = monitor;
        administrador = admin;
        this.buffer = buffer;
    }
    
    @Override
    public void run() {
        while(administrador.getTareas()<administrador.getTareasCompletadas()) {
            monitor.disparar(t1);
            System.out.println("Se dirige al buffer " + buffer.getId());
            monitor.disparar(t2);
            System.out.println("Arribó una tarea al buffer " + buffer.getId());
            System.out.println("Elementos en el buffer " + buffer.getId() + ": " + buffer.getElementos());
        }
    }
}
