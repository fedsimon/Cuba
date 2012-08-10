package Test;

import Permutas.Permutas_Main;
import java.io.*;
import org.htmlparser.NodeFilter;
import org.htmlparser.beans.FilterBean;
import org.htmlparser.filters.HasAttributeFilter;
import java.text.Normalizer;
import java.io.IOException;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.htmlparser.filters.*;

public class Test {

    public static String onMac = "/Users/fsimon/Desktop/ADAIR/CubaData/";
    public static String onPC = "C:/Documents and Settings/fsimon0/My Documents/CubaData Original/";
    public static Hashtable<String, String> hasht1 = new Hashtable<String, String>();
    public static Hashtable<String, String> hasht2 = new Hashtable<String, String>();
    public String headers = "2012-30Jan";

    public static void main(String[] args) throws IOException {
        Test cre = new Test();
        cre.textCreator();
    }

    public void textCreator() throws IOException {
        Test rvp1 = new Test();

        // Declare some variables
        String date = "";
        String towrite = "";
        String path = "";

        String datepath = "/2012-30Jan/permutas/anuncios/";
        String datepathname = headers;
        
        // GET ROOT FILE
        File dir1 = new File(onMac+datepath);

        String[] datefolderChildren = dir1.list();

        String allstring = "";

        // GET ALL date FOLDERS
        for (String afile : datefolderChildren) {
            System.out.println(afile);
            if (!afile.endsWith(".csv") && !afile.startsWith(".")) {

                date = afile;

                String enclosing = onMac + datepath;

                File dir = new File(enclosing);
                System.out.println(enclosing);
                String[] individualHTMLChildren = dir.list();

                File theTXTFile = new File(onMac + datepathname + "-Test_NumberofCodes.csv");

                FileWriter aFileWriter = null;
                try {
                    aFileWriter = new FileWriter(theTXTFile);
                } catch (IOException e1) {
                }

                if (aFileWriter != null) {
                    try {
                        aFileWriter.write(headers);
                    } catch (IOException e) {
                    }
                }
                if (individualHTMLChildren != null) {
                    for (int j = 0; j < individualHTMLChildren.length; j++) {
                        if (individualHTMLChildren[j].startsWith(".")) {
                            continue;
                        }
                        if (!individualHTMLChildren[j].contains("htm") || individualHTMLChildren[j].contains("index.html")) {
                            continue;
                        }
                        String fn = enclosing + individualHTMLChildren[j];
                        System.out.println("filename:" + fn + "progress:"
                                + (float) j / individualHTMLChildren.length + "%"
                                + "-" + "/" + datefolderChildren.length + "%");

                        if (aFileWriter != null) {
                            try {
                                path = date.replace(",", ";") + "," + individualHTMLChildren[j].replace(",", ";");
                                Pattern pattern = Pattern.compile("!(.*?).htm");
                                Matcher matcher = pattern.matcher(fn);
                                if (matcher.find()){
                                    towrite = matcher.group(1) + "\n";
                                    System.out.println("work?" + towrite);
                                }
                                else{System.out.println("no code");}
                                aFileWriter.write(towrite);
                                allstring = allstring + towrite;
                            } catch (IOException e) {
                            }
                        }
                    }
                }
            }
        }
        File theAllTXTFile = new File(onMac + datepathname + "Permutas_Number_of_Codes.csv");
        FileWriter allFileWriter = null;
        try {
            allFileWriter = new FileWriter(theAllTXTFile);
        } catch (IOException e1) {
        }
        allFileWriter.write(headers);
        allFileWriter.write(allstring);
    }
}