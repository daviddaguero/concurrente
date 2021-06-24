public class Encendido implements Runnable{
    private Monitor monitor;
    private Transicion t1;
    private Transicion t2;
    private Transicion t3;
    private Administrador administrador;
    private int id;

    public Encendido(Monitor monitor, Transicion t1, Transicion t2, Transicion t3, Administrador admin, int id) {
        this.monitor=monitor;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        administrador = admin;
        this.id = id;
    }

    @Override
    public void run() {
        while(!administrador.getEnd()) {
            monitor.disparar(t1);
            System.out.println(Thread.currentThread().getName() + " esta encendiendo el nucleo");
            monitor.disparar(t2);
            System.out.println(Thread.currentThread().getName() + " encendio el nucleo");
            /*if (id == 1) {
                administrador.setNucleoEncendido1(true);
            } else {
                administrador.setNucleoEncendido2(true);
            }
             */
            monitor.disparar(t3);
            System.out.println(Thread.currentThread().getName() + " apago el nucleo");
            /*if (id == 1) {
                administrador.setNucleoEncendido1(false);
            } else {
                administrador.setNucleoEncendido2(false);
            }
             */
        }
        /*if (administrador.nucleoEncendido1() && id == 1){
            monitor.apagarNucleo(t3);
            //administrador.tareaCompletada();
        }
        if (administrador.nucleoEncendido2() && id == 2) {
            monitor.apagarNucleo(t3);
            //administrador.tareaCompletada();
        }
         */
    }

}
