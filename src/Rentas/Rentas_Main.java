package Rentas;

import Permutas.Permutas_Main;
import java.io.*;
import org.htmlparser.NodeFilter;
import org.htmlparser.beans.FilterBean;
import org.htmlparser.filters.HasAttributeFilter;
import java.text.Normalizer;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.htmlparser.filters.*;

public class Rentas_Main {/*
     * DELETE TYPE OF RENT
     */


    public static String onMac = "/Users/fsimon/Desktop/ADAIR/CubaData/";
    public static String onPC = "C:/Documents and Settings/fsimon0/My Documents/CubaData Original/";
    public static Hashtable<String, String> hasht1 = new Hashtable<String, String>();
    public static Hashtable<String, String> hasht2 = new Hashtable<String, String>();
    public static Hashtable<String, String[]> forUniqueTable = new Hashtable<String, String[]>();
    public String headers = "Precio, Price per Hour Dummy, Price per Day Dummy, "
            + "Price per Month Dummy, Date, Type of Rent, For Cubans Dummy, "
            + "For Foreigners Dummy,Rooms, Direction,Location,City Dummy, "
            + "Beach Dummy, Rural Dummy,Type, Apartment Dummy, House Dummy, "
            + "Rooms Dummy, Description , AirCon, Video, Cold Water, Telephone,"
            + "Fan, TV, Kitchen, Safe (for belongings), Fridge, Private Entrance, Terrace/Living Room, Parking, Audio, Private Bathroom,"
            + "View of the Sea, Breakfast, Laundry, Spanish Classes, Dinner, Internet, Dancing Classes, Minibar, Massages, Excursions, "
            + "Picture Dummy, Municipio Code \n ";

    public static void main(String[] args) throws IOException, ParseException {
        Rentas_Main cre = new Rentas_Main();
        Rentas_Main.createDictionary();
        cre.textCreator();
        cre.makeTheHashTableFile();
        System.out.println("done");
    }

    // CHANGE THE LOCATION OF THE DICTIONARY
    public static void createDictionary() throws FileNotFoundException, IOException {
        Rentas_Main fixer2 = new Rentas_Main();
        String fn = onMac + "Rentas_Date_Dictionary.csv";
        //"C:/Documents and Settings/fsimon0/My Documents/CubaData Original/RentasDateDictionary.csv";
        String municfn = onMac + "MunicipioDictionaryaccentfix.csv";
        //"C:/Documents and Settings/fsimon0/My Documents/CubaData Original/MunicipioDictionaryaccentfix.csv";

        BufferedReader areader = new BufferedReader(new FileReader(fn));
        BufferedReader munireader = new BufferedReader(new FileReader(municfn));
        //Scanner dateDictionary = new Scanner (fn);

        String sCurrentLine;
        String sCurrentLineMun;
        while ((sCurrentLine = areader.readLine()) != null) {
            System.out.println(sCurrentLine);
            String key = sCurrentLine.split("=")[0].trim();
            String object = sCurrentLine.split("=")[1].trim();
            hasht1.put(key, object);
        }
        while ((sCurrentLineMun = munireader.readLine()) != null) {
            //System.out.println(sCurrentLineMun);
            String key2 = sCurrentLineMun.split("=")[0].trim();
            String object2 = sCurrentLineMun.split("=")[1].trim(); // notice the key and object are switched.
            key2 = fixer2.accentFix(key2).toLowerCase();
            key2 = key2.replace("- ", "");
            object2 = fixer2.accentFix(object2).toLowerCase();
            //System.out.println("added as key:"+key2);
            System.out.println("FINAL ADDED IN DICTIONARY" + key2);
            hasht2.put(key2, object2);
        }
    }

        public void makeTheHashTableFile() throws IOException{
        File theAllTXTFile = new File(onMac + "Rentas-UniqueSpreadsheet.csv");
        FileWriter allFileWriter = new FileWriter(theAllTXTFile);
        allFileWriter.write(headers);
        String completeMinusHeaders = "";
        
        System.out.println("asdkajsdk" + forUniqueTable.get("1901")[1]);
        
        Object[] keyArray = forUniqueTable.keySet().toArray();
        for(Object key : keyArray){
            completeMinusHeaders = completeMinusHeaders + forUniqueTable.get(key)[1];
        }

        allFileWriter.write(completeMinusHeaders);
        allFileWriter.close();
    }

    public String getCodeFromURL (String theURL){
        String code = "";
        Pattern pattern = Pattern.compile("!(.*?).htm");
        Matcher matcher = pattern.matcher(theURL);
        if (matcher.find()) {
            code = matcher.group(1);
        }
        return code;
    }
    
    public String myStringExtract(String theURL) {
        // NO PICTURES
        //<editor-fold>
        //Find the date using the dictionary.
        Pattern pattern = Pattern.compile("!(.*?).htm");
        Matcher matcher = pattern.matcher(theURL);
        String dateFromDict = "0";
        if (matcher.find()) {
            //System.out.println("key for hasht1" + matcher.group(1));
            if (hasht1.get(matcher.group(1)) != null) {
                dateFromDict = hasht1.get(matcher.group(1));
            }
        }

        TagNameFilter theTD = new TagNameFilter("td");
        HasAttributeFilter theIMG = new HasAttributeFilter("src", "http://www.cubisima.com/Images/checked.png");
        HasChildFilter childOfTD = new HasChildFilter(theIMG);
        AndFilter both = new AndFilter(theTD, childOfTD);

        // Set FilterBean
        FilterBean fb = new FilterBean();
        fb.setFilters(new NodeFilter[]{both});
        fb.setURL(theURL);
        String comoS_N = fb.getText();

        String aicon_N = "0", vid_N = "0", agf_N = "0",
                telef_N = "0", vent_N = "0", tv_N = "0",
                cocina_N = "0", cajfu_N = "0", refr_N = "0",
                entr_N = "0", terr_N = "0", parq_N = "0",
                audio_N = "0", banpri_N = "0",
                vista_N = "0", desayuno_N = "0", lavanderia_N = "0", clases_de_espanol_N = "0",
                cena_N = "0", internet_N = "0", clases_de_baile_N = "0",
                minibar_N = "0", masajes_N = "0", excursiones_N = "0";

        //System.out.println("THIS IS comoS_N!!!" + comoS_N);
        if (comoS_N.contains("Aire acondicionado")) {
            aicon_N = "1";
        }
        if (comoS_N.contains("Video")) {
            vid_N = "1";
        }
        if (comoS_N.contains("Agua Fria/Caliente")) {
            agf_N = "1";
        }
        if (comoS_N.contains("Telefono")) {
            telef_N = "1";
        }
        if (comoS_N.contains("Ventilador")) {
            vent_N = "1";
        }
        if (comoS_N.contains("TV")) {
            tv_N = "1";
        }
        if (comoS_N.contains("Cocina")) {
            cocina_N = "1";
        }
        if (comoS_N.contains("Caja fuerte")) {
            cajfu_N = "1";
        }
        if (comoS_N.contains("Refrigerador")) {
            refr_N = "1";
        }
        if (comoS_N.contains("Entrada privada")) {
            entr_N = "1";
        }
        if (comoS_N.contains("Terraza/Sala de estar")) {
            terr_N = "1";
        }
        if (comoS_N.contains("Parqueo")) {
            parq_N = "1";
        }
        if (comoS_N.contains("Audio")) {
            audio_N = "1";
        }
        if (comoS_N.contains("Baño privado")) {
            banpri_N = "1";
        } //Check if enie works
        if (comoS_N.contains("Audio")) {
            audio_N = "1";
        }
        if (comoS_N.contains("Vista al mar")) {
            vista_N = "1";
        }
        if (comoS_N.contains("Desayuno")) {
            desayuno_N = "1";
        }
        if (comoS_N.contains("Lavandería")) {
            lavanderia_N = "1";
        }
        if (comoS_N.contains("Clases de Español")) {
            clases_de_espanol_N = "1";
        }
        if (comoS_N.contains("Cena")) {
            cena_N = "1";
        }
        if (comoS_N.contains("Internet")) {
            internet_N = "1";
        }
        if (comoS_N.contains("Clases de baile")) {
            clases_de_baile_N = "1";
        }
        if (comoS_N.contains("Minibar")) {
            minibar_N = "1";
        }
        if (comoS_N.contains("Masajes")) {
            masajes_N = "1";
        }
        if (comoS_N.contains("Excursiones")) {
            excursiones_N = "1";
        }


        //DIRECCION
        FilterBean direc_NP = new FilterBean();
        HasAttributeFilter direcattr_NP1 = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelDireccion1");
        HasAttributeFilter direcattr_NP = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelDireccion");
        OrFilter direcattr_NP1AND = new OrFilter(direcattr_NP1, direcattr_NP);
        direc_NP.setFilters(new NodeFilter[]{direcattr_NP1AND});
        direc_NP.setURL(theURL);
        String direcs_NP = direc_NP.getText();
        System.out.println("This should not be empty:" + direcs_NP);
        String munic = "";
        //System.out.println("this should be 1 or 2" + direcs_NP.split("\\r|\\n").length);
        if (direcs_NP.split("\\r|\\n").length == 1) {
            munic = direcs_NP;
        } else {
            munic = direcs_NP.split("\\r|\\n")[direcs_NP.split("\\r|\\n").length - 1];
        }

        munic = accentFix(munic);

        //System.out.println("is this just the municipio???" + munic);
        direcs_NP = direcs_NP.replaceAll("\\r|\\n", " ");
        direcs_NP = direcs_NP.replaceAll(",", "");

        String municCode = "0";
        munic = munic.toLowerCase();
        System.out.println("searching for key: " + munic);
        if (hasht2.get(munic) != null) {
            //System.out.println("found a munic code");
            municCode = hasht2.get(munic);
        }


        //</editor-fold>

        // WITH PICTURES

        //TYPE
        FilterBean type_P = new FilterBean();
        HasAttributeFilter typattr_P1 = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelTipoAlojamiento1");
        HasAttributeFilter typattr_P = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelTipoAlojamiento");
        OrFilter typattr_PAND = new OrFilter(typattr_P1, typattr_P);
        type_P.setFilters(new NodeFilter[]{typattr_PAND});
        type_P.setURL(theURL);
        String types_P = type_P.getText();
        String d_types_apart = "0";
        String d_types_house = "0";
        String d_types_rooms = "0";

        if (types_P.contains("Apartamento")) {
            d_types_apart = "1";
        }
        if (types_P.contains("Casa")) {
            d_types_house = "1";
        }
        if (types_P.contains("Habitacion(es)")) {
            d_types_rooms = "1";
        }



        //UBICACION
        FilterBean ubic_P = new FilterBean();
        HasAttributeFilter ubicattr_P1 = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelUbicacion1");
        HasAttributeFilter ubicattr_P = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelUbicacion");
        OrFilter ubicattr_PAND = new OrFilter(ubicattr_P1, ubicattr_P);
        ubic_P.setFilters(new NodeFilter[]{ubicattr_PAND});
        ubic_P.setURL(theURL);
        String ubics_P = ubic_P.getText();
        String d_ubics_city = "0";
        String d_ubics_beach = "0";
        String d_ubics_rural = "0";
        if (ubics_P.contains("en la ciudad")) {
            d_ubics_city = "1";
        }
        if (ubics_P.contains("en la playa")) {
            d_ubics_beach = "1";
        }
        if (ubics_P.contains("en el campo")) {
            d_ubics_rural = "1";
        }

        //TYPE OF RENT
        FilterBean typrent_P = new FilterBean();
        HasAttributeFilter typrentattr_P1 = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelTipoRenta1");
        HasAttributeFilter typrentattr_P = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelTipoRenta");
        OrFilter typrentattr_PAND = new OrFilter(typrentattr_P1, typrentattr_P);
        typrent_P.setFilters(new NodeFilter[]{typrentattr_PAND});
        typrent_P.setURL(theURL);
        String typrents_P = typrent_P.getText();
        String D_types_foreign = "0";
        String D_types_cuban = "0";
        if (typrents_P.contains("a extranjeros")) {
            D_types_foreign = "1";
        }
        if (typrents_P.contains("a cubanos")) {
            D_types_cuban = "1";
        }

        //HABITACIONES
        FilterBean habs_P = new FilterBean();
        HasAttributeFilter habsattr_P1 = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelHabitaciones1");
        HasAttributeFilter habsattr_P = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelHabitaciones");
        OrFilter habsattr_P1AND = new OrFilter(habsattr_P1, habsattr_P);
        habs_P.setFilters(new NodeFilter[]{habsattr_P1AND});
        habs_P.setURL(theURL);
        String habss_P = habs_P.getText();

        //PRECIO
        FilterBean prec_P = new FilterBean();
        HasAttributeFilter precattr_P1 = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelPrecio1");
        HasAttributeFilter precattr_P = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelPrecio");
        OrFilter precattr_PAND = new OrFilter(precattr_P1, precattr_P);
        prec_P.setFilters(new NodeFilter[]{precattr_PAND});
        prec_P.setURL(theURL);
        String precs_P = prec_P.getText();
        String D_precs_hour = "0";
        String D_precs_day = "0";
        String D_precs_month = "0";
        if (precs_P.contains("por hora")) {
            D_precs_hour = "1";
        }
        if (precs_P.contains("diario")) {
            D_precs_day = "1";
        }
        if (precs_P.contains("mensual")) {
            D_precs_month = "1";
        }
        precs_P = precs_P.replaceAll("por hora", "");
        precs_P = precs_P.replaceAll("diario", "");
        precs_P = precs_P.replaceAll("mensual", "");
        precs_P = precs_P.replaceAll("cuc", "");
        //System.out.println("precs" + precs);

        //DESCRPCION
        FilterBean descr_P = new FilterBean();
        HasAttributeFilter descrattr_P1 = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelDescripcion1");
        HasAttributeFilter descrattr_P = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelDescripcion");
        OrFilter descrattr_PAND = new OrFilter(descrattr_P1, descrattr_P);
        descr_P.setFilters(new NodeFilter[]{descrattr_PAND});
        descr_P.setURL(theURL);
        String descrs_P = descr_P.getText();
        //</editor-fold>

        String picDummy = "0"; //FIX THE PICTURE DETAIL.

        String[] fArray = new String[]{
            precs_P, D_precs_hour, D_precs_day, D_precs_month, dateFromDict,
            typrents_P, D_types_cuban, D_types_foreign,
            habss_P, direcs_NP, ubics_P, d_ubics_city, d_ubics_beach, d_ubics_rural,
            types_P, d_types_apart, d_types_house, d_types_rooms,
            descrs_P,
            aicon_N, vid_N, agf_N,
            telef_N, vent_N, tv_N,
            cocina_N, cajfu_N, refr_N,
            entr_N, terr_N, parq_N,
            audio_N, banpri_N,
            vista_N, desayuno_N, lavanderia_N, clases_de_espanol_N,
            cena_N, internet_N, clases_de_baile_N,
            minibar_N, masajes_N, excursiones_N, picDummy, municCode};

        String retstr = "";

        for (int o = 0; o < fArray.length; o++) {
            fArray[o] = accentFix(fArray[o]);
            retstr = retstr + fArray[o] + ",";
        }
        return retstr;
    }

    public String accentFix(String str) {

        //System.out.println("HEREHREHRHEHR" + str);
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = str.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        str = str.trim(); //removes trailing and leading whitespace
        str = str.replace("½", "");
        str = str.replace(",", "");
        str = str.replaceAll("\\r|\\n", " ");

        return str;
    }
    
    public static void makeUniqueHashTable (String code, String date, String allInfo) throws ParseException{
        // We need to check if it exists in the table already, and replace if we have a newer date.
        SimpleDateFormat allParse = new SimpleDateFormat("yyyyddMMM");
        SimpleDateFormat allDisplay = new SimpleDateFormat("dd-MM-yyyy");
        String [] thisCalArr = allDisplay.format(allParse.parse(date)).split("-");
        int thisMonth = Integer.parseInt(thisCalArr[1]);		    
	int thisYear = Integer.parseInt(thisCalArr[2]);
	int thisDay = Integer.parseInt(thisCalArr[0]);
        Calendar newDate = new GregorianCalendar(thisYear, thisMonth, thisDay);

        String [] anArr = {allDisplay.format(allParse.parse(date)), allInfo};
        if (forUniqueTable.containsKey(code)){
            //If the entry exists, make a Calendar out of it
            String[] insideCalArr = forUniqueTable.get(code)[0].split("-");
            int insideMonth = Integer.parseInt(insideCalArr[1]);
            int insideYear = Integer.parseInt(insideCalArr[2]);
            int insideDAy = Integer.parseInt(insideCalArr[0]);
            if(newDate.after(forUniqueTable.get(code)[0])){
                forUniqueTable.remove(code);
                forUniqueTable.put(code, anArr);
            }
        }
        else{
            forUniqueTable.put(code, anArr);
        }
    }


    public void textCreator() throws IOException, ParseException {
        Rentas_Main rvp1 = new Rentas_Main();

        // Declare some variables
        String date = "";
        String towrite = "";
        String path = "";


        // GET ROOT FILE
        File dir1 = new File(onMac);
        //"C:/Documents and Settings/fsimon0/My Documents/Cubisima Final/"
        String[] datefolderChildren = dir1.list();

        String allstring = "";

        // GET ALL date FOLDERS
        for (int i = 1; i < datefolderChildren.length; i++) {
            if (!datefolderChildren[i].contains("NCB") && !datefolderChildren[i].contains("NR") && !datefolderChildren[i].endsWith(".csv") && !datefolderChildren[i].startsWith(".")) {
                date = datefolderChildren[i];
                // APPLY ALL ALGORITHM TO ALL datefolders
                String enclosing = onMac + date + "/casas/renta/";
                //"C:/Documents and Settings/fsimon0/My Documents/Cubisima Final/"+date+"/renta/";
                File dir = new File(enclosing);
                System.out.println(enclosing);
                String[] individualHTMLChildren = dir.list();
                ////System.out.println("THISTHISTHIS:" + children[1]);

                File theTXTFile = new File(onMac + date + "/" + date + " - Rentas.csv");
                //"C:/Documents and Settings/fsimon0/My Documents/Cubisima Final/"+date+"/"+date+" - Rentas.csv");
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
                //System.out.println(individualHTMLChildren[0]);
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
                                + "-" + i + "/" + datefolderChildren.length + "%");

                        if (aFileWriter != null) {
                            try {
                                path = date.replace(",", ";") + "," + individualHTMLChildren[j].replace(",", ";");
                                towrite = rvp1.myStringExtract(fn) + path + "\n";
                                ///// MAKE UNIQUE HASHTABLE
                                String code = getCodeFromURL(fn);
                                makeUniqueHashTable(code, date, towrite);
                                /////---------------
                                aFileWriter.write(towrite);
                                allstring = allstring + towrite;
                            } catch (IOException e) {
                            }
                        }
                    }
                }
            }
        }
        //try {aFileWriter.close();} catch (IOException e) {}
        File theAllTXTFile = new File(onMac + "Cubisima Rentas.csv");
        //"C:/Documents and Settings/fsimon0/My Documents/Cubisima Rentas.csv"
        FileWriter allFileWriter = null;
        try {
            allFileWriter = new FileWriter(theAllTXTFile);
        } catch (IOException e1) {
        }
        allFileWriter.write(headers);
        allFileWriter.write(allstring);
    }
}