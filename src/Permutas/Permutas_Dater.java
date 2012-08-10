package Permutas;

import java.io.*;
import org.htmlparser.beans.FilterBean;
import java.util.Hashtable;

public class Permutas_Dater {

    public static Hashtable<String, String> hasht1 = new Hashtable<String, String>();
    public static String onMac = "/Users/fsimon/Desktop/ADAIR/CubaData/";
    public static String onPC = "C:/Documents and Settings/fsimon0/My Documents/CubaData Original/";

    public static void main(String[] args) throws IOException {
        File[] files = new File(onMac).listFiles();
        iteratorOverAllFiles(files);
        writeTheHashtableToTextFile();
        System.out.println(hasht1.toString());
    }

    public static void iteratorOverAllFiles(File[] files) {
        for (File file : files) {
            System.out.println(file);
            if (file.isDirectory() && !file.getName().equals("casas")) {
                //System.out.println(file.getName());
                iteratorOverAllFiles(file.listFiles());
            } else {
                if ((file.getName().equals("buscador.html")) || (file.getName().equals("index.html"))) {
                    codeDateExtractEntireBuscadorhtml(file.getAbsolutePath());
                    //System.out.println("something sent to codeExtractEntire");
                }
            }
        }
    }

    public static void writeTheHashtableToTextFile() throws IOException {

        File theAllTXTFile = new File(onMac + "Permutas_Date_Dictionary.csv");
        FileWriter allFileWriter = null;
        try {
            allFileWriter = new FileWriter(theAllTXTFile);
        } catch (IOException e1) {
        }
        String toWrite = hasht1.toString();
        toWrite = toWrite.replace("{", "");
        toWrite = toWrite.replace("}", "");
        toWrite = toWrite.replace("lunes,", "lunes");
        toWrite = toWrite.replace("martes,", "martes");
        toWrite = toWrite.replace("miércoles,", "miercoles");
        toWrite = toWrite.replace("jueves,", "jueves");
        toWrite = toWrite.replace("viernes,", "viernes");
        toWrite = toWrite.replace("sábado,", "sabado");
        toWrite = toWrite.replace("domingo,", "domingo");
        toWrite = toWrite.replace(",", "\n");
        allFileWriter.write(toWrite);
        try {
            allFileWriter.close();
        } catch (IOException e) {
        }

    }

    public static void codeDateExtractEntireBuscadorhtml(String filename) {

        String fn = filename;
        FilterBean codeFilter = new FilterBean();
        codeFilter.setURL(fn);
        String code = codeFilter.getText();
        String[] codearray = code.split("\n");

        for (int j = 0; j < codearray.length - 1; j++) {
            String cod1 = "";
            if (codearray[j].contains("Código:")) {
                //System.out.println("in if statement"+codearray[j]);
                cod1 = codearray[j].replace("Código: ", "");
                String[] splitted = cod1.split("\\|");
                //System.out.println("splitted0  "+splitted[0]);
                String key = splitted[0].trim();
                //System.out.println("splitted1  "+splitted[1]);
                String object = splitted[1].replace("Publicado: ", "").trim();
                //System.out.println("putting: "+key+" with "+object+" for filename "+fn);
                hasht1.put(key, object);
                //System.out.println(hasht1.toString());
            }
        }
    }
}