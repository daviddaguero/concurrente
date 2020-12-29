public class Main {

	public static void main(String[] args) {

		long startTime = System.nanoTime();
		
		int t0[] = {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		int t1[] = {0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		int t2[] = {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0};
		int t3[] = {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0};
		int t4[] = {0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0};
		int t5[] = {0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0};
		int t6[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0};
		int t7[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0};
		int t8[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0};
		int t9[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1};
		int t10[] = {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		int t11[] = {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0};
		int t12[] = {0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0};
		int t13[] = {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0};
		int t14[] = {0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0};
		int t15[] = {0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0};
		int t16[] = {0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0};

		Transicion transicion0 = new Transicion(t0, 0);
		Transicion transicion1 = new Transicion(t1, 1);
		Transicion transicion2 = new Transicion(t2, 2);
		Transicion transicion3 = new Transicion(t3, 3);
		Transicion transicion4 = new Transicion(t4, 4);
		Transicion transicion5 = new Transicion(t5, 5);
		Transicion transicion6 = new Transicion(t6, 6);
		Transicion transicion7 = new Transicion(t7, 7);
		Transicion transicion8 = new Transicion(t8, 8);
		Transicion transicion9 = new Transicion(t9, 9);
		Transicion transicion10 = new Transicion(t10, 10);
		Transicion transicion11 = new Transicion(t11, 11);
		Transicion transicion12 = new Transicion(t12, 12);
		Transicion transicion13 = new Transicion(t13, 13);
		Transicion transicion14 = new Transicion(t14, 14);
		Transicion transicion15 = new Transicion(t15, 15);
		Transicion transicion16 = new Transicion(t16, 16);

		Administrador administrador = new Administrador();

		Tiempo tiempo = new Tiempo();
		RedDePetri rdp = new RedDePetri(tiempo);
		Buffer buffer1 = new Buffer(rdp, 1);
		Buffer buffer2 = new Buffer(rdp, 2);
		Log log = new Log();
		Politica politica = new Politica(buffer1, buffer2, rdp);
		Monitor monitor = new Monitor(rdp, politica, log, tiempo, administrador);
		Regex regex = new Regex(log);


		Encendido encendido1 = new Encendido(monitor, transicion4, transicion5, transicion7, administrador);
		Encendido encendido2 = new Encendido(monitor, transicion12, transicion13, transicion15, administrador);
		Auxiliar auxiliar1 = new Auxiliar(monitor, transicion6, administrador);
		Auxiliar auxiliar2 = new Auxiliar(monitor, transicion14, administrador);
		Servicio servicio1 = new Servicio(monitor, transicion2, transicion3, administrador, tiempo);
		Servicio servicio2 = new Servicio(monitor, transicion10, transicion11, administrador, tiempo);
		Arribo arribo = new Arribo(monitor, transicion16, administrador, tiempo);
		aBuffer aBuffer1 = new aBuffer(transicion0, transicion1, monitor, administrador, buffer1);
		aBuffer aBuffer2 = new aBuffer(transicion8, transicion9, monitor, administrador, buffer2);
		
		Thread enc1 = new Thread(encendido1);
		enc1.setName("ENCENDIDO 1");
		Thread enc2 = new Thread(encendido2);
		enc2.setName("ENCENDIDO 2");
		Thread aux1 = new Thread(auxiliar1);
		aux1.setName("AUXILIAR 1");
		Thread aux2 = new Thread(auxiliar2);
		aux2.setName("AUXILIAR 2");
		Thread serv1 = new Thread(servicio1);
		serv1.setName("SERVICIO 1");
		Thread serv2 = new Thread(servicio2);
		serv2.setName("SERVICIO 2");
		Thread ar = new Thread(arribo);
		ar.setName("ARRIBO");
		Thread ab1 = new Thread(aBuffer1);
        Thread ab2 = new Thread(aBuffer2);
		
		enc1.start();
		enc2.start();
		aux1.start();
		aux2.start();
		serv1.start();
		serv2.start();
		ar.start();
		ab1.start();
		ab2.start();

		try {
			serv1.join();
			serv2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		enc1.interrupt();
		enc2.interrupt();
		aux1.interrupt();
		aux2.interrupt();
		ar.interrupt();
		ab1.interrupt();
		ab2.interrupt();


		long endTime = System.nanoTime() - startTime;
		int segundos = (int) (endTime / 1e9);
		int minutos = (int) (segundos / 60);
		segundos = segundos - minutos * 60;
		
		System.out.println("\n\n");
		log.cerrar();
		regex.chequeoInvariantes();
		
		System.out.println("\n-----FIN DEL MAIN-----");
		System.out.println("Duracion: " + minutos + " minutos, " + segundos + " segundos");
	}
}
