public class Arribo implements Runnable {
	private Monitor monitor;
	private Transicion t;
	private Administrador administrador;
	private Tiempo tiempo;
        
	public Arribo(Monitor monitor, Transicion t, Administrador admin, Tiempo tiempo) {
		this.monitor=monitor;
		this.t = t;
		administrador = admin;
		this.tiempo = tiempo;
	}

	@Override
	public void run() {
		while(administrador.getTareas()<administrador.getTareasCompletadas()) {
				monitor.disparar(t);
			    tiempo.setTiempoActual(System.currentTimeMillis(), t.getTransicion());
			    System.out.println("Arribo una tarea");
			}
		}
	}