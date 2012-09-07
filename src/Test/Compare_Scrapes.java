/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *
package Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.collections.*;

/**
 *
 * @author fsimon
 *
public class Compare_Scrapes {

    public static void main(String[] args) throws IOException {
        String onMac = "/Users/fsimon/Desktop/ADAIR/CubaData/";

        String perm_anunc = "permutas/anuncios/";

        String scrape1 = "2012-23Mar";
        String scrape2 = "2012-16April";
        String shared = "Shared";
        String differents = "Symmetric Difference";

        String scrape1_Full_Path = onMac + scrape1 + "/" + perm_anunc;
        String scrape2_Full_Path = onMac + scrape2 + "/" + perm_anunc;

//        String scrape1_Full_String = "";
//        String scrape2_Full_String = "";


        File[] scrape1_files = new File(scrape1_Full_Path).listFiles();
        File[] scrape2_files = new File(scrape2_Full_Path).listFiles();

        Collection<String> one = new HashSet<String>();
        Collection<String> two = new HashSet<String>();

        for (File aScrape1 : scrape1_files) {
            if (aScrape1.getAbsolutePath().endsWith(".html") || aScrape1.getAbsolutePath().endsWith(".htm")) {
                Pattern pattern1 = Pattern.compile("!(.*?).htm");
                Matcher matcher1 = pattern1.matcher(aScrape1.getAbsolutePath());
                if (matcher1.find()) {
                    one.add(matcher1.group(1));
                }
            }
        }

        for (File aScrape2 : scrape2_files) {
            if (aScrape2.getAbsolutePath().endsWith(".html") || aScrape2.getAbsolutePath().endsWith(".htm")) {
                Pattern pattern2 = Pattern.compile("!(.*?).htm");
                Matcher matcher2 = pattern2.matcher(aScrape2.getAbsolutePath());
                if (matcher2.find()) {
                    two.add(matcher2.group(1));
                }
            }
        }

        Collection<String> different = CollectionUtils.disjunction(one, two);
        Object[] differentArr = different.toArray();

        Object[] bigger = null;
        Object[] smaller = null;

        if (one.size() < two.size()) {
            smaller = one.toArray();
            bigger = two.toArray();

            if (bigger == null) {
                System.out.println("not initialized");
            }
        }
        if (two.size() < one.size()) {
            bigger = one.toArray();
            smaller = two.toArray();
            if (bigger == null) {
                System.out.println("not initialized");
            }
        }

        for (int i = 0; i < bigger.length; i++) {
            System.out.println("linFIRST LINEe" + bigger[i]);
        }

        for (int i = 0; i < bigger.length; i++) {
            if (i < smaller.length && i < differentArr.length) {
                bigger[i] = bigger[i] + "," + smaller[i] + "," + differentArr[i] + "\n";
            } else if (i > smaller.length && i < differentArr.length) {
                bigger[i] = bigger[i] + "," + "," + differentArr[i] + "\n";
                System.out.println("2");
            } else if (i < smaller.length && i >= differentArr.length) {
                bigger[i] = bigger[i] + "," + smaller[i] + "," + "\n";
            } else {
                bigger[i] = bigger[i] + "," + "," + "\n";
            }
        }

        String fullString = "";

        for (int i = 0; i < bigger.length; i++) {
            System.out.println("line" + bigger[i]);
            fullString = fullString + bigger[i];
        }
        System.out.println(bigger.length + "," + smaller.length + "," + differentArr.length);

        File bothScrapes = new File(onMac + "(" + scrape1 + ")" + " and " + "(" + scrape2 + ")" + "_Test_Codes.csv");
        FileWriter bothScrape_Writer = null;
        try {
            bothScrape_Writer = new FileWriter(bothScrapes);
        } catch (IOException e1) {
        }
        bothScrape_Writer.write(scrape1 + "," + scrape2 + "," + differents + " (Size: "
                + differentArr.length + ")" + "\n");
        bothScrape_Writer.write(fullString);
        bothScrape_Writer.close();
    }
}*/