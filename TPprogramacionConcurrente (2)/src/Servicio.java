public class Servicio implements Runnable{
	private Monitor monitor;
	private Transicion t1;
	private Transicion t2;
	//private static final int TAREAS_COMPLETADAS = 1000; // Cada hilo hace 500 tareas.
	private Administrador administrador;
	private int tareas;
	private Tiempo tiempo;

	
	public Servicio(Monitor monitor, Transicion t1,Transicion t2, Administrador admin, Tiempo tiempo) {
		this.monitor=monitor;
		this.t1 = t1;
		this.t2 = t2;
		administrador = admin;
		tareas = 0;
		this.tiempo = tiempo;
	}

	@Override
	public void run() {
		while(administrador.getTareas()<administrador.getTareasCompletadas()) {
				monitor.disparar(t1);
			    tiempo.setTiempoActual(System.currentTimeMillis(), t1.getTransicion());
			    System.out.println(Thread.currentThread().getName() + " esta resolviendo una tarea");
				monitor.disparar(t2);
				administrador.tareaCompetada();
				tareas++;
				System.out.println(Thread.currentThread().getName() + " resolvio " + tareas + " tareas.");
			    System.out.println("Tareas resueltas: " + administrador.getTareas());
			}
		}	
	}

