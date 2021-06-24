import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.filechooser.FileSystemView;

public class Log {

    private File file;
    private FileWriter fichero;
    private PrintWriter pw;
    private BufferedReader br;
    private StringBuffer info;

    public Log() {
        info = new StringBuffer();
        fichero = null;
        pw = null;
        try {
            file = new File(
                    FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath().replace('\\', '/')
                            + "/log.txt");
            fichero = new FileWriter(file);
            pw = new PrintWriter(fichero);
            br = new BufferedReader(
                    new FileReader(FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath().replace('\\', '/')
                            + "/log.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void escribir(String texto) {
        pw.print(texto);
    }

    public void escribirLog(StringBuffer texto) {
        fichero = null;
        pw=null;
        File file1 = new File(
                FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath().replace('\\', '/')
                        + "/log1.txt");
        try {
            fichero = new FileWriter(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        abrir();
        pw.print(texto);
        cerrar();
        file.delete();
        file1.renameTo(file);
    }

    public String leer() {
        try {
            return br.readLine();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }

    public void abrir() {
        try {
            pw = new PrintWriter(fichero);
            br = new BufferedReader(
                    new FileReader(FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath().replace('\\', '/')
                            + "/log.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cerrar() {
        try {
            pw.close();
            br.close();
            fichero.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public StringBuffer sacarInfo() {
        abrir();
        String linea;
        while ((linea = leer()) != null) {
            //System.out.println(linea);
            info.append(linea);
            //System.out.println(info);
        }
        cerrar();
        return info;
        //System.out.println("info " + info);
    }


    public void eliminar (int contador[]) {
        fichero = null;
        pw=null;
        File file1 = new File(
                FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath().replace('\\', '/')
                        + "/log1.txt");
        try {
            fichero = new FileWriter(file1);
        } catch (IOException e) {

            e.printStackTrace();
        }
        abrir();
        int contador0 = 0;
        int contador1 = 0;
        int contador2 = 0;
        int contador3 = 0;
        int contador4 = 0;
        int contador5 = 0;
        int contador7 = 0;
        int contador8 = 0;
        int contador9 = 0;
        int contador10 = 0;
        int contador11 = 0;
        int contador12 = 0;
        int contador13 = 0;
        int contador15 = 0;
        int contador16 = 0;
        String linea;
        escribir("\n");
        while ((linea = leer()) != null) {
            if((linea.equals("0")) && (contador0 <= contador[0])) {contador0++;}
            else if((linea.equals("1")) && (contador1 <= contador[1])) {contador1++;}
            else if((linea.equals("2")) && (contador2 <= contador[2])) {contador2++;}
            else if((linea.equals("3")) && (contador3 <= contador[3])) {contador3++;}
            else if((linea.equals("4")) && (contador4 <= contador[4])) {contador4++;}
            else if((linea.equals("5")) && (contador5 <= contador[5])) {contador5++;}
            else if((linea.equals("7")) && (contador7 <= contador[6])) {contador7++;}
            else if((linea.equals("8")) && (contador8 <= contador[7])) {contador8++;}
            else if((linea.equals("9")) && (contador9 <= contador[8])) {contador9++;}
            else if((linea.equals("10")) && (contador10 <= contador[9])) {contador10++;}
            else if((linea.equals("11")) && (contador11 <= contador[10])) {contador11++;}
            else if((linea.equals("12")) && (contador12 <= contador[11])) {contador12++;}
            else if((linea.equals("13")) && (contador13 <= contador[12])) {contador13++;}
            else if((linea.equals("15")) && (contador15 <= contador[13])) {contador15++;}
            else if((linea.equals("16")) && (contador16 <= contador[14])) {contador16++;}
            else {
                escribir(linea + "\n");
            }
        }
        cerrar();
        file.delete();
        file1.renameTo(file);
    }
}
