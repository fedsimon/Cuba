/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 */
public class OldCompare {

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
        
        //Make shared list
        Collection<String> different = CollectionUtils.disjunction(one,two);
        ArrayList oneA = new ArrayList(one);
        ArrayList twoA = new ArrayList(two);
        
        
        
        //Make the full string
        String both_Full_String = "";
        int leftoff = 0;
        
        for(int i = 0; i<Math.min(one.size(),two.size()); i++){
            both_Full_String = both_Full_String + oneA.get(i) + "," + twoA.get(i) + "\n";
            leftoff = i;
        }
        
        if(one.size()<two.size()){
            for(int j = leftoff; j<two.size();j++){
                both_Full_String = both_Full_String + "," + twoA.get(j) + "\n";
            }
        }
        
        if(two.size()<one.size()){
            for(int j = leftoff; j<one.size();j++){
                both_Full_String = both_Full_String + "," + oneA.get(j) + "\n";
            }
        }
        
        String[] both_Full_String_Array = both_Full_String.split("\n");
        Object[] differentArray = different.toArray();
        //Object[] similarArray = similar.toArray();
        
        for(int k = 0; k<differentArray.length; k++){
            System.out.println("differentarrayk" + differentArray[k]);
            both_Full_String_Array[k] = both_Full_String_Array[k] + "," + differentArray[k];
        }
        
        String theFinalString = "";
        
        for(String line:both_Full_String_Array){
            theFinalString = theFinalString + line + "\n";
        }
        
        
        //DEBUG
        System.out.println("differentsize" + differentArray.length);
        //System.out.println("samsize" + similarArray.length);
        System.out.println("scrape1AL.size()" + one.size() + "," + "scrape2AL.size()" + two.size());
        

        //Write
        File bothScrapes = new File(onMac + "("+ scrape1 +")" + " and " + "(" + scrape2 + ")" + "_Test_Codes.csv");
        FileWriter bothScrape_Writer = null;
        try {
            bothScrape_Writer = new FileWriter(bothScrapes);
        } catch (IOException e1) {
        }
        bothScrape_Writer.write(scrape1 + "," + scrape2 +","+ differents+" (Size: "+
                differentArray.length +")" + "\n");
        bothScrape_Writer.write(theFinalString);
        bothScrape_Writer.close();
    }
}
