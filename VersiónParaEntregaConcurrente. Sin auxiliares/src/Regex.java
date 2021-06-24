
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;

public class Regex {
    private Log log;
    private StringBuffer invariantes = new StringBuffer();


    // No separar en grupos los invariantes

    private String expresionRegular = "((16\\s)(.*?)(((\\s0)(.*?)(\\s1\\s)(.*?)(((\\s4)(.*?)(\\s5)(.*?)(\\s2)(.*?)(\\s3)(.*?)(7\\s))|((\\s2)(.*?)(\\s3)(.*?)(\\s6))|((\\s2)(.*?)(\\s6)(.*?)(\\s3))|((\\s6)(.*?)(\\s2)(.*?)(\\s3))))|((8\\s)(.*?)(9\\s)(.*?)(((12\\s)(.*?)(13\\s)(.*?)(10\\s)(.*?)(11\\s)(.*?)(15\\s))|((10\\s)(.*?)(11\\s)(.*?)(14\\s))|((10\\s)(.*?)(14\\s)(.*?)(11\\s))|((14\\s)(.*?)(10\\s)(.*?)(11\\s))))))";


    //I1:   16 8 9 12 13 10 11 15
    //I2.1: 16 8 9 10 11 14
    //I3:   16 0 1 4 5 2 3 7
    //I4.1: 16 0 1 2 3 6
    //I2.2: 16 8 9 10 14 11
    //I2.3: 16 8 9 14 10 11
    //I4.2: 16 0 1 2 6 3
    //I4.3: 16 0 1 6 2 3

    List<Integer> startIndexs = new ArrayList<>();
    List<Integer> endIndexs = new ArrayList<>();
    List<InvTransicion> listaInvariantes = new ArrayList<>();


    private int[] contadores = {0, 0, 0, 0, 0, 0, 0, 0};
    private int contadorTodo = 0;

    public Regex(Log log, List<InvTransicion> listaInvariantes) {
        this.log = log;
        this.listaInvariantes = listaInvariantes;
    }

    public void chequeoInvariantes() {
        invariantes = log.sacarInfo();
        //invariantes.append("16  0  1  4  5  7  2  3  16    0  1    2   3  6 10  11  8  9  12  13  4  5   15  7  1  8  9  12  13  4  5   10   11  15    10  11   8  9    12  13  6  15     1  8  9  6  12   13   10  11    10    11     15  1  8  9   6   12  13 ");
        Pattern patternTodo = Pattern.compile(expresionRegular);


        int antes = 0;
        int despues = 0;
        boolean seguir = true;

        while (seguir) {

            antes = contadorTodo;

            Matcher matcherExpresionRegular = patternTodo.matcher(invariantes);
            System.out.println("Estoy en chequeoInvariantes");

            while (matcherExpresionRegular.find()) { //mientras que la expresion regular haga match
                System.out.println("Encontré");
                int invarianteMatcheado = invarianteMatch(matcherExpresionRegular);
                contadores[invarianteMatcheado] = contadores[invarianteMatcheado] + 1;
                LlenarListaInvariantes(matcherExpresionRegular, listaInvariantes.get(invarianteMatcheado));
                contadorTodo++;

            }
            QuitarListaInvariantes();


            despues = contadorTodo;

            if (antes == despues) {

                seguir = false;
            }

        }

        //imprimir contadores
        for (int i = 0; i < contadores.length; i++) {
            System.out.println("invariante " + i + ": " + contadores[i]);
        }

        log.escribirLog(invariantes);
    }

    private int invarianteMatch (Matcher matcherExpresionRegular) {//me fijo que invariante matcheo y lo devuelvo
        int inv = 0;
        for (int i = 0; i < listaInvariantes.size(); i++) {
            int contador = 0;

            int [] grupos = listaInvariantes.get(i).getGrupos();
            for (int j = 0; j < grupos.length; j++) {
                if (matcherExpresionRegular.group(grupos[j]) != null) {

                    contador++;
                }
            }
            if (contador==grupos.length) {
                inv = i;
                break;
            }
        }

        //System.out.println("invariante encontrado es : " + inv);
        return inv;
    }

    private void QuitarListaInvariantes () { //elimina los invariantes ya encontrados con los indices de la lista
        for (int j = startIndexs.size(); j > 0; j--) {
            invariantes.delete(startIndexs.get(j - 1), endIndexs.get(j - 1));
        }
        startIndexs.clear();
        endIndexs.clear();
    }

    private void LlenarListaInvariantes (Matcher matcherExpresionRegular, InvTransicion invariante) { //agrega los indices de los invariantes encontrados a la lista
        int[] grupos = invariante.getGrupos();
        for (int i = 0; i < grupos.length; i++) {
            startIndexs.add(matcherExpresionRegular.start(grupos[i]));
            endIndexs.add(matcherExpresionRegular.end(grupos[i]));
            //System.out.println("grupos para sacar " + grupos[i]);
        }
    }
}