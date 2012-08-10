package Rentas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.htmlparser.NodeFilter;
import org.htmlparser.beans.FilterBean;
import org.htmlparser.beans.LinkBean;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.util.ParserException;

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
        //"C:/Documents and Settings/fsimon0/My Documents/CubaData Original/Rentas_Date_Dictionary.csv"
        FileWriter allFileWriter = null;
        try {
            allFileWriter = new FileWriter(theAllTXTFile);
        } catch (IOException e1) {
        }
        String toWrite = hasht1.toString();
        toWrite = toWrite.replace("{", "");
        toWrite = toWrite.replace("}", "");
        toWrite = toWrite.replace(",", "\n");
        allFileWriter.write(toWrite);
        try {
            allFileWriter.close();
        } catch (IOException e) {
        }
    }

    public static void extractFromHTML(String theURL) throws URISyntaxException, ParserException {

        FilterBean thePriceFilter = new FilterBean();
        HasAttributeFilter priceattr = new HasAttributeFilter("class", "renta_precio");
        thePriceFilter.setFilters(new NodeFilter[]{priceattr});
        thePriceFilter.setURL(theURL);
        String rawPExtract = thePriceFilter.getText();
        String priceStr = rawPExtract.replace("\r\n", "");
        priceStr = priceStr.replace(")", ")\n").replace("-", "- \n");
        //System.out.println("jhjbj"+priceStr);

        FilterBean pubFilter = new FilterBean();
        HasAttributeFilter pubattr = new HasAttributeFilter("class", "renta_publicado");
        pubFilter.setFilters(new NodeFilter[]{pubattr});
        pubFilter.setURL(theURL);
        String rawPubExtract = pubFilter.getText();
        //System.out.println("ASDASDASDASDASDASDASDASDA" + rawPubExtract)


        String linkString = "";

        LinkBean linkBean = new LinkBean();
        linkBean.setURL(theURL);
        URL[] links = linkBean.getLinks();
        for (URL link : links) {
            linkString = linkString + link.toString() + "\n";
            //System.out.println(linkString);
        }

        String[] linkStringArray = linkString.split("\n");
        String toBeFinalArray = "";

        for (String line : linkStringArray) {
            if (line.contains("file:") && !line.contains("index") && !line.contains("administrar")) {
                Pattern pattern = Pattern.compile("!(.*?).html");
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    //System.out.println(matcher.group(1));
                    toBeFinalArray = toBeFinalArray + matcher.group(1) + "\n";
                }
            }
        }

        String[] finalArrayWithDuplicates = toBeFinalArray.split("\n");;
        String tobeUnique = "";

        for (int tobeeven = 0; tobeeven < finalArrayWithDuplicates.length; tobeeven++) {
            if (tobeeven % 2 == 0) {
                tobeUnique = tobeUnique + finalArrayWithDuplicates[tobeeven] + "\n";
            }
        }

        String[] finalArrayofCodes = tobeUnique.split("\n");

        String[] finalArrayofDates = rawPubExtract.split("\n");

        System.out.println(finalArrayofCodes.length + "," + finalArrayofDates.length);

        if (finalArrayofCodes.length == finalArrayofDates.length) {
            //System.out.println("CATASTROPHIC FAILURE");
            for (int w = 0; w < finalArrayofCodes.length; w++) {
                String key = finalArrayofCodes[w];
                String value = finalArrayofDates[w];
                System.out.println(key + ","+ value);
                hasht1.put(key, value);
                //System.out.println(finalArrayofCodes[w] + "," +finalArrayofDates[w]);
            }
        }
    }
}
