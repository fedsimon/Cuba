package Rentas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
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
public class Rentas_Dater {

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

    public static void extractFromHTML(String theURL) throws URISyntaxException, ParserException {

        //********
        //This extracts the price, publish date
        FilterBean thePriceFilter = new FilterBean();
        HasAttributeFilter priceattr = new HasAttributeFilter("class", "renta_precio");
        thePriceFilter.setFilters(new NodeFilter[]{priceattr});
        thePriceFilter.setURL(theURL);
        String rawPExtract = thePriceFilter.getText();
        String priceStr = "";
        if (rawPExtract != null) {
            priceStr = rawPExtract.replace("\r\n", "");
        }
        priceStr = priceStr.replace(")", ")\n").replace("-", "- \n");

        FilterBean pubFilter = new FilterBean();
        HasAttributeFilter pubattr = new HasAttributeFilter("class", "renta_publicado");// <== WHY RENTA???
        pubFilter.setFilters(new NodeFilter[]{pubattr});
        pubFilter.setURL(theURL);
        String rawPubExtract = pubFilter.getText();
        //System.out.println("small" + rawPubExtract);
        //********

        //********
        //This makes an array of all the links on the html file
        String linkString = "";
        LinkBean linkBean = new LinkBean();
        linkBean.setURL(theURL);
        URL[] links = linkBean.getLinks();
        for (URL link : links) {
            linkString = linkString + link.toString() + "\n";
            //System.out.println(linkString);
        }
        String[] linkStringArray = linkString.split("\n");
        //********
        
        //********
        //This makes an array of all of the unique IDs
        Set <Integer> toBeFinalSet = new TreeSet <Integer>();
        Pattern pattern = Pattern.compile("!(.*?).htm"); //changed this from .html.
        for (String line : linkStringArray) {
            if (line.contains("file:") && !line.contains("index") && !line.contains("administrar")) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    //System.out.println(matcher.group(1));
                    toBeFinalSet.add(Integer.parseInt(matcher.group(1)));
                }
            }
        }
        //********

        Integer[] finalArrayofCodes = toBeFinalSet.toArray(new Integer[toBeFinalSet.size()]);
        //System.out.println("here:" + rawPubExtract);
        String[] finalArrayofDates = rawPubExtract.split("\n");

        //System.out.println(finalArrayofCodes.length + "," + finalArrayofDates.length);

        if (finalArrayofCodes.length == finalArrayofDates.length) {
            for (int w = 0; w < finalArrayofCodes.length; w++) {
                //System.out.println(finalArrayofCodes.length + "-" + w + "=" + (finalArrayofCodes.length-w));
                String key = finalArrayofCodes[finalArrayofCodes.length-w-1].toString();
                String value = finalArrayofDates[w];
                System.out.println(key + "," + value);
                hasht1.put(key, value);
                //System.out.println(finalArrayofCodes[w] + "," +finalArrayofDates[w]);
            }
        }
    }
}
