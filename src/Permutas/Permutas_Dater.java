package Permutas;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.htmlparser.beans.FilterBean;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static void iteratorOverAllFiles(File[] files) throws MalformedURLException, IOException {
        for (File file : files) {
            if (file.isDirectory() && !file.getName().equals("casas")) {
                //System.out.println(file.getName());
                iteratorOverAllFiles(file.listFiles());
            } else {
                if ((file.getName().equals("buscador.html")) || (file.getName().equals("index.html"))) {
                    System.out.println(file);
                    whichTypeOfExtractor(file.getAbsolutePath());
                }
            }
        }
    }

    public static void whichTypeOfExtractor(String fn) throws MalformedURLException, IOException{
        URL url = new URL("file:" + fn);
        URLConnection con = url.openConnection();
        Pattern p = Pattern.compile("text/html;\\s+charset=([^\\s]+)\\s*");
        Matcher m = p.matcher(con.getContentType());
        /*
         * If Content-Type doesn't match this pre-conception, choose default and
         * hope for the best.
         */
        String charset = m.matches() ? m.group(1) : "ISO-8859-1";
        Reader r = new InputStreamReader(con.getInputStream(), charset);
        StringBuilder buf = new StringBuilder();
        while (true) {
            int ch = r.read();
            if (ch < 0) {
                break;
            }
            buf.append((char) ch);
        }
        String str = buf.toString();
        
        if(str.contains("Resultados de") && str.contains("permutas") && 
                !str.contains("renta de casas")){
            System.out.println(fn);
            newCodeExtractor(fn, str);
        }
        else{
            oldCodeExtractor(fn);
        }
        
    }
    
    public static void newCodeExtractor(String fn, String str){
        System.out.println("called");
        String[] renta_repeater = str.split("renta_repeater");
        ArrayList<String> renta_repeater_filtered = new ArrayList();
        for (String rr : renta_repeater) {
            if (rr.contains("renta_publicado")) {
                renta_repeater_filtered.add(rr);
            }
        }
        for (String al : renta_repeater_filtered) {
            String code = "";
            String date = "";
            Pattern codepat = Pattern.compile("title=\"(.*?)\"");
            Matcher matcher = codepat.matcher(al);
            if (matcher.find()) {
                System.out.println(matcher.group(1));
                code = matcher.group(1);
            }
            Pattern datepat = Pattern.compile("\"renta_publicado\">(.*?)</div>");
            Matcher datematcher = datepat.matcher(al);
            if (datematcher.find()) {
                System.out.println(datematcher.group(1));
                date = datematcher.group(1);
            }
            if((date.contains("am") || date.contains("pm")) && date.contains(":")){
                System.out.println("not added to hasht1");
                continue;
            }
            hasht1.put(code, date);
        }
    }
    
    public static void writeTheHashtableToTextFile() throws IOException {

        File theAllTXTFile = new File(onMac + "Permutas_Date_Dictionary.csv");
        FileWriter allFileWriter = null;
        allFileWriter = new FileWriter(theAllTXTFile);
        String toWrite = hasht1.toString();
        toWrite = toWrite.replace("{", "");
        toWrite = toWrite.replace("}", "");
        toWrite = toWrite.replace("lunes,", "monday");
        toWrite = toWrite.replace("martes,", "tuesday");
        toWrite = toWrite.replace("miércoles,", "wednesday");
        toWrite = toWrite.replace("jueves,", "thursday");
        toWrite = toWrite.replace("viernes,", "friday");
        toWrite = toWrite.replace("sábado,", "saturday");
        toWrite = toWrite.replace("domingo,", "sunday");
        toWrite = toWrite.replace(",", "\n");
        allFileWriter.write(toWrite);
        allFileWriter.close();

    }

    public static void oldCodeExtractor(String fn) {
        //System.out.println(fn);
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