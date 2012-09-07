package Test;

import Permutas.Permutas_Main;
import java.io.*;
import org.htmlparser.NodeFilter;
import org.htmlparser.beans.FilterBean;
import org.htmlparser.filters.HasAttributeFilter;
import java.text.Normalizer;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.htmlparser.filters.*;

public class Collect_All_Codes {

    public static String onMac = "/Users/fsimon/Desktop/ADAIR/CubaData/";
    public static String onPC = "C:/Documents and Settings/fsimon0/My Documents/CubaData Original/";
    public static Collection<String> allCodes = new HashSet<String>();
    public static Hashtable<String, String> hasht2 = new Hashtable<String, String>();
    public String headers = "2012-30Jan";

    public static void main(String[] args) throws IOException {
        Collect_All_Codes cre = new Collect_All_Codes();
        File[] files = new File(onMac).listFiles();
        iterator(files);
        System.out.println(allCodes.size());
    }

    //LET's get rentas now
    public static void iterator(File[] firstFile) {
        for (File file : firstFile) {
            System.out.println(file);
            if (file.isDirectory() && !file.getName().equals("permutas") && !file.getName().startsWith(".")
                    && !file.getName().equals("anuncios") && !file.getName().equals("renta")) {
                iterator(file.listFiles());
            }
            else if (file.getAbsolutePath().contains("casas") && 
                    ((file.getAbsolutePath().endsWith(".html") || file.getAbsolutePath().endsWith(".htm")))){
                extractCode(file);
            }
        }
    }

    public static void extractCode(File aFile) {
        Pattern pattern2 = Pattern.compile("!(.*?).htm");
        Matcher matcher2 = pattern2.matcher(aFile.getAbsolutePath());
        if (matcher2.find()) {
            allCodes.add(matcher2.group(1));
            System.out.println(matcher2.group(1) + aFile.getName());
        }
    }
}
