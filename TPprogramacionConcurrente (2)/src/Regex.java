/*public class Regex {
	private Log log;
	private String i1[] = { "16", "0", "1" };
	private String i2[] = { "2", "3" };
	private String i3[] = { "4", "5", "7" };
	private String i4[] = { "16", "8", "9" };
	private String i5[] = { "10", "11" };
	private String i6[] = { "12", "13", "15" };
	private int contador[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
						//    0, 1, 2, 3, 4, 5, 7, 8, 9,10,11,12,13,15,16		numero de transiciones
	
						//	  0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14		posiciones dentro del vector

	public Regex(Log log) {
		this.log = log;
	}

	public void chequeoInvariante1() {
		log.abrir();
		int contador = 0;
		int k = 0;
		String linea;
		while ((linea = log.leer()) != null) {
			if (linea.equals(i1[k])) {
				k++;
				if (k > i1.length - 1) {
					k = 0;
					contador++;
				}
			}
		}
		System.out.println("invariante 1 = " + contador);
		this.contador[14] = this.contador[14] + contador;
		this.contador[0] = this.contador[0] + contador;
		this.contador[1] = this.contador[1] + contador;
		log.cerrar();
	}
	
	public void chequeoInvariante2() {
		log.abrir();
		int contador = 0;
		int k = 0;
		String linea;
		while ((linea = log.leer()) != null) {
			if (linea.equals(i2[k])) {
				k++;
				if (k > i2.length - 1) {
					k = 0;
					contador++;
				}
			}
		}
		System.out.println("invariante 2 = " + contador);
		this.contador[2] = this.contador[2] + contador;
		this.contador[3] = this.contador[3] + contador;
		log.cerrar();
	}
	
	public void chequeoInvariante3() {
		log.abrir();
		int contador = 0;
		int k = 0;
		String linea;
		while ((linea = log.leer()) != null) {
			if (linea.equals(i3[k])) {
				k++;
				if (k > i3.length - 1) {
					k = 0;
					contador++;
				}
			}
		}
		System.out.println("invariante 3 = " + contador);
		this.contador[4] = this.contador[4] + contador;
		this.contador[5] = this.contador[5] + contador;
		this.contador[6] = this.contador[6] + contador;
		log.cerrar();
	}
	
	public void chequeoInvariante4() {
		log.abrir();
		int contador = 0;
		int k = 0;
		String linea;
		while ((linea = log.leer()) != null) {
			if (linea.equals(i4[k])) {
				k++;
				if (k > i4.length - 1) {
					k = 0;
					contador++;
				}
			}
		}
		System.out.println("invariante 4 = " + contador);
		this.contador[14] = this.contador[14] + contador;
		this.contador[7] = this.contador[7] + contador;
		this.contador[8] = this.contador[8] + contador;
		log.cerrar();
	}
	
	public void chequeoInvariante5() {
		log.abrir();
		int contador = 0;
		int k = 0;
		String linea;
		while ((linea = log.leer()) != null) {
			if (linea.equals(i5[k])) {
				k++;
				if (k > i5.length - 1) {
					k = 0;
					contador++;
				}
			}
		}
		System.out.println("invariante 5 = " + contador);
		this.contador[9] = this.contador[9] + contador;
		this.contador[10] = this.contador[10] + contador;
		log.cerrar();
	}
	
	public void chequeoInvariante6() {
		log.abrir();
		int contador = 0;
		int k = 0;
		String linea;
		while ((linea = log.leer()) != null) {
			if (linea.equals(i6[k])) {
				k++;
				if (k > i6.length - 1) {
					k = 0;
					contador++;
				}
			}
		}
		System.out.println("invariante 6 = " + contador);
		this.contador[11] = this.contador[11] + contador;
		this.contador[12] = this.contador[12] + contador;
		this.contador[13] = this.contador[13] + contador;
		log.cerrar();
	}
	
	public void chequeoInvariantes() {
		chequeoInvariante1();
		chequeoInvariante2();
		chequeoInvariante3();
		chequeoInvariante4();
		chequeoInvariante5();
		chequeoInvariante6();
		log.eliminar(contador);
	}
}
*/
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;

public class Regex {
	private Log log;
	private StringBuffer texto = new StringBuffer();

	private String invarianteTodo = "((10)(.*?)(11))|((12)(.*?)(13)(.*?)(15))|((\\s2)(.*?)(\\s3))|((16)(.*?)((\\s0)(.*?)(\\s1\\s)|(8)(.*?)(9)))|((\\s4)(.*?)(\\s5)(.*?)(7))";

	List<Integer> startIndexs = new ArrayList<>();
	List<Integer> endIndexs = new ArrayList<>();

	public Regex(Log log) {
		this.log = log;
	}

	public void chequeoInvariantes() {
		texto = log.sacarInfo();
		// texto.append("16 0 1 14 2 6 3 4 14 5 14 12 13 15 16 0 1 14 2 6 3 4 14 5 6 6 7
		// 14 16 6 8 9 6 10 14 11 14 12 13 15 16 0 1 14 2 6 3 4 14 5 6 6 7 14 16 6 8 9 6
		// 10 14 11 14 12 13 15 16 0 1 14 2 6 6 7 14 16 6 8 9 6 10 14 11 6 3 4 14 5 6 6
		// 7 14 16 6 8 9 6 10 14 11 14 12 13 15 ");

		Pattern patternTodo = Pattern.compile(invarianteTodo);

		int contadorinv1 = 0;
		int contadorinv2 = 0;
		int contadorinv3 = 0;
		int contadorinv4 = 0;
		int contadorinv5 = 0;
		int contadorinv6 = 0;
		int contadorTodo = 0;
		int antes = 0;
		int despues = 0;
		boolean seguir = true;

		while (seguir) {

			antes = contadorTodo;

			Matcher matcherTodo = patternTodo.matcher(texto);

			while (matcherTodo.find()) {
				if (matcherTodo.group(15) != null) {
					startIndexs.add(matcherTodo.start(16));
					endIndexs.add(matcherTodo.end(16));
					if (matcherTodo.group(19) != null) {
						contadorinv1++;
						startIndexs.add(matcherTodo.start(19));
						endIndexs.add(matcherTodo.end(19));
						startIndexs.add(matcherTodo.start(21));
						endIndexs.add(matcherTodo.end(21));
					} else {
						contadorinv4++;
						startIndexs.add(matcherTodo.start(22));
						endIndexs.add(matcherTodo.end(22));
						startIndexs.add(matcherTodo.start(24));
						endIndexs.add(matcherTodo.end(24));
					}
				} else if (matcherTodo.group(11) != null) {
					contadorinv2++;
					startIndexs.add(matcherTodo.start(12));
					endIndexs.add(matcherTodo.end(12));
					startIndexs.add(matcherTodo.start(14));
					endIndexs.add(matcherTodo.end(14));
				} else if (matcherTodo.group(25) != null) {
					contadorinv3++;
					startIndexs.add(matcherTodo.start(26));
					endIndexs.add(matcherTodo.end(26));
					startIndexs.add(matcherTodo.start(28));
					endIndexs.add(matcherTodo.end(28));
					startIndexs.add(matcherTodo.start(30));
					endIndexs.add(matcherTodo.end(30));
				} else if (matcherTodo.group(1) != null) {
					contadorinv5++;
					startIndexs.add(matcherTodo.start(2));
					endIndexs.add(matcherTodo.end(2));
					startIndexs.add(matcherTodo.start(4));
					endIndexs.add(matcherTodo.end(4));
				} else if (matcherTodo.group(5) != null) {
					contadorinv6++;
					startIndexs.add(matcherTodo.start(6));
					endIndexs.add(matcherTodo.end(6));
					startIndexs.add(matcherTodo.start(8));
					endIndexs.add(matcherTodo.end(8));
					startIndexs.add(matcherTodo.start(10));
					endIndexs.add(matcherTodo.end(10));
				}
				contadorTodo++;
			}
			//System.out.println("invariantes " + contadorTodo);

			for (int j = startIndexs.size(); j > 0; j--) {
				texto.delete(startIndexs.get(j - 1), endIndexs.get(j - 1));
			}
			startIndexs.clear();
			endIndexs.clear();
			//System.out.println(texto);
			despues = contadorTodo;

			if (antes == despues) {
				seguir = false;
			}

		}

		System.out.println("invariante 1 " + contadorinv1);
		System.out.println("invariante 2 " + contadorinv2);
		System.out.println("invariante 3 " + contadorinv3);
		System.out.println("invariante 4 " + contadorinv4);
		System.out.println("invariante 5 " + contadorinv5);
		System.out.println("invariante 6 " + contadorinv6);

		log.escribirLog(texto);
	}

}
