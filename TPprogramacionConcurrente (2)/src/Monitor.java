import java.util.concurrent.Semaphore;

public class Monitor {
	private Semaphore semaforo;
	private Semaphore[] semaforos;
	private RedDePetri rdp;
	private Politica politica;
	private Log log;

	private Tiempo tiempo;//private int tareas;
	//private int tareas1;
	//private int tareas2;
	//private boolean end = true;
    private Administrador administrador;

	public Monitor(RedDePetri rdp, Politica politica, Log log, Tiempo tiempo, Administrador admin) {
		semaforo = new Semaphore(1, true);
		semaforos = new Semaphore[17];
		for (int i = 0; i < semaforos.length; i++) {
			semaforos[i] = new Semaphore(0, true);
		}
		this.rdp = rdp;
		this.politica = politica;
		this.log = log;
		//tareas = 0;
		//tareas1 = 0;
		//tareas2 = +0;
        this.tiempo = tiempo;
        administrador = admin;
	}

	public void disparar(Transicion t) {
		try {
			semaforo.acquire();
			while (!administrador.getEnd()) {
				if (rdp.evaluarDisparo(t.getTransicion())) {
					rdp.disparar(t.getTransicion());
					log.escribir(t.getId() + "  ");
					//INVARIANTES DE PLAZA
                    int p1 = rdp.getMP0() + rdp.getMP1() + rdp.getMP9() + rdp.getMP17();
                    int p2 = rdp.getMP3() + rdp.getMP4();
                    int p3 = rdp.getMP5() + rdp.getMP7() + rdp.getMP8();
                    int p4 = rdp.getMP11() + rdp.getMP12();
                    int p5 = rdp.getMP13() + rdp.getMP14() + rdp.getMP16();

                    assert p1 == 1 : String.format("Invariante 1 no cumplido");
                    assert p2 == 1 : String.format("Invariante 2 no cumplido");
                    assert p3 == 1 : String.format("Invariante 3 no cumplido");
                    assert p4 == 1 : String.format("Invariante 4 no cumplido");
                    assert p5 == 1 : String.format("Invariante 5 no cumplido");

                    int[] sensibilizadas = rdp.getTransicionesSensibilizadas();
                    //int decision = politica.resolverConflicto(sensibilizadas);
                    int decision = politica.decidir(sensibilizadas); //Una vex que tengo la decisión, despierto a la transici+on elegida (en el vector de semáforos)
                    //int decision = politica.resolverConflictoRandom(sensibilizadas);
                    semaforos[decision].release();
					break;
				} else {
					semaforo.release();
					dormir(t.getTransicion());
					try {
                        semaforo.acquire();
                    } catch (Exception exit) {
					    break;
                    }

                }
			}
		} catch (InterruptedException e) {
		    return;
		} finally {
            if (administrador.getEnd()) {
                for (int i = 0; i <semaforos.length ; i++) {
                    semaforos[i].release();
                }
            }
			semaforo.release();
		}
		return;
	}

	private void dormir(int[] t) {
		for (int i = 0; i < t.length; i++) {
			if (t[i] == 1) {
                    if (tiempo.esTemporal(t)) {
                        long time = tiempo.calcularTiempo(t);
                        if (time>0) {
                            try {
                                Thread.sleep(time);
                            } catch (InterruptedException exit) {
                                break;
                            }
                        }
                    }
                    try {
                        semaforos[i].acquire();
                    } catch (InterruptedException exit) {
                        break;
                    }
				break;
			}
		}
	}

   /* public int getTareas() {
	    return tareas;
    }

    public void setEnd() {
	    end = false;
    }
    */
}
