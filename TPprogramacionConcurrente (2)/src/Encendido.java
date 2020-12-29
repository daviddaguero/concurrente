public class Encendido implements Runnable{
	private Monitor monitor;
	private Transicion t1;
	private Transicion t2;
	private Transicion t3;
	private Administrador administrador;

	public Encendido(Monitor monitor, Transicion t1, Transicion t2, Transicion t3, Administrador admin) {
		this.monitor=monitor;
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
		administrador = admin;
	}

	@Override
	public void run() {
		while(administrador.getTareas()<administrador.getTareasCompletadas()) {
			monitor.disparar(t1);
			System.out.println(Thread.currentThread().getName() + " esta encendiendo el nucleo");
			monitor.disparar(t2);
			System.out.println(Thread.currentThread().getName() + " encendio el nucleo");
			monitor.disparar(t3);
			System.out.println(Thread.currentThread().getName() + " apago el nucleo");
		}
	}

}

