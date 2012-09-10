package Rentas;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.htmlparser.NodeFilter;
import org.htmlparser.beans.FilterBean;
import org.htmlparser.beans.LinkBean;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.util.ParserException;

// Look for documentation in Casas_Dater
public class Rentas_Alt_Dater {

    public static String onMac = "/Users/fsimon/Desktop/ADAIR/CubaData/";
    public static String onPC = "C:/Documents and Settings/fsimon0/My Documents/CubaData Original/";
    public static Hashtable<String, String> hasht1 = new Hashtable<String, String>();

    public static void main(String[] args) throws IOException, URISyntaxException, ParserException {
        File[] files = new File(onMac).listFiles();
        iteratorOverAllFiles(files);
        writeTheHashtableToTextFile();
        //System.out.println(hasht1.toString());
    }

    public static void iteratorOverAllFiles(File[] files) throws URISyntaxException, ParserException {
        for (File file : files) {
            if (file.isDirectory() && !file.getName().equals("permutas")
                    && !file.getName().equals("anuncios")) {
                //System.out.println(file.getName());
                iteratorOverAllFiles(file.listFiles());
            } else {
                if ((file.getName().equals("buscador.html") || file.getName().equals("index.html"))
                        && file.getAbsolutePath().contains("renta")) {
                    System.out.println(file.getAbsolutePath());
                    extractFromHTML(file.getAbsolutePath());
                    //System.out.println("something sent to codeExtractEntire");
                }
            }
        }
    }

    public static void writeTheHashtableToTextFile() throws IOException {
        File theAllTXTFile = new File(onMac + "Rentas_Date_Dictionary.csv");
        FileWriter allFileWriter = new FileWriter(theAllTXTFile);
        String toWrite = hasht1.toString();
        toWrite = toWrite.replaceAll("[{}]", "");
        toWrite = toWrite.replace(",", "\n");
        allFileWriter.write(toWrite);
        allFileWriter.close();
    }

    public static void extractFromHTML(String theURL) throws URISyntaxException,
            ParserException, IOException {
        URL url = new URL("file:" + theURL);
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
            hasht1.put(code, date);
        }
    }
}
