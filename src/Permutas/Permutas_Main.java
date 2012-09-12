package Permutas;

import java.io.*;
import org.htmlparser.NodeFilter;
import org.htmlparser.beans.FilterBean;
import org.htmlparser.filters.HasAttributeFilter;
import java.awt.*;
import java.awt.event.*;
import java.lang.Object;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.*;
import java.lang.Object;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Enumeration;
import java.util.Hashtable;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.regex.Matcher;
import org.htmlparser.filters.OrFilter;

public class Permutas_Main extends Frame {

    public static String onMac = "/Users/fsimon/Desktop/ADAIR/CubaTest/";
    public static String onPC = "C:/Documents and Settings/fsimon0/My Documents/CubaData Original/";
    public static Hashtable<String, String> hasht1 = new Hashtable<String, String>();
    public static Hashtable<String, String> hasht2 = new Hashtable<String, String>();
    public static Hashtable<String, String[]> forUniqueTable = new Hashtable<String, String[]>();
    public static String headers =
            //<editor-fold>

            "Codigo, Date Published, Type, "
            ///// FIRST HAVE
            //<editor-fold>
            + "Title (Have)_1, TipoLocal(Have)_1, Rooms (Have),"
            + "# Rooms _1, # Bathrooms _1, Services(Have),"
            + "Sala-Comedor Dummy_1, Sala Dummy_1, Comedor Dummy_1, "
            + "Cocina Dummy_1, Cocina-Comedor Dummy,"
            + "Azotea Libre Dummy_1, Azotea Compartida Dummy_1, Telefono Dummy,"
            + "Balcon Dummy_1, Barbacoa Dummy_1, Terraza Dummy_1, "
            + "Patio Dummy_1, Portal Dummy_1, Jardin Dummy_1, Piscina Dummy,"
            + "Garaje Dummy_1, Posibilidad Garaje Dummy_1, Elevador Dummy,"
            + "Puntal Alto Dummy_1, Gas de Balon Dummy_1, "
            + "Gas de la Calle Dummy_1, Agua las 24 horas Dummy_1, "
            + "Carposhe Dummy_1,"
            + "Address (Have)_1, Municipality (Have)_1, Municipality Code Have_1, Habana (Dummy)_1, "
            + "Address Details (Have)_1, Quality of Unit (Higher is better) (Have)_1, "
            + "Quality of Building (Have)_1, Repairable Dummy_1, Number of People_1, Further Observations (Have)_1, "
            + "||||||||||, "
            //</editor-fold>

            ///// SECOND HAVE
            //<editor-fold>
            + "Title (Have), TipoLocal(Have)_2, Rooms_2 (Have),"
            + "# Rooms _2, # Bathrooms _2 , Services(Have)_2,"
            + "Sala-Comedor Dummy_2, Sala Dummy_2, Comedor Dummy_2, "
            + "Cocina Dummy_2, Cocina-Comedor Dummy,"
            + "Azotea Libre Dummy_2, Azotea Compartida Dummy_2, Telefono Dummy,"
            + "Balcon Dummy_2, Barbacoa Dummy_2, Terraza Dummy_2, "
            + "Patio Dummy_2, Portal Dummy_2, Jardin Dummy_2, Piscina Dummy,"
            + "Garaje Dummy_2, Posibilidad Garaje Dummy_2, Elevador Dummy,"
            + "Puntal Alto Dummy_2, Gas de Balon Dummy_2, "
            + "Gas de la Calle Dummy_2, Agua las 24 horas Dummy_2, "
            + "Carposhe Dummy_2,"
            + "Address (Have)_2, Municipality (Have)_2, Municipality Code Have_2,"
            + "Habana (Dummy)_2, Address Details (Have)_2,"
            + "Quality of Unit (Have)_2, Quality of Building (Have)_2, Repairable Dummy_2, Number of People_2,"
            + "Further Observations (Have)_2, ||||||||||, "
            //</editor-fold>

            ///// THIRD HAVE
            //<editor-fold>
            + "Title (Have), TipoLocal (Have)_3, Rooms_3 (Have),"
            + "# Rooms _3, # Bathrooms _3 , Services(Have)_3,"
            + "Sala-Comedor Dummy_3, Sala Dummy_3, Comedor Dummy_3, "
            + "Cocina Dummy_3, Cocina-Comedor Dummy,"
            + "Azotea Libre Dummy_3, Azotea Compartida Dummy_3, Telefono Dummy,"
            + "Balcon Dummy_3, Barbacoa Dummy_3, Terraza Dummy_3, "
            + "Patio Dummy_3, Portal Dummy_3, Jardin Dummy_3, Piscina Dummy,"
            + "Garaje Dummy_3, Posibilidad Garaje Dummy_3, Elevador Dummy,"
            + "Puntal Alto Dummy_3, Gas de Balon Dummy_3, "
            + "Gas de la Calle Dummy_3, Agua las 24 horas Dummy_3, "
            + "Carposhe Dummy_3,"
            + "Address (Have)_3, Municipality (Have)_3, Municipality Code Have_3,"
            + "Habana (Dummy)_3, Address Details (Have)_3,"
            + "Quality of Unit (Have)_3, Quality of Building (Have)_3, Repairable Dummy_3, Number of People_3,"
            + "Further Observations (Have)_3, ||||||||||, "
            //</editor-fold>

            /// FIRST WANTED
            //<editor-fold>
            + "Type (Wanted), Rooms (Wanted),"
            + "Services (Wanted), "
            + "Sala-Comedor Dummy_Wanted_1, Sala Dummy_Wanted_1, Comedor Dummy_Wanted_1, "
            + "Cocina Dummy_Wanted_1, Cocina-Comedor Dummy,"
            + "Azotea Libre Dummy_Wanted_1, Azotea Compartida Dummy_Wanted_1, Telefono Dummy,"
            + "Balcon Dummy_Wanted_1, Barbacoa Dummy_Wanted_1, Terraza Dummy_Wanted_1, "
            + "Patio Dummy_Wanted_1, Portal Dummy_Wanted_1, Jardin Dummy_Wanted_1, Piscina Dummy,"
            + "Garaje Dummy_Wanted_1, Posibilidad Garaje Dummy_Wanted_1, Elevador Dummy,"
            + "Puntal Alto Dummy_Wanted_1, Gas de Balon Dummy_Wanted_1, "
            + "Gas de la Calle Dummy_Wanted_1, Agua las 24 horas Dummy_Wanted_1, "
            + "Carposhe Dummy_Wanted_1,"
            + "Location (Wanted), Quality of Unit (Wanted), "
            + "Quality of Building (Wanted), Details (Wanted), ||||||||||, "
            //</editor-fold>

            /// SECOND WANTED
            //<editor-fold>
            + "Type 2 (Wanted), Rooms 2 (Wanted), Services 2 (Wanted), "
            + "+ Sala-Comedor Dummy_Wanted_2, Sala Dummy_Wanted_2, Comedor Dummy_Wanted_2, "
            + "Cocina Dummy_Wanted_2, Cocina-Comedor Dummy,"
            + "Azotea Libre Dummy_Wanted_2, Azotea Compartida Dummy_Wanted_2, Telefono Dummy,"
            + "Balcon Dummy_Wanted_2, Barbacoa Dummy_Wanted_2, Terraza Dummy_Wanted_2, "
            + "Patio Dummy_Wanted_2, Portal Dummy_Wanted_2, Jardin Dummy_Wanted_2, Piscina Dummy,"
            + "Garaje Dummy_Wanted_2, Posibilidad Garaje Dummy_Wanted_2, Elevador Dummy,"
            + "Puntal Alto Dummy_Wanted_2, Gas de Balon Dummy_Wanted_2, "
            + "Gas de la Calle Dummy_Wanted_2, Agua las 24 horas Dummy_Wanted_2, "
            + "Carposhe Dummy_Wanted_2,"
            + "Location 2 (Wanted), Quality of Unit 2 (Wanted), "
            + "Quality of Building 2 (Wanted), Details 2 (Wanted), ||||||||||, "
            //</editor-fold>

            /// Third WANTED
            //<editor-fold>
            + "Type 3 (Wanted), Rooms 3 (Wanted), Services 3 (Wanted), "
            + "Sala-Comedor Dummy_Wanted_3, Sala Dummy_Wanted_3, Comedor Dummy_Wanted_3, "
            + "Cocina Dummy_Wanted_3, Cocina-Comedor Dummy,"
            + "Azotea Libre Dummy_Wanted_3, Azotea Compartida Dummy_Wanted_3, Telefono Dummy,"
            + "Balcon Dummy_Wanted_3, Barbacoa Dummy_Wanted_3, Terraza Dummy_Wanted_3, "
            + "Patio Dummy_Wanted_3, Portal Dummy_Wanted_3, Jardin Dummy_Wanted_3, Piscina Dummy,"
            + "Garaje Dummy_Wanted_3, Posibilidad Garaje Dummy_Wanted_3, Elevador Dummy,"
            + "Puntal Alto Dummy_Wanted_3, Gas de Balon Dummy_Wanted_3, "
            + "Gas de la Calle Dummy_Wanted_3, Agua las 24 horas Dummy_Wanted_3, "
            + "Carposhe Dummy_Wanted_3,"
            + "Location 3 (Wanted), Quality of Unit 3 (Wanted), "
            + "Quality of Building 3 (Wanted), Details 3 (Wanted), ||||||||||, "
            //</editor-fold>

            + "Contact Name, Owner, Address, Address Mentions Habana Dummy, Phone Number, Email, Other Info, "
            + "Side Payments Give Dummy, Side Payments Receive Dummy, Side Payments In Unspecified Direction Dummy,"
            + "Scrape Date, File Name  \n";
    //</editor-fold>

    public static void main(String[] args) throws IOException, ParseException {
        createDictionary();
        Permutas_Main rvp = new Permutas_Main();
        rvp.textCreator();
        rvp.makeTheHashTableFile();
        //String theURL = "file://localhost/Users/federicocsimon/Dropbox/CubaHousing/CubaData/Cubisima/"
        //+"02Dec2011 - CB/permutas/anuncios";        
    }
//  

    public void makeTheHashTableFile() throws IOException {
        File theAllTXTFile = new File(onMac + "Permutas-UniqueSpreadsheet.csv");
        FileWriter allFileWriter = new FileWriter(theAllTXTFile);
        allFileWriter.write(headers);
        String completeMinusHeaders = "";

        Object[] keyArray = forUniqueTable.keySet().toArray();
        for (Object key : keyArray) {
            completeMinusHeaders = completeMinusHeaders + forUniqueTable.get(key)[1];
        }

        allFileWriter.write(completeMinusHeaders);
        allFileWriter.close();
    }

    public String getCodeFromURL(String theURL) {
        String code = "";
        Pattern pattern = Pattern.compile("!(.*?).htm");
        Matcher matcher = pattern.matcher(theURL);
        if (matcher.find()) {
            code = matcher.group(1);
        }
        code = code.replaceAll("[^\\d]", "");
        return code;
    }

    public static void makeUniqueHashTable(String code, String date, String allInfo)
            throws ParseException {
        // We need to check if it exists in the table already, and replace if we have a newer date.
        SimpleDateFormat allParse = new SimpleDateFormat("yyyyddMMM");
        SimpleDateFormat allDisplay = new SimpleDateFormat("dd-MM-yyyy");
        String[] thisCalArr = allDisplay.format(allParse.parse(date)).split("-");
        int thisMonth = Integer.parseInt(thisCalArr[1]);
        int thisYear = Integer.parseInt(thisCalArr[2]);
        int thisDay = Integer.parseInt(thisCalArr[0]);
        Calendar newDate = new GregorianCalendar(thisYear, thisMonth, thisDay);

        String[] anArr = {allDisplay.format(allParse.parse(date)), allInfo};
        if (forUniqueTable.containsKey(code)) {
            //If the entry exists, make a Calendar out of it
            String[] insideCalArr = forUniqueTable.get(code)[0].split("-");
            int insideMonth = Integer.parseInt(insideCalArr[1]);
            int insideYear = Integer.parseInt(insideCalArr[2]);
            int insideDAy = Integer.parseInt(insideCalArr[0]);
            if (newDate.after(forUniqueTable.get(code)[0])) {
                forUniqueTable.remove(code);
                forUniqueTable.put(code, anArr);
            }
        } else {
            forUniqueTable.put(code, anArr);
        }
    }

    public static void createDictionary() throws FileNotFoundException,
            IOException {
        Permutas_Main fixer2 = new Permutas_Main();
        String fn = onMac + "Permutas_Date_Dictionary.csv";
        //"C:/Documents and Settings/fsimon0/My Documents/CubaData Original/"Permutas_Only_Date_Dictionary.csv"
        String municfn = onMac + "/MunicipioDictionaryaccentfix.csv";
        //"C:/Documents and Settings/fsimon0/My Documents/CubaData Original/MunicipioDictionaryaccentfix.csv"

        BufferedReader areader = new BufferedReader(new FileReader(fn));
        BufferedReader munireader = new BufferedReader(new FileReader(municfn));
        //Scanner dateDictionary = new Scanner (fn);

        String sCurrentLine;
        String sCurrentLineMun;
        while ((sCurrentLine = areader.readLine()) != null) {
            //System.out.println(sCurrentLine);
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
            //System.out.println("FINAL ADDED IN DICTIONARY" + key2);
            hasht2.put(key2, object2);
        }
    }

    public String myStringExtract(String theURL) {
        Permutas_Main fixer = new Permutas_Main();
        //GENERAL
        //<editor-fold>

        //1.- CODE && Date Dictionary
        //<editor-fold>
        String code = getCodeFromURL(theURL);
        code = code.replace("CÓDIGO", "");
        code = code.trim();
        String datepubl = "No Date";
        try {
            if (hasht1.get(code) != null) {
                //System.out.println("A Date Found!");
                datepubl = hasht1.get(code);
            }
        } catch (NullPointerException np1) {
        }


        //</editor-fold>

        //2.- Type
        //<editor-fold>
        FilterBean typeFilter = new FilterBean();
        HasAttributeFilter typeAttribute = new HasAttributeFilter("id", "ctl01_LabelCantidades");
        HasAttributeFilter typeAttribute1 = new HasAttributeFilter("id", "ctl02_LabelCantidades");
        OrFilter typeAttributeAND = new OrFilter(typeAttribute, typeAttribute1);
        typeFilter.setFilters(new NodeFilter[]{typeAttributeAND});
        typeFilter.setURL(theURL);
        String type = typeFilter.getText();
        type = type.replace("PERMUTO:", "");
        type = type.replace("viviendas", "");
        type = type.replace("vivienda ", "");
        type = type.replace("vivienda", "");
        //</editor-fold>
        //</editor-fold>

        //TENGO
        //<editor-fold>

        //1.- Location & Type
        //<editor-fold>
        FilterBean locF = new FilterBean();
        HasAttributeFilter locA = new HasAttributeFilter("id", "ctl01_LabelTitulo");
        HasAttributeFilter locA1 = new HasAttributeFilter("id", "ctl02_LabelTitulo");
        OrFilter locAND = new OrFilter(locA, locA1);
        locF.setFilters(new NodeFilter[]{locAND});
        locF.setURL(theURL);
        String hLocS = locF.getText();
        hLocS = fixer.accentFix(hLocS);
        hLocS = hLocS.replace("DIRECCIÓN", "");

        FilterBean tipolocF = new FilterBean();
        HasAttributeFilter tiplocA = new HasAttributeFilter("id", "ctl01_LabelTipoLocal");
        HasAttributeFilter tiplocA1 = new HasAttributeFilter("id", "ctl02_LabelTipoLocal");
        OrFilter tipLocAND = new OrFilter(tiplocA, tiplocA1);
        tipolocF.setFilters(new NodeFilter[]{tipLocAND});
        tipolocF.setURL(theURL);
        String htipLocS = tipolocF.getText();
        htipLocS = htipLocS.replace(",", "");
        htipLocS = fixer.accentFix(htipLocS);

        //</editor-fold>

        //2.- Habitaciones
        //<editor-fold>
        FilterBean habF = new FilterBean();
        HasAttributeFilter habA = new HasAttributeFilter("id", "ctl01_LabelHabitaciones");
        HasAttributeFilter habA1 = new HasAttributeFilter("id", "ctl02_LabelHabitaciones");
        OrFilter habAND = new OrFilter(habA, habA1);
        habF.setFilters(new NodeFilter[]{habAND});
        habF.setURL(theURL);
        String habS = habF.getText();
        if (habS.endsWith(",")) {
            habS = habS.substring(0, habS.length() - 1);
        }
        habS = habS.replace("½", ".5");


        String roomHD = "0";
        String bathrHD = "0";

        String habSEdited = habS;
        habSEdited = habSEdited.replace("cuartos", "");
        habSEdited = habSEdited.replace("cuarto", "");
        habSEdited = habSEdited.replace("baños", "");
        habSEdited = habSEdited.replace("baño", "");
        habSEdited = habSEdited.trim();

        String[] habSEditedArr = habSEdited.split(",");
        if (habSEditedArr.length > 1) {
            roomHD = habSEditedArr[0];
            bathrHD = habSEditedArr[1];
        }
        //</editor-fold>

        //3.- Servicios
        //<editor-fold>
        FilterBean serF = new FilterBean();
        HasAttributeFilter serA = new HasAttributeFilter("id", "ctl01_LabelServicios");
        HasAttributeFilter serA1 = new HasAttributeFilter("id", "ctl02_LabelServicios");
        OrFilter serAND = new OrFilter(serA, serA1);
        serF.setFilters(new NodeFilter[]{serAND});
        serF.setURL(theURL);
        String serS = serF.getText();

        //Initialize Strings to "zero"
        String serSalaComH_1 = "0", serSalaH_1 = "0", serComedorH_1 = "0",
                serCocinaH_1 = "0", serCocinaComH_1 = "0", serAzotLibH_1 = "0",
                serAzotCompH_1 = "0", serTelH_1 = "0", serBalcH_1 = "0",
                serBarbacH_1 = "0", serTerrH_1 = "0", serPatH_1 = "0",
                serPortH_1 = "0", serJardH_1 = "0", serPiscH_1 = "0",
                serGaraH_1 = "0", serPosGaraH_1 = "0", serElevH_1 = "0",
                serPuntAltH_1 = "0", serGasBalH_1 = "0", serGasCallH_1 = "0",
                serAg24HH_1 = "0", serCarpH_1 = "0";

        // Check for existence of substrings
        // THERE ARE MORE, LIKE TELEFONO and sala-comedor
        //<editor-fold>
        if (serS.contains("sala-comedor")) {
            serSalaComH_1 = "1";
        }
        if (serS.contains("sala ")) {
            serSalaH_1 = "1";
        }
        if (serS.contains(" comedor")) {
            serComedorH_1 = "1";
        }
        if (serS.contains("cocina")) {
            serCocinaH_1 = "1";
        }
        if (serS.contains("cocina-comedor")) {
            serCocinaComH_1 = "1";
        }
        if (serS.contains("azotea libre")) {
            serAzotLibH_1 = "1";
        }
        if (serS.contains("azotea compartida")) {
            serAzotCompH_1 = "1";
        }
        if (serS.contains("teléfono")) {
            serTelH_1 = "1";
        }
        if (serS.contains("balcón")) {
            serBalcH_1 = "1";
        }
        if (serS.contains("barbacoa")) {
            serBarbacH_1 = "1";
        }
        if (serS.contains("terraza")) {
            serTerrH_1 = "1";
        }
        if (serS.contains("patio")) {
            serPatH_1 = "1";
        }
        if (serS.contains("portal")) {
            serPortH_1 = "1";
        }
        if (serS.contains("jardín")) {
            serJardH_1 = "1";
        }
        if (serS.contains("piscina")) {
            serPiscH_1 = "1";
        }
        if (serS.contains("garaje")) {
            serGaraH_1 = "1";
        }
        if (serS.contains("posibilidad de garaje")) {
            serPosGaraH_1 = "1";
        }
        if (serS.contains("elevador")) {
            serElevH_1 = "1";
        }
        if (serS.contains("puntal alto")) {
            serPuntAltH_1 = "1";
        }
        if (serS.contains("gas de balón")) {
            serGasBalH_1 = "1";
        }
        if (serS.contains("gas de la calle")) {
            serGasCallH_1 = "1";
        }
        if (serS.contains("agua las 24 horas")) {
            serAg24HH_1 = "1";
        }
        if (serS.contains("carposhe")) {
            serCarpH_1 = "1";
        }
        //</editor-fold>


        //</editor-fold>

        //4.- Direccion
        //<editor-fold>
        FilterBean dirF = new FilterBean();
        HasAttributeFilter dirA = new HasAttributeFilter("id", "ctl01_LabelDireccionLugar");
        HasAttributeFilter dirA1 = new HasAttributeFilter("id", "ctl02_LabelDireccionLugar");
        OrFilter dirOR = new OrFilter(dirA, dirA1);
        dirF.setFilters(new NodeFilter[]{dirOR});
        dirF.setURL(theURL);
        String derS = dirF.getText();
        derS = derS.replace("DIRECCIÓN: ", "");
        derS = derS.replace("DIRECCIÓN:", "");
        if (derS.equals("")) {
            derS = "0";
        }
        //</editor-fold>

        //5.- Municipio
        //<editor-fold>
        FilterBean munF = new FilterBean();
        HasAttributeFilter munA = new HasAttributeFilter("id", "ctl01_LabelMunicipio");
        HasAttributeFilter munA1 = new HasAttributeFilter("id", "ctl02_LabelMunicipio");
        OrFilter munOR = new OrFilter(munA, munA1);
        munF.setFilters(new NodeFilter[]{munOR});
        munF.setURL(theURL);
        String munS = munF.getText();
        //System.out.println("RAW MUNS" + munS);
        String munCode = "0";
        String habDummy = "0";
        if (munS.contains("Habana")) {
            habDummy = "1";
        }

        if (!munS.equals("")) {
            munS = munS.split(",")[0] + munS.split(",")[1];
            //System.out.println("THE TWO SPLITS" + munS);
            munS = munS.replace(". ", "");
            munS = munS.replace(".  ", "");
            munS = munS.replace(". ", "");
            munS = accentFix(munS).toLowerCase().replace(",", "");
            //System.out.println("MUNICIPIO CODE ACCENTFIXED =" + munS);
            try {
                if (hasht2.get(munS) != null) {
                    //System.out.println("Municipio Code Found!!");
                    munCode = hasht2.get(munS);
                } else {/*
                     * System.out.println("not found:" + munS);
                     */

                }
            } catch (NullPointerException np1) {
            }
        }
        //System.out.println("MUNSSSSSS"+munS);
        //</editor-fold>

        //6.- Direccion Details
        //<editor-fold>
        FilterBean dirdF = new FilterBean();
        HasAttributeFilter dirdA = new HasAttributeFilter("id", "ctl01_LabelObservacionesDireccion");
        HasAttributeFilter dirdA1 = new HasAttributeFilter("id", "ctl02_LabelObservacionesDireccion");
        OrFilter dirdOR = new OrFilter(dirdA, dirdA1);
        dirdF.setFilters(new NodeFilter[]{dirdOR});
        dirdF.setURL(theURL);
        String dirdS = dirdF.getText();
        dirdS = dirdS.replace("OBSERVACIONES PARA LA DIRECCIÓN: ", "");
        //</editor-fold>

        //7.- Quality of Unit (TENGO)
        //<editor-fold>
        FilterBean quaUTF = new FilterBean();
        HasAttributeFilter quaUTA = new HasAttributeFilter("id", "ctl01_LabelEstadoVivienda");
        HasAttributeFilter quaUTA1 = new HasAttributeFilter("id", "ctl02_LabelEstadoVivienda");
        OrFilter quaUTOR = new OrFilter(quaUTA, quaUTA1);
        quaUTF.setFilters(new NodeFilter[]{quaUTOR});
        quaUTF.setURL(theURL);
        String quaUTS = quaUTF.getText();
        quaUTS = quaUTS.replace("ESTADO FÍSICO DE LA VIVIENDA: ", "");
        quaUTS = qualityreplacer(quaUTS);
        //</editor-fold>

        //8.- Quality of Building (TENGO)
        //<editor-fold>
        FilterBean quaBTF = new FilterBean();
        HasAttributeFilter quaBTA = new HasAttributeFilter("id", "ctl01_LabelEstadoEdificio");
        HasAttributeFilter quaBTA1 = new HasAttributeFilter("id", "ctl02_LabelEstadoEdificio");
        OrFilter quaBTAOR = new OrFilter(quaBTA, quaBTA1);
        quaBTF.setFilters(new NodeFilter[]{quaBTAOR});
        quaBTF.setURL(theURL);
        String quaBTS = quaBTF.getText();
        quaBTS = quaBTS.replace("ESTADO FÍSICO DE LA EDIFICACIÓN:", "");
        String repairdummy1 = "0";
        if (quaBTS.contains("reparable")) {
            repairdummy1 = "1";
        }
        quaBTS = qualityreplacer(quaBTS);

        //</editor-fold>

        //9.- Cantidad de personas (TENGO)
        //<editor-fold>
        FilterBean cperF_1 = new FilterBean();
        HasAttributeFilter cperA_1 = new HasAttributeFilter("id", "ctl01_LabelCantPersonas");
        HasAttributeFilter cperA_11 = new HasAttributeFilter("id", "ctl02_LabelCantPersonas");
        OrFilter cperA_1OR = new OrFilter(cperA_1, cperA_11);
        cperF_1.setFilters(new NodeFilter[]{cperA_1OR});
        cperF_1.setURL(theURL);
        String cperS_1 = cperF_1.getText();
        cperS_1 = cperS_1.replace("CANTIDAD DE PERSONAS QUE PUEDEN VIVIR CÓMODAMENTE: ", "");


        //</editor-fold>

        //10.- Further Observations
        //<editor-fold>
        FilterBean fobsF = new FilterBean();
        HasAttributeFilter fobsA = new HasAttributeFilter("id", "ctl01_LabelObservaciones");
        HasAttributeFilter fobsA1 = new HasAttributeFilter("id", "ctl02_LabelObservaciones");
        OrFilter fobs_OR = new OrFilter(fobsA, fobsA1);
        fobsF.setFilters(new NodeFilter[]{fobs_OR});
        fobsF.setURL(theURL);
        String fobsS = fobsF.getText();
        fobsS = fobsS.replace("OTRAS OBSERVACIONES SOBRE LA VIVIENDA: ", "");
        //</editor-fold>

        //</editor-fold>

        //TENGO 2
        //<editor-fold>
        //1.- Location & tipo
        //<editor-fold>
        FilterBean locF2 = new FilterBean();
        HasAttributeFilter locA2 = new HasAttributeFilter("id", "ctl01_LabelTitulo2");
        HasAttributeFilter locA21 = new HasAttributeFilter("id", "ctl02_LabelTitulo2");
        OrFilter locA2OR = new OrFilter(locA2, locA21);
        locF2.setFilters(new NodeFilter[]{locA2OR});
        locF2.setURL(theURL);
        String hLocS2 = locF2.getText();
        hLocS2 = fixer.accentFix(hLocS2);
        hLocS2 = hLocS2.replace("DIRECCIÓN", "");

        FilterBean tipolocF_2 = new FilterBean();
        HasAttributeFilter tipolocA_2 = new HasAttributeFilter("id", "ctl01_LabelTipoLocal2");
        HasAttributeFilter tipolocA_21 = new HasAttributeFilter("id", "ctl02_LabelTipoLocal2");
        OrFilter tipolocOR = new OrFilter(tipolocA_2, tipolocA_21);
        tipolocF_2.setFilters(new NodeFilter[]{tipolocOR});
        tipolocF_2.setURL(theURL);
        String htipLocS_2 = tipolocF_2.getText();
        htipLocS_2 = htipLocS_2.replace(",", "");
        htipLocS_2 = fixer.accentFix(htipLocS_2);

        //</editor-fold>

        //2.- Habitaciones
        //<editor-fold>
        FilterBean habF2 = new FilterBean();
        HasAttributeFilter habA2 = new HasAttributeFilter("id", "ctl01_LabelHabitaciones2");
        HasAttributeFilter habA21 = new HasAttributeFilter("id", "ctl02_LabelHabitaciones2");
        OrFilter habOR = new OrFilter(habA2, habA21);
        habF2.setFilters(new NodeFilter[]{habOR});
        habF2.setURL(theURL);
        String habS2 = habF2.getText();
        if (habS2.endsWith(",")) {
            habS2 = habS2.substring(0, habS2.length() - 1);
        }
        habS2 = habS2.replace("½", ".5");


        String roomHD2 = "0";
        String bathrHD2 = "0";

        String habSEdited2 = habS2;
        habSEdited2 = habSEdited2.replace("cuartos", "");
        habSEdited2 = habSEdited2.replace("cuarto", "");
        habSEdited2 = habSEdited2.replace("baños", "");
        habSEdited2 = habSEdited2.replace("baño", "");
        habSEdited2 = habSEdited2.trim();

        String[] habSEditedArr2 = habSEdited2.split(",");
        if (habSEditedArr2.length > 1) {
            roomHD2 = habSEditedArr2[0];
            bathrHD2 = habSEditedArr2[1];
        }
        //</editor-fold>

        //3.- Servicios
        //<editor-fold>
        FilterBean serF_2 = new FilterBean();
        HasAttributeFilter serA_2 = new HasAttributeFilter("id", "ctl01_LabelServicios2");
        HasAttributeFilter serA_21 = new HasAttributeFilter("id", "ctl02_LabelServicios2");
        OrFilter serOR = new OrFilter(serA_2, serA_21);
        serF_2.setFilters(new NodeFilter[]{serOR});
        serF_2.setURL(theURL);
        String serS_2 = serF_2.getText();

        //Initialize Strings to "zero"
        String serSalaComH_2 = "0", serSalaH_2 = "0", serComedorH_2 = "0",
                serCocinaH_2 = "0", serCocinaComH_2 = "0", serAzotLibH_2 = "0",
                serAzotCompH_2 = "0", serTelH_2 = "0", serBalcH_2 = "0",
                serBarbacH_2 = "0", serTerrH_2 = "0", serPatH_2 = "0",
                serPortH_2 = "0", serJardH_2 = "0", serPiscH_2 = "0",
                serGaraH_2 = "0", serPosGaraH_2 = "0", serElevH_2 = "0",
                serPuntAltH_2 = "0", serGasBalH_2 = "0", serGasCallH_2 = "0",
                serAg24HH_2 = "0", serCarpH_2 = "0";

        // Check for existence of substrings
        // THERE ARE MORE, LIKE TELEFONO and sala-comedor
        //<editor-fold>
        if (serS_2.contains("sala-comedor")) {
            serSalaComH_2 = "1";
        }
        if (serS_2.contains("sala")) {
            serSalaH_2 = "1";
        }
        if (serS_2.contains(" comedor")) {
            serComedorH_2 = "1";
        }
        if (serS_2.contains("cocina")) {
            serCocinaH_2 = "1";
        }
        if (serS_2.contains("cocina-comedor")) {
            serCocinaComH_2 = "1";
        }
        if (serS_2.contains("azotea libre")) {
            serAzotLibH_2 = "1";
        }
        if (serS_2.contains("azotea compartida")) {
            serAzotCompH_2 = "1";
        }
        if (serS_2.contains("teléfono")) {
            serTelH_2 = "1";
        }
        if (serS_2.contains("balcón")) {
            serBalcH_2 = "1";
        }
        if (serS_2.contains("barbacoa")) {
            serBarbacH_2 = "1";
        }
        if (serS_2.contains("terraza")) {
            serTerrH_2 = "1";
        }
        if (serS_2.contains("patio")) {
            serPatH_2 = "1";
        }
        if (serS_2.contains("portal")) {
            serPortH_2 = "1";
        }
        if (serS_2.contains("jardín")) {
            serJardH_2 = "1";
        }
        if (serS_2.contains("piscina")) {
            serPiscH_2 = "1";
        }
        if (serS_2.contains("garaje")) {
            serGaraH_2 = "1";
        }
        if (serS_2.contains("posibilidad de garaje")) {
            serPosGaraH_2 = "1";
        }
        if (serS_2.contains("elevador")) {
            serElevH_2 = "1";
        }
        if (serS_2.contains("puntal alto")) {
            serPuntAltH_2 = "1";
        }
        if (serS_2.contains("gas de balón")) {
            serGasBalH_2 = "1";
        }
        if (serS_2.contains("gas de la calle")) {
            serGasCallH_2 = "1";
        }
        if (serS_2.contains("agua las 24 horas")) {
            serAg24HH_2 = "1";
        }
        if (serS_2.contains("carposhe")) {
            serCarpH_2 = "1";
        }
        //</editor-fold>

        //</editor-fold>

        //4.- Direccion
        //<editor-fold>
        FilterBean dirF2 = new FilterBean();
        HasAttributeFilter dirA2 = new HasAttributeFilter("id", "ctl01_LabelDireccionLugar2");
        HasAttributeFilter dirA21 = new HasAttributeFilter("id", "ctl02_LabelDireccionLugar2");
        OrFilter dirAOR = new OrFilter(dirA2, dirA21);
        dirF2.setFilters(new NodeFilter[]{dirAOR});
        dirF2.setURL(theURL);
        String derS2 = dirF2.getText();
        derS2 = derS2.replace("DIRECCIÓN: ", "");
        derS2 = derS2.replace("DIRECCIÓN:", "");
        //if (derS2.equals(""))
        //  derS2 = "0";
        //</editor-fold>

        //5.- Municipio
        //<editor-fold>
        FilterBean munF2 = new FilterBean();
        HasAttributeFilter munA2 = new HasAttributeFilter("id", "ctl01_LabelMunicipio2");
        HasAttributeFilter munA21 = new HasAttributeFilter("id", "ctl02_LabelMunicipio2");
        OrFilter munA2OR = new OrFilter(munA2, munA21);
        munF2.setFilters(new NodeFilter[]{munA2OR});
        munF2.setURL(theURL);
        String munS2 = munF2.getText();
        String habDummy2 = "0";
        if (munS2.contains("Habana")) {
            habDummy2 = "1";
        }

        String munCode2 = "0";
        if (!munS2.equals("")) {
            munS2 = munS2.split(",")[0] + munS2.split(",")[1];
            munS2 = munS2.replace(".  ", "");
            munS2 = munS2.replace(". ", "");
            munS2 = accentFix(munS2).toLowerCase().replace(",", "");
            try {
                if (hasht2.get(munS2) != null) {
                    //System.out.println("Municipio Code Found!!");
                    munCode2 = hasht2.get(munS2);
                } else {/*
                     * System.out.println("not found:" + munS2);
                     */

                }
            } catch (NullPointerException np1) {
            }
        }

        //System.out.println("MUNSSSSSS2"+munS2);
        //</editor-fold>

        //6.- Direccion Details
        //<editor-fold>
        FilterBean dirdF2 = new FilterBean();
        HasAttributeFilter dirdA2 = new HasAttributeFilter("id", "ctl01_LabelObservacionesDireccion2");
        HasAttributeFilter dirdA21 = new HasAttributeFilter("id", "ctl02_LabelObservacionesDireccion2");
        OrFilter dirdA2OR = new OrFilter(dirdA2, dirdA21);
        dirdF2.setFilters(new NodeFilter[]{dirdA2OR});
        dirdF2.setURL(theURL);
        String dirdS2 = dirdF2.getText();
        dirdS2 = dirdS2.replace("OBSERVACIONES PARA LA DIRECCIÓN: ", "");
        //</editor-fold>

        //7.- Quality of Unit (TENGO)
        //<editor-fold>
        FilterBean quaUTF2 = new FilterBean();
        HasAttributeFilter quaUTA2 = new HasAttributeFilter("id", "ctl01_LabelEstadoVivienda2");
        HasAttributeFilter quaUTA21 = new HasAttributeFilter("id", "ctl02_LabelEstadoVivienda2");
        OrFilter quaUTAOR = new OrFilter(quaUTA2, quaUTA21);
        quaUTF2.setFilters(new NodeFilter[]{quaUTAOR});
        quaUTF2.setURL(theURL);
        String quaUTS2 = quaUTF2.getText();
        quaUTS2 = quaUTS2.replace("ESTADO FÍSICO DE LA VIVIENDA: ", "");
        quaUTS2 = qualityreplacer(quaUTS2);

        //</editor-fold>

        //8.- Quality of Building (TENGO)
        //<editor-fold>
        FilterBean quaBTF2 = new FilterBean();
        HasAttributeFilter quaBTA2 = new HasAttributeFilter("id", "ctl01_LabelEstadoEdificio2");
        HasAttributeFilter quaBTA21 = new HasAttributeFilter("id", "ctl02_LabelEstadoEdificio2");
        OrFilter quaBTA2OR = new OrFilter(quaBTA2, quaBTA21);
        quaBTF2.setFilters(new NodeFilter[]{quaBTA2OR});
        quaBTF2.setURL(theURL);
        String quaBTS2 = quaBTF2.getText();
        quaBTS2 = quaBTS2.replace("ESTADO FÍSICO DE LA EDIFICACIÓN:", "");
        String repairdummy2 = "0";
        if (quaBTS2.contains("reparable")) {
            repairdummy2 = "1";
        }
        quaBTS2 = qualityreplacer(quaBTS2);

        //</editor-fold>

        //9.- Cantidad de personas (TENGO)
        //<editor-fold>
        FilterBean cperF_2 = new FilterBean();
        HasAttributeFilter cperA_2 = new HasAttributeFilter("id", "ctl01_LabelCantPersonas2");
        HasAttributeFilter cperA_21 = new HasAttributeFilter("id", "ctl02_LabelCantPersonas2");
        OrFilter cperA_2OR = new OrFilter(cperA_2, cperA_21);
        cperF_2.setFilters(new NodeFilter[]{cperA_2OR});
        cperF_2.setURL(theURL);
        String cperS_2 = cperF_2.getText();
        cperS_2 = cperS_2.replace("CANTIDAD DE PERSONAS QUE PUEDEN VIVIR CÓMODAMENTE: ", "");


        //</editor-fold>

        //10.- Further Observations
        //<editor-fold>
        FilterBean fobsF2 = new FilterBean();
        HasAttributeFilter fobsA2 = new HasAttributeFilter("id", "ctl01_LabelObservaciones2");
        HasAttributeFilter fobsA21 = new HasAttributeFilter("id", "ctl02_LabelObservaciones2");
        OrFilter fobsA2OR = new OrFilter(fobsA2, fobsA21);
        fobsF2.setFilters(new NodeFilter[]{fobsA2OR});
        fobsF2.setURL(theURL);
        String fobsS2 = fobsF2.getText();
        fobsS2 = fobsS2.replace("OTRAS OBSERVACIONES SOBRE LA VIVIENDA: ", "");

        if (hLocS2.equals("") && htipLocS_2.equals("") && roomHD2.equals("") && bathrHD2.equals("")
                && serSalaComH_2.equals("0") && serSalaH_2.equals("0") && serComedorH_2.equals("0") && serCocinaH_2.equals("0")
                && serAzotLibH_2.equals("0") && serAzotCompH_2.equals("0") && serTelH_2.equals("0") && serBalcH_2.equals("0")
                && serBarbacH_2.equals("0") && serTerrH_2.equals("0") && serPatH_2.equals("0") && serPortH_2.equals("0")
                && serJardH_2.equals("0") && serPiscH_2.equals("0") && serGaraH_2.equals("0") && serPosGaraH_2.equals("0")
                && serElevH_2.equals("0") && serPuntAltH_2.equals("0") && serGasBalH_2.equals("0") && serGasCallH_2.equals("0")
                && serAg24HH_2.equals("0") && serCarpH_2.equals("0") && derS2.equals("") && munS2.equals("")
                && dirdS2.equals("") && quaUTS2.equals("") && quaBTS.equals("") && fobsS2.equals("")) {
            hLocS2 = "-";
            htipLocS_2 = "-";
            roomHD2 = "-";
            bathrHD2 = "-";
            serSalaComH_2 = "-";
            serSalaH_2 = "-";
            serComedorH_2 = "-";
            serCocinaH_2 = "-";
            serAzotLibH_2 = "-";
            serAzotCompH_2 = "-";
            serTelH_2 = "-";
            serBalcH_2 = "-";
            serBarbacH_2 = "-";
            serTerrH_2 = "-";
            serPatH_2 = "-";
            serPortH_2 = "-";
            serJardH_2 = "-";
            serPiscH_2 = "-";
            serGaraH_2 = "-";
            serPosGaraH_2 = "-";
            serElevH_2 = "-";
            serPuntAltH_2 = "-";
            serGasBalH_2 = "-";
            serGasCallH_2 = "-";
            serAg24HH_2 = "-";
            serCarpH_2 = "-";
            derS2 = "-";
            munS2 = "-";
            dirdS2 = "-";
            quaUTS2 = "-";
            quaBTS = "-";
            fobsS2 = "-";
        }
        //</editor-fold>
        //</editor-fold>

        //TENGO 3
        //<editor-fold>
        //1.- Location & tipo
        //<editor-fold>
        FilterBean locF3 = new FilterBean();
        HasAttributeFilter locA3 = new HasAttributeFilter("id", "ctl01_LabelTitulo3");
        HasAttributeFilter locA31 = new HasAttributeFilter("id", "ctl02_LabelTitulo3");
        OrFilter locA3OR = new OrFilter(locA3, locA31);
        locF3.setFilters(new NodeFilter[]{locA3OR});
        locF3.setURL(theURL);
        String hLocS3 = locF3.getText();
        hLocS3 = fixer.accentFix(hLocS3);
        hLocS3 = hLocS3.replace("DIRECCIÓN", "");

        FilterBean tipolocF_3 = new FilterBean();
        HasAttributeFilter tipolocA_3 = new HasAttributeFilter("id", "ctl01_LabelTipoLocal3");
        HasAttributeFilter tipolocA_31 = new HasAttributeFilter("id", "ctl02_LabelTipoLocal3");
        OrFilter tipolocA_3OR = new OrFilter(tipolocA_3, tipolocA_31);
        tipolocF_3.setFilters(new NodeFilter[]{tipolocA_3OR});
        tipolocF_3.setURL(theURL);
        String htipLocS_3 = tipolocF_3.getText();
        htipLocS_3 = htipLocS_3.replace(",", "");
        htipLocS_3 = fixer.accentFix(htipLocS_3);
        //</editor-fold>

        //2.- Habitaciones
        //<editor-fold>
        FilterBean habF3 = new FilterBean();
        HasAttributeFilter habA3 = new HasAttributeFilter("id", "ctl01_LabelHabitaciones3");
        HasAttributeFilter habA31 = new HasAttributeFilter("id", "ctl02_LabelHabitaciones3");
        OrFilter habA3OR = new OrFilter(habA3, habA31);
        habF3.setFilters(new NodeFilter[]{habA3OR});
        habF3.setURL(theURL);
        String habS3 = habF3.getText();
        if (habS3.endsWith(",")) {
            habS3 = habS3.substring(0, habS3.length() - 1);
        }
        habS3 = habS3.replace("½", ".5");


        String roomHD3 = "0";
        String bathrHD3 = "0";

        String habSEdited3 = habS3;
        habSEdited3 = habSEdited3.replace("cuartos", "");
        habSEdited3 = habSEdited3.replace("cuarto", "");
        habSEdited3 = habSEdited3.replace("baños", "");
        habSEdited3 = habSEdited3.replace("baño", "");
        habSEdited3 = habSEdited3.trim();

        String[] habSEditedArr3 = habSEdited3.split(",");
        if (habSEditedArr3.length > 1) {
            roomHD3 = habSEditedArr3[0];
            bathrHD3 = habSEditedArr3[1];
        }
        //</editor-fold>

        //3.- Servicios
        //<editor-fold>
        FilterBean serF_3 = new FilterBean();
        HasAttributeFilter serA_3 = new HasAttributeFilter("id", "ctl01_LabelServicios3");
        HasAttributeFilter serA_31 = new HasAttributeFilter("id", "ctl02_LabelServicios3");
        OrFilter serA_3OR = new OrFilter(serA_3, serA_31);
        serF_3.setFilters(new NodeFilter[]{serA_3OR});
        serF_3.setURL(theURL);
        String serS_3 = serF_3.getText();

        //Initialize Strings to "zero"
        String serSalaComH_3 = "0", serSalaH_3 = "0", serComedorH_3 = "0",
                serCocinaH_3 = "0", serCocinaComH_3 = "0", serAzotLibH_3 = "0",
                serAzotCompH_3 = "0", serTelH_3 = "0", serBalcH_3 = "0",
                serBarbacH_3 = "0", serTerrH_3 = "0", serPatH_3 = "0",
                serPortH_3 = "0", serJardH_3 = "0", serPiscH_3 = "0",
                serGaraH_3 = "0", serPosGaraH_3 = "0", serElevH_3 = "0",
                serPuntAltH_3 = "0", serGasBalH_3 = "0", serGasCallH_3 = "0",
                serAg24HH_3 = "0", serCarpH_3 = "0";

        // Check for existence of substrings
        // THERE ARE MORE, LIKE TELEFONO and sala-comedor
        //<editor-fold>
        if (serS_3.contains("sala-comedor")) {
            serSalaComH_3 = "1";
        }
        if (serS_3.contains("sala")) {
            serSalaH_3 = "1";
        }
        if (serS_3.contains(" comedor")) {
            serComedorH_3 = "1";
        }
        if (serS_3.contains("cocina")) {
            serCocinaH_3 = "1";
        }
        if (serS_3.contains("cocina-comedor")) {
            serCocinaComH_3 = "1";
        }
        if (serS_3.contains("azotea libre")) {
            serAzotLibH_3 = "1";
        }
        if (serS_3.contains("azotea compartida")) {
            serAzotCompH_3 = "1";
        }
        if (serS_3.contains("teléfono")) {
            serTelH_3 = "1";
        }
        if (serS_3.contains("balcón")) {
            serBalcH_3 = "1";
        }
        if (serS_3.contains("barbacoa")) {
            serBarbacH_3 = "1";
        }
        if (serS_3.contains("terraza")) {
            serTerrH_3 = "1";
        }
        if (serS_3.contains("patio")) {
            serPatH_3 = "1";
        }
        if (serS_3.contains("portal")) {
            serPortH_3 = "1";
        }
        if (serS_3.contains("jardín")) {
            serJardH_3 = "1";
        }
        if (serS_3.contains("piscina")) {
            serPiscH_3 = "1";
        }
        if (serS_3.contains("garaje")) {
            serGaraH_3 = "1";
        }
        if (serS_3.contains("posibilidad de garaje")) {
            serPosGaraH_3 = "1";
        }
        if (serS_3.contains("elevador")) {
            serElevH_3 = "1";
        }
        if (serS_3.contains("puntal alto")) {
            serPuntAltH_3 = "1";
        }
        if (serS_3.contains("gas de balón")) {
            serGasBalH_3 = "1";
        }
        if (serS_3.contains("gas de la calle")) {
            serGasCallH_3 = "1";
        }
        if (serS_3.contains("agua las 24 horas")) {
            serAg24HH_3 = "1";
        }
        if (serS_3.contains("carposhe")) {
            serCarpH_3 = "1";
        }
        //</editor-fold>

        //</editor-fold>

        //4.- Direccion
        //<editor-fold>
        FilterBean dirF3 = new FilterBean();
        HasAttributeFilter dirA3 = new HasAttributeFilter("id", "ctl01_LabelDireccionLugar3");
        HasAttributeFilter dirA31 = new HasAttributeFilter("id", "ctl02_LabelDireccionLugar3");
        OrFilter dirA3OR = new OrFilter(dirA3, dirA31);
        dirF3.setFilters(new NodeFilter[]{dirA3OR});
        dirF3.setURL(theURL);
        String derS3 = dirF3.getText();
        derS3 = derS3.replace("DIRECCIÓN: ", "");
        derS3 = derS3.replace("DIRECCIÓN:", "");
        if (derS3.equals("")) {
            derS3 = "0";
        }
        //</editor-fold>

        //5.- Municipio
        //<editor-fold>
        FilterBean munF3 = new FilterBean();
        HasAttributeFilter munA3 = new HasAttributeFilter("id", "ctl01_LabelMunicipio3");
        HasAttributeFilter munA31 = new HasAttributeFilter("id", "ctl02_LabelMunicipio3");
        OrFilter munA3OR = new OrFilter(munA3, munA31);
        munF3.setFilters(new NodeFilter[]{munA3OR});
        munF3.setURL(theURL);
        String munS3 = munF3.getText();
        String munCode3 = "0";
        String habDummy3 = "0";
        if (munS3.contains("Habana")) {
            habDummy3 = "1";
        }

        if (!munS3.equals("")) {
            munS3 = munS3.split(",")[0] + munS3.split(",")[1];
            munS3 = munS3.replace("Ciudad Habana,", "");
            munS3 = munS3.replace("Habana,", "");
            munS3 = munS3.replace(".  ", "");
            munS3 = munS3.replace(". ", "");
            munS3 = accentFix(munS3).toLowerCase().replace(",", "");
            try {
                if (hasht2.get(munS3) != null) {
                    //System.out.println("Municipio Code Found!!");
                    munCode3 = hasht2.get(munS3);
                } else {/*
                     * System.out.println("not found:" + munS3);
                     */

                }
            } catch (NullPointerException np1) {
            }

        }
        //System.out.println("MUNSSSSSS3"+munS3);
        //</editor-fold>

        //6.- Direccion Details
        //<editor-fold>
        FilterBean dirdF3 = new FilterBean();
        HasAttributeFilter dirdA3 = new HasAttributeFilter("id", "ctl01_LabelObservacionesDireccion3");
        HasAttributeFilter dirdA31 = new HasAttributeFilter("id", "ctl02_LabelObservacionesDireccion3");
        OrFilter dirdA3OR = new OrFilter(dirdA3, dirdA31);
        dirdF3.setFilters(new NodeFilter[]{dirdA3OR});
        dirdF3.setURL(theURL);
        String dirdS3 = dirdF3.getText();
        dirdS3 = dirdS3.replace("OBSERVACIONES PARA LA DIRECCIÓN: ", "");
        //</editor-fold>

        //7.- Quality of Unit (TENGO)
        //<editor-fold>
        FilterBean quaUTF3 = new FilterBean();
        HasAttributeFilter quaUTA3 = new HasAttributeFilter("id", "ctl01_LabelEstadoVivienda3");
        HasAttributeFilter quaUTA31 = new HasAttributeFilter("id", "ctl02_LabelEstadoVivienda3");
        OrFilter quaUTA3OR = new OrFilter(quaUTA3, quaUTA31);
        quaUTF3.setFilters(new NodeFilter[]{quaUTA3OR});
        quaUTF3.setURL(theURL);
        String quaUTS3 = quaUTF3.getText();
        quaUTS3 = quaUTS3.replace("ESTADO FÍSICO DE LA VIVIENDA: ", "");
        quaUTS3 = qualityreplacer(quaUTS3);

        //</editor-fold>

        //8.- Quality of Building (TENGO)
        //<editor-fold>
        FilterBean quaBTF3 = new FilterBean();
        HasAttributeFilter quaBTA3 = new HasAttributeFilter("id", "ctl01_LabelEstadoEdificio3");
        HasAttributeFilter quaBTA31 = new HasAttributeFilter("id", "ctl02_LabelEstadoEdificio3");
        OrFilter quaBTA3OR = new OrFilter(quaBTA3, quaBTA31);
        quaBTF3.setFilters(new NodeFilter[]{quaBTA3OR});
        quaBTF3.setURL(theURL);
        String quaBTS3 = quaBTF3.getText();
        quaBTS3 = quaBTS3.replace("ESTADO FÍSICO DE LA EDIFICACIÓN:", "");
        String repairdummy3 = "0";
        if (quaBTS3.contains("reparable")) {
            repairdummy3 = "1";
        }
        quaBTS3 = qualityreplacer(quaBTS3);


        //</editor-fold>

        //9.- Cantidad de personas (TENGO)
        //<editor-fold>
        FilterBean cperF_3 = new FilterBean();
        HasAttributeFilter cperA_3 = new HasAttributeFilter("id", "ctl01_LabelCantPersonas3");
        HasAttributeFilter cperA_31 = new HasAttributeFilter("id", "ctl02_LabelCantPersonas3");
        OrFilter cperA_3OR = new OrFilter(cperA_3, cperA_31);
        cperF_3.setFilters(new NodeFilter[]{cperA_3OR});
        cperF_3.setURL(theURL);
        String cperS_3 = cperF_3.getText();
        cperS_3 = cperS_3.replace("CANTIDAD DE PERSONAS QUE PUEDEN VIVIR CÓMODAMENTE: ", "");


        //</editor-fold>
        //9.- Further Observations
        //<editor-fold>
        FilterBean fobsF3 = new FilterBean();
        HasAttributeFilter fobsA3 = new HasAttributeFilter("id", "ctl01_LabelObservaciones3");
        HasAttributeFilter fobsA31 = new HasAttributeFilter("id", "ctl02_LabelObservaciones3");
        OrFilter fobsA3OR = new OrFilter(fobsA3, fobsA3);
        fobsF3.setFilters(new NodeFilter[]{fobsA3OR});
        fobsF3.setURL(theURL);
        String fobsS3 = fobsF3.getText();
        fobsS3 = fobsS3.replace("OTRAS OBSERVACIONES SOBRE LA VIVIENDA: ", "");

        if (hLocS3.equals("") && htipLocS_3.equals("") && roomHD3.equals("") && bathrHD3.equals("")
                && serSalaComH_3.equals("0") && serSalaH_3.equals("0") && serComedorH_3.equals("0") && serCocinaH_3.equals("0")
                && serAzotLibH_3.equals("0") && serAzotCompH_3.equals("0") && serTelH_3.equals("0") && serBalcH_3.equals("0")
                && serBarbacH_3.equals("0") && serTerrH_3.equals("0") && serPatH_3.equals("0") && serPortH_3.equals("0")
                && serJardH_3.equals("0") && serPiscH_3.equals("0") && serGaraH_3.equals("0") && serPosGaraH_3.equals("0")
                && serElevH_3.equals("0") && serPuntAltH_3.equals("0") && serGasBalH_3.equals("0") && serGasCallH_3.equals("0")
                && serAg24HH_3.equals("0") && serCarpH_3.equals("0") && derS3.equals("") && munS3.equals("")
                && dirdS3.equals("") && quaUTS3.equals("") && quaBTS.equals("") && fobsS3.equals("")) {
            hLocS3 = "-";
            htipLocS_3 = "-";
            roomHD3 = "-";
            bathrHD3 = "-";
            serSalaComH_3 = "-";
            serSalaH_3 = "-";
            serComedorH_3 = "-";
            serCocinaH_3 = "-";
            serAzotLibH_3 = "-";
            serAzotCompH_3 = "-";
            serTelH_3 = "-";
            serBalcH_3 = "-";
            serBarbacH_3 = "-";
            serTerrH_3 = "-";
            serPatH_3 = "-";
            serPortH_3 = "-";
            serJardH_3 = "-";
            serPiscH_3 = "-";
            serGaraH_3 = "-";
            serPosGaraH_3 = "-";
            serElevH_3 = "-";
            serPuntAltH_3 = "-";
            serGasBalH_3 = "-";
            serGasCallH_3 = "-";
            serAg24HH_3 = "-";
            serCarpH_3 = "-";
            derS3 = "-";
            munS3 = "-";
            dirdS3 = "-";
            quaUTS3 = "-";
            quaBTS = "-";
            fobsS3 = "-";
        }
        //</editor-fold>
        //</editor-fold>

        //QUIERO
        //<editor-fold>
        //1.- Type
        //<editor-fold>
        FilterBean qtypeF = new FilterBean();
        HasAttributeFilter qtypeA = new HasAttributeFilter("id", "ctl01_LabelTipoLocal4");
        HasAttributeFilter qtypeA1 = new HasAttributeFilter("id", "ctl02_LabelTipoLocal4");
        OrFilter qtypeAOR = new OrFilter(qtypeA, qtypeA1);
        qtypeF.setFilters(new NodeFilter[]{qtypeAOR});
        qtypeF.setURL(theURL);
        String qtypeS = qtypeF.getText();
        //</editor-fold>

        //2.- Rooms

        //<editor-fold>
        FilterBean qroomF = new FilterBean();
        HasAttributeFilter qroomA = new HasAttributeFilter("id", "ctl01_LabelHabitaciones4");
        HasAttributeFilter qroomA1 = new HasAttributeFilter("id", "ctl02_LabelHabitaciones4");
        OrFilter qroomAOR = new OrFilter(qroomA, qroomA1);
        qroomF.setFilters(new NodeFilter[]{qroomAOR});
        qroomF.setURL(theURL);
        String qroomS = qroomF.getText();
        qroomS = accentFix(qroomS);
        //</editor-fold>


        //3.- Services
        //<editor-fold>
        FilterBean qserF = new FilterBean();
        HasAttributeFilter qserA = new HasAttributeFilter("id", "ctl01_LabelServicios4");
        HasAttributeFilter qserA1 = new HasAttributeFilter("id", "ctl02_LabelServicios4");
        OrFilter qserAOR = new OrFilter(qserA, qserA1);
        qserF.setFilters(new NodeFilter[]{qserAOR});
        qserF.setURL(theURL);
        String qserS = qserF.getText();
        String qserSalaComW_1 = "0", qserSalaW_1 = "0", serComedorW_1 = "0",
                serCocinaW_1 = "0", serCocinaComW_1 = "0", serAzotLibW_1 = "0",
                serAzotCompW_1 = "0", serTelW_1 = "0", serBalcW_1 = "0",
                serBarbacW_1 = "0", serTerrW_1 = "0", serPatW_1 = "0",
                serPortW_1 = "0", serJardW_1 = "0", serPiscW_1 = "0",
                serGaraW_1 = "0", serPosGaraW_1 = "0", serElevW_1 = "0",
                serPuntAltW_1 = "0", serGasBalW_1 = "0", serGasCallW_1 = "0",
                serAg24HW_1 = "0", serCarpW_1 = "0";

        // Check for existence of substrings
        // THERE ARE MORE, LIKE TELEFONO and sala-comedor
        //<editor-fold>
        if (qserS.contains("sala-comedor")) {
            qserSalaComW_1 = "1";
        }
        if (qserS.contains("sala ")) {
            qserSalaW_1 = "1";
        }
        if (qserS.contains(" comedor")) {
            serComedorW_1 = "1";
        }
        if (qserS.contains("cocina")) {
            serCocinaW_1 = "1";
        }
        if (qserS.contains("cocina-comedor")) {
            serCocinaComW_1 = "1";
        }
        if (qserS.contains("azotea libre")) {
            serAzotLibW_1 = "1";
        }
        if (qserS.contains("azotea compartida")) {
            serAzotCompW_1 = "1";
        }
        if (qserS.contains("teléfono")) {
            serTelW_1 = "1";
        }
        if (qserS.contains("balcón")) {
            serBalcW_1 = "1";
        }
        if (qserS.contains("barbacoa")) {
            serBarbacW_1 = "1";
        }
        if (qserS.contains("terraza")) {
            serTerrW_1 = "1";
        }
        if (qserS.contains("patio")) {
            serPatW_1 = "1";
        }
        if (qserS.contains("portal")) {
            serPortW_1 = "1";
        }
        if (qserS.contains("jardín")) {
            serJardW_1 = "1";
        }
        if (qserS.contains("piscina")) {
            serPiscW_1 = "1";
        }
        if (qserS.contains("garaje")) {
            serGaraW_1 = "1";
        }
        if (qserS.contains("posibilidad de garaje")) {
            serPosGaraW_1 = "1";
        }
        if (qserS.contains("elevador")) {
            serElevW_1 = "1";
        }
        if (qserS.contains("puntal alto")) {
            serPuntAltW_1 = "1";
        }
        if (qserS.contains("gas de balón")) {
            serGasBalW_1 = "1";
        }
        if (qserS.contains("gas de la calle")) {
            serGasCallW_1 = "1";
        }
        if (qserS.contains("agua las 24 horas")) {
            serAg24HW_1 = "1";
        }
        if (qserS.contains("carposhe")) {
            serCarpW_1 = "1";
        }
        //</editor-fold>

        //4.- Location
        //<editor-fold>
        FilterBean qlocF = new FilterBean();
        HasAttributeFilter qlocA = new HasAttributeFilter("id", "ctl01_LabelProvinciasMunicipios4");
        HasAttributeFilter qlocA1 = new HasAttributeFilter("id", "ctl02_LabelProvinciasMunicipios4");
        OrFilter qlocAOR = new OrFilter(qlocA, qlocA1);
        qlocF.setFilters(new NodeFilter[]{qlocAOR});
        qlocF.setURL(theURL);
        String qlocS = qlocF.getText();
        qlocS = qlocS.replace("UBICADA EN: ", "");
        //</editor-fold>

        //5.- Quality (TENGO)
        //<editor-fold>
        FilterBean qqualF = new FilterBean();
        HasAttributeFilter qqualA = new HasAttributeFilter("id", "ctl01_LabelEstadoVivienda4");
        HasAttributeFilter qqualA1 = new HasAttributeFilter("id", "ctl02_LabelEstadoVivienda4");
        OrFilter qqualAOR = new OrFilter(qqualA, qqualA1);
        qqualF.setFilters(new NodeFilter[]{qqualAOR});
        qqualF.setURL(theURL);
        String qqualS = qqualF.getText();
        qqualS = qqualS.replace("ESTADO FÍSICO DE LA VIVIENDA: ", "");
        qqualS = qqualS.replace("ESTADO FÍSICO DE LA VIVIENDA:", "");
        qqualS = qualityreplacer(qqualS);

        //</editor-fold>

        //6.- Quality (QUIERO)
        //<editor-fold>
        FilterBean qqualQF = new FilterBean();
        HasAttributeFilter qqualQA = new HasAttributeFilter("id", "ctl01_LabelEstadoEdificio4");
        HasAttributeFilter qqualQA1 = new HasAttributeFilter("id", "ctl02_LabelEstadoEdificio4");
        OrFilter qqualQAOR = new OrFilter(qqualQA, qqualQA1);
        qqualQF.setFilters(new NodeFilter[]{qqualQAOR});
        qqualQF.setURL(theURL);
        String qqualQS = qqualQF.getText();
        qqualQS = qqualQS.replace("ESTADO FÍSICO DE LA EDIFICACIÓN: ", "");
        qqualQS = qqualQS.replace("ESTADO FÍSICO DE LA EDIFICACIÓN: ", "");
        qqualQS = qualityreplacer(qqualQS);

        //</editor-fold>

        //7.- Detalles
        //<editor-fold>
        FilterBean qdetF = new FilterBean();
        HasAttributeFilter qdetA = new HasAttributeFilter("id", "ctl01_LabelDetalles4");
        HasAttributeFilter qdetA1 = new HasAttributeFilter("id", "ctl02_LabelDetalles4");
        OrFilter qdetAOR = new OrFilter(qdetA, qdetA1);
        qdetF.setFilters(new NodeFilter[]{qdetAOR});
        qdetF.setURL(theURL);
        String qdetS = qdetF.getText();
        qdetS = qdetS.replace("OTROS DETALLES DE LA VIVIENDA QUE BUSCO: ", "");
        //</editor-fold>
        //</editor-fold>

        //QUIERO 2
        //<editor-fold>
        //<editor-fold>
        FilterBean qtypeF2 = new FilterBean();
        HasAttributeFilter qtypeA2 = new HasAttributeFilter("id", "ctl01_LabelTipoLocal5");
        HasAttributeFilter qtypeA21 = new HasAttributeFilter("id", "ctl02_LabelTipoLocal5");
        OrFilter qtypeA2OR = new OrFilter(qtypeA2, qtypeA21);
        qtypeF2.setFilters(new NodeFilter[]{qtypeA2OR});
        qtypeF2.setURL(theURL);
        String qtypeS2 = qtypeF2.getText();
        //</editor-fold>

        //2.- Rooms
        //<editor-fold>
        FilterBean qroomF2 = new FilterBean();
        HasAttributeFilter qroomA2 = new HasAttributeFilter("id", "ctl01_LabelHabitaciones5");
        HasAttributeFilter qroomA21 = new HasAttributeFilter("id", "ctl02_LabelHabitaciones5");
        OrFilter qroomA2OR = new OrFilter(qroomA2, qroomA21);
        qroomF2.setFilters(new NodeFilter[]{qroomA2OR});
        qroomF2.setURL(theURL);
        String qroomS2 = qroomF2.getText();
        qroomS2 = accentFix(qroomS2);
        //</editor-fold>

        //3.- Services
        //<editor-fold>
        FilterBean qserF2 = new FilterBean();
        HasAttributeFilter qserA2 = new HasAttributeFilter("id", "ctl01_LabelServicios5");
        HasAttributeFilter qserA21 = new HasAttributeFilter("id", "ctl02_LabelServicios5");
        OrFilter qserA2OR = new OrFilter(qserA2, qserA21);
        qserF2.setFilters(new NodeFilter[]{qserA2OR});
        qserF2.setURL(theURL);
        String qserS2 = qserF2.getText();
        String qserSalaComW_2 = "0", qserSalaW_2 = "0", serComedorW_2 = "0",
                serCocinaW_2 = "0", serCocinaComW_2 = "0", serAzotLibW_2 = "0",
                serAzotCompW_2 = "0", serTelW_2 = "0", serBalcW_2 = "0",
                serBarbacW_2 = "0", serTerrW_2 = "0", serPatW_2 = "0",
                serPortW_2 = "0", serJardW_2 = "0", serPiscW_2 = "0",
                serGaraW_2 = "0", serPosGaraW_2 = "0", serElevW_2 = "0",
                serPuntAltW_2 = "0", serGasBalW_2 = "0", serGasCallW_2 = "0",
                serAg24HW_2 = "0", serCarpW_2 = "0";

        // Check for existence of substrings
        // THERE ARE MORE, LIKE TELEFONO and sala-comedor
        //<editor-fold>
        if (qserS2.contains("sala-comedor")) {
            qserSalaComW_2 = "1";
        }
        if (qserS2.contains("sala ")) {
            qserSalaW_2 = "1";
        }
        if (qserS2.contains(" comedor")) {
            serComedorW_2 = "1";
        }
        if (qserS2.contains("cocina")) {
            serCocinaW_2 = "1";
        }
        if (qserS2.contains("cocina-comedor")) {
            serCocinaComW_2 = "1";
        }
        if (qserS2.contains("azotea libre")) {
            serAzotLibW_2 = "1";
        }
        if (qserS2.contains("azotea compartida")) {
            serAzotCompW_2 = "1";
        }
        if (qserS2.contains("teléfono")) {
            serTelW_2 = "1";
        }
        if (qserS2.contains("balcón")) {
            serBalcW_2 = "1";
        }
        if (qserS2.contains("barbacoa")) {
            serBarbacW_2 = "1";
        }
        if (qserS2.contains("terraza")) {
            serTerrW_2 = "1";
        }
        if (qserS2.contains("patio")) {
            serPatW_2 = "1";
        }
        if (qserS2.contains("portal")) {
            serPortW_2 = "1";
        }
        if (qserS2.contains("jardín")) {
            serJardW_2 = "1";
        }
        if (qserS2.contains("piscina")) {
            serPiscW_2 = "1";
        }
        if (qserS2.contains("garaje")) {
            serGaraW_2 = "1";
        }
        if (qserS2.contains("posibilidad de garaje")) {
            serPosGaraW_2 = "1";
        }
        if (qserS2.contains("elevador")) {
            serElevW_2 = "1";
        }
        if (qserS2.contains("puntal alto")) {
            serPuntAltW_2 = "1";
        }
        if (qserS2.contains("gas de balón")) {
            serGasBalW_2 = "1";
        }
        if (qserS2.contains("gas de la calle")) {
            serGasCallW_2 = "1";
        }
        if (qserS2.contains("agua las 24 horas")) {
            serAg24HW_2 = "1";
        }
        if (qserS2.contains("carposhe")) {
            serCarpW_2 = "1";
        }
        //</editor-fold>

        //4.- Location
        //<editor-fold>
        FilterBean qlocF2 = new FilterBean();
        HasAttributeFilter qlocA2 = new HasAttributeFilter("id", "ctl01_LabelProvinciasMunicipios5");
        HasAttributeFilter qlocA21 = new HasAttributeFilter("id", "ctl02_LabelProvinciasMunicipios5");
        OrFilter qlocA2OR = new OrFilter(qlocA2, qlocA21);
        qlocF2.setFilters(new NodeFilter[]{qlocA2OR});
        qlocF2.setURL(theURL);
        String qlocS2 = qlocF2.getText();
        qlocS2 = qlocS2.replace("UBICADA EN: ", "");
        //</editor-fold>

        //5.- Quality (TENGO)
        //<editor-fold>
        FilterBean qqualF2 = new FilterBean();
        HasAttributeFilter qqualA2 = new HasAttributeFilter("id", "ctl01_LabelEstadoVivienda5");
        HasAttributeFilter qqualA21 = new HasAttributeFilter("id", "ctl02_LabelEstadoVivienda5");
        OrFilter qqualA2OR = new OrFilter(qqualA2, qqualA21);
        qqualF2.setFilters(new NodeFilter[]{qqualA2OR});
        qqualF2.setURL(theURL);
        String qqualS2 = qqualF2.getText();
        qqualS2 = qualityreplacer(qqualS2);

        //</editor-fold>

        //6.- Quality (QUIERO)
        //<editor-fold>
        FilterBean qqualQF2 = new FilterBean();
        HasAttributeFilter qqualQA2 = new HasAttributeFilter("id", "ctl01_LabelEstadoEdificio5");
        HasAttributeFilter qqualQA21 = new HasAttributeFilter("id", "ctl02_LabelEstadoEdificio5");
        OrFilter qqualQA2OR = new OrFilter(qqualQA2, qqualQA21);
        qqualQF2.setFilters(new NodeFilter[]{qqualQA2OR});
        qqualQF2.setURL(theURL);
        String qqualQS2 = qqualQF2.getText();
        qqualQS2 = qualityreplacer(qqualQS2);

        //</editor-fold>

        //7.- Detalles
        //<editor-fold>
        FilterBean qdetF2 = new FilterBean();
        HasAttributeFilter qdetA2 = new HasAttributeFilter("id", "ctl01_LabelDetalles5");
        HasAttributeFilter qdetA21 = new HasAttributeFilter("id", "ctl02_LabelDetalles5");
        OrFilter qdetA2OR = new OrFilter(qdetA2, qdetA21);
        qdetF2.setFilters(new NodeFilter[]{qdetA2OR});
        qdetF2.setURL(theURL);
        String qdetS2 = qdetF2.getText();
        qdetS2 = qdetS2.replace("OTROS DETALLES DE LA VIVIENDA QUE BUSCO: ", "");
        //</editor-fold>
        if (qtypeS2.equals("") && qroomS2.equals("") && qserS2.equals("") && qlocS2.equals("")
                && qqualS2.equals("") && qqualQS2.equals("") && qdetS2.equals("")) {
            qtypeS2 = "-";
            qroomS2 = "-";
            qserS2 = "-";
            qlocS2 = "-";
            qqualS2 = "-";
            qqualQS2 = "-";
            qdetS2 = "-";
        }

        //</editor-fold>

        //QUIERO 3
        //<editor-fold>
        //<editor-fold>
        FilterBean qtypeF3 = new FilterBean();
        HasAttributeFilter qtypeA3 = new HasAttributeFilter("id", "ctl01_LabelTipoLocal6");
        HasAttributeFilter qtypeA31 = new HasAttributeFilter("id", "ctl02_LabelTipoLocal6");
        OrFilter qtypeA3OR = new OrFilter(qtypeA3, qtypeA31);
        qtypeF3.setFilters(new NodeFilter[]{qtypeA3OR});
        qtypeF3.setURL(theURL);
        String qtypeS3 = qtypeF3.getText();
        //</editor-fold>

        //2.- Rooms
        //<editor-fold>
        FilterBean qroomF3 = new FilterBean();
        HasAttributeFilter qroomA3 = new HasAttributeFilter("id", "ctl01_LabelHabitaciones6");
        HasAttributeFilter qroomA31 = new HasAttributeFilter("id", "ctl02_LabelHabitaciones6");
        OrFilter qroomA3OR = new OrFilter(qroomA3, qroomA31);
        qroomF3.setFilters(new NodeFilter[]{qroomA3OR});
        qroomF3.setURL(theURL);
        String qroomS3 = qroomF3.getText();
        qroomS3 = accentFix(qroomS3);
        //</editor-fold>

        //3.- Services
        //<editor-fold>
        FilterBean qserF3 = new FilterBean();
        HasAttributeFilter qserA3 = new HasAttributeFilter("id", "ctl01_LabelServicios6");
        HasAttributeFilter qserA31 = new HasAttributeFilter("id", "ctl02_LabelServicios6");
        OrFilter qserA3OR = new OrFilter(qserA3, qserA31);
        qserF3.setFilters(new NodeFilter[]{qserA3OR});
        qserF3.setURL(theURL);
        String qserS3 = qserF2.getText();
        String qserSalaComW_3 = "0", qserSalaW_3 = "0", serComedorW_3 = "0",
                serCocinaW_3 = "0", serCocinaComW_3 = "0", serAzotLibW_3 = "0",
                serAzotCompW_3 = "0", serTelW_3 = "0", serBalcW_3 = "0",
                serBarbacW_3 = "0", serTerrW_3 = "0", serPatW_3 = "0",
                serPortW_3 = "0", serJardW_3 = "0", serPiscW_3 = "0",
                serGaraW_3 = "0", serPosGaraW_3 = "0", serElevW_3 = "0",
                serPuntAltW_3 = "0", serGasBalW_3 = "0", serGasCallW_3 = "0",
                serAg24HW_3 = "0", serCarpW_3 = "0";

        // Check for existence of substrings
        // THERE ARE MORE, LIKE TELEFONO and sala-comedor
        //<editor-fold>
        if (qserS3.contains("sala-comedor")) {
            qserSalaComW_3 = "1";
        }
        if (qserS3.contains("sala ")) {
            qserSalaW_3 = "1";
        }
        if (qserS3.contains(" comedor")) {
            serComedorW_3 = "1";
        }
        if (qserS3.contains("cocina")) {
            serCocinaW_3 = "1";
        }
        if (qserS3.contains("cocina-comedor")) {
            serCocinaComW_3 = "1";
        }
        if (qserS3.contains("azotea libre")) {
            serAzotLibW_3 = "1";
        }
        if (qserS3.contains("azotea compartida")) {
            serAzotCompW_3 = "1";
        }
        if (qserS3.contains("teléfono")) {
            serTelW_3 = "1";
        }
        if (qserS3.contains("balcón")) {
            serBalcW_3 = "1";
        }
        if (qserS3.contains("barbacoa")) {
            serBarbacW_3 = "1";
        }
        if (qserS3.contains("terraza")) {
            serTerrW_3 = "1";
        }
        if (qserS3.contains("patio")) {
            serPatW_3 = "1";
        }
        if (qserS3.contains("portal")) {
            serPortW_3 = "1";
        }
        if (qserS3.contains("jardín")) {
            serJardW_3 = "1";
        }
        if (qserS3.contains("piscina")) {
            serPiscW_3 = "1";
        }
        if (qserS3.contains("garaje")) {
            serGaraW_3 = "1";
        }
        if (qserS3.contains("posibilidad de garaje")) {
            serPosGaraW_3 = "1";
        }
        if (qserS3.contains("elevador")) {
            serElevW_3 = "1";
        }
        if (qserS3.contains("puntal alto")) {
            serPuntAltW_3 = "1";
        }
        if (qserS3.contains("gas de balón")) {
            serGasBalW_3 = "1";
        }
        if (qserS3.contains("gas de la calle")) {
            serGasCallW_3 = "1";
        }
        if (qserS3.contains("agua las 24 horas")) {
            serAg24HW_3 = "1";
        }
        if (qserS3.contains("carposhe")) {
            serCarpW_3 = "1";
        }
        //</editor-fold>

        //4.- Location
        //<editor-fold>
        FilterBean qlocF3 = new FilterBean();
        HasAttributeFilter qlocA3 = new HasAttributeFilter("id", "ctl01_LabelProvinciasMunicipios6");
        HasAttributeFilter qlocA31 = new HasAttributeFilter("id", "ctl02_LabelProvinciasMunicipios6");
        OrFilter qlocA3OR = new OrFilter(qlocA3, qlocA31);
        qlocF3.setFilters(new NodeFilter[]{qlocA3OR});
        qlocF3.setURL(theURL);
        String qlocS3 = qlocF3.getText();
        qlocS3 = qlocS3.replace("UBICADA EN: ", "");
        //</editor-fold>

        //5.- Quality (TENGO)
        //<editor-fold>
        FilterBean qqualF3 = new FilterBean();
        HasAttributeFilter qqualA3 = new HasAttributeFilter("id", "ctl01_LabelEstadoVivienda6");
        HasAttributeFilter qqualA31 = new HasAttributeFilter("id", "ctl02_LabelEstadoVivienda6");
        OrFilter qqualA3OR = new OrFilter(qqualA3, qqualA31);
        qqualF3.setFilters(new NodeFilter[]{qqualA3OR});
        qqualF3.setURL(theURL);
        String qqualS3 = qqualF3.getText();
        qqualS3 = qualityreplacer(qqualS3);

        //</editor-fold>

        //6.- Quality (QUIERO)
        //<editor-fold>
        FilterBean qqualQF3 = new FilterBean();
        HasAttributeFilter qqualQA3 = new HasAttributeFilter("id", "ctl01_LabelEstadoEdificio6");
        HasAttributeFilter qqualQA31 = new HasAttributeFilter("id", "ctl02_LabelEstadoEdificio6");
        OrFilter qqualQA3OR = new OrFilter(qqualQA3, qqualQA31);
        qqualQF3.setFilters(new NodeFilter[]{qqualQA3OR});
        qqualQF3.setURL(theURL);
        String qqualQS3 = qqualQF3.getText();
        qqualQS3 = qualityreplacer(qqualQS3);

        //</editor-fold>

        //7.- Detalles
        //<editor-fold>
        FilterBean qdetF3 = new FilterBean();
        HasAttributeFilter qdetA3 = new HasAttributeFilter("id", "ctl01_LabelDetalles6");
        HasAttributeFilter qdetA31 = new HasAttributeFilter("id", "ctl02_LabelDetalles6");
        OrFilter qdetA3OR = new OrFilter(qdetA3, qdetA31);
        qdetF3.setFilters(new NodeFilter[]{qdetA3OR});
        qdetF3.setURL(theURL);
        String qdetS3 = qdetF3.getText();
        qdetS3 = qdetS3.replace("OTROS DETALLES DE LA VIVIENDA QUE BUSCO: ", "");
        //</editor-fold>
        if (qtypeS3.equals("") && qroomS3.equals("") && qserS3.equals("") && qlocS3.equals("")
                && qqualS3.equals("") && qqualQS3.equals("") && qdetS3.equals("")) {

            qtypeS3 = "-";
            qroomS3 = "-";
            qserS3 = "-";
            qlocS3 = "-";
            qqualS3 = "-";
            qqualQS3 = "-";
            qdetS3 = "-";
        }
        //</editor-fold>

        //CONTACT
        //<editor-fold>
        //1.- Contact Name
        //<editor-fold>
        FilterBean cnF = new FilterBean();
        HasAttributeFilter cnA = new HasAttributeFilter("id", "ctl01_LabelContacto");
        HasAttributeFilter cnA1 = new HasAttributeFilter("id", "ctl02_LabelContacto");
        OrFilter cnAOR = new OrFilter(cnA, cnA1);
        cnF.setFilters(new NodeFilter[]{cnAOR});
        cnF.setURL(theURL);
        String cnS = cnF.getText();
        cnS = cnS.replace("CONTACTAR A: ", "");
        //</editor-fold>

        //2.- Owner of property
        //<editor-fold>
        FilterBean ownF = new FilterBean();
        HasAttributeFilter ownA = new HasAttributeFilter("id", "ctl01_LabelPropietario");
        HasAttributeFilter ownA1 = new HasAttributeFilter("id", "ctl02_LabelPropietario");
        OrFilter ownAOR = new OrFilter(ownA, ownA1);
        ownF.setFilters(new NodeFilter[]{ownAOR});
        ownF.setURL(theURL);
        String ownS = ownF.getText();
        ownS = ownS.replace("PROPIETARIO DE LA VIVIENDA: ", "");
        //</editor-fold>

        //3.- Address
        //<editor-fold>
        FilterBean addrF = new FilterBean();
        HasAttributeFilter addrA = new HasAttributeFilter("id", "ctl01_LabelDireccionContacto");
        HasAttributeFilter addrA1 = new HasAttributeFilter("id", "ctl02_LabelDireccionContacto");
        OrFilter addrAOR = new OrFilter(addrA, addrA1);
        addrF.setFilters(new NodeFilter[]{addrAOR});
        addrF.setURL(theURL);
        String addrS = addrF.getText();
        addrS = addrS.replace("DIRECCIÓN: ", "");
        addrS = addrS.replace("DIRECCIÓN:", "");
        String habanaDummy = "0";
        if ((addrS.contains("habana")) || (addrS.contains(("Habana")))) {
            habanaDummy = "1";
        }
        //</editor-fold>

        //4.- Phone Number
        //<editor-fold>
        FilterBean phnF = new FilterBean();
        HasAttributeFilter phnA = new HasAttributeFilter("id", "ctl01_LabelTelefonoContacto");
        HasAttributeFilter phnA1 = new HasAttributeFilter("id", "ctl02_LabelTelefonoContacto");
        OrFilter phnAOR = new OrFilter(phnA, phnA1);
        phnF.setFilters(new NodeFilter[]{phnAOR});
        phnF.setURL(theURL);
        String phnS = phnF.getText();
        phnS = phnS.replace("TELÉFONO:", "");
        //</editor-fold>

        //5.- Email
        //<editor-fold>
        FilterBean emlF = new FilterBean();
        HasAttributeFilter emlA = new HasAttributeFilter("id", "ctl01_LabelCorreo");
        HasAttributeFilter emlA1 = new HasAttributeFilter("id", "ctl02_LabelCorreo");
        OrFilter emlAOR = new OrFilter(emlA, emlA1);
        emlF.setFilters(new NodeFilter[]{emlAOR});
        emlF.setURL(theURL);
        String emlS = emlF.getText();
        emlS = emlS.replace("CORREO ELECTRÓNICO: ", "");
        //</editor-fold>

        //6.- Other Info
        //<editor-fold>
        FilterBean othF = new FilterBean();
        HasAttributeFilter othA = new HasAttributeFilter("id", "ctl01_LabelOtraInformacion");
        HasAttributeFilter othA1 = new HasAttributeFilter("id", "ctl02_LabelOtraInformacion");
        OrFilter othAOR = new OrFilter(othA, othA1);
        othF.setFilters(new NodeFilter[]{othAOR});
        othF.setURL(theURL);
        String othS = othF.getText();
        othS = othS.replace("OTRA INFORMACIÓN DE CONTACTO: ", "");
        //</editor-fold>

        //7.- Side Payments Dummy
        //<editor-fold>
        String sidepaydummyGive = "0";
        String sidepaydummyReceive = "0";
        String sidepayUnspecifiedDirection = "0";
        //String othSlow = othS.toLowerCase();
        //String hLocS2low = othS.toLowerCase();
        //String hLocS3low = othS.toLowerCase();


        //</editor-fold>

        //</editor-fold>



        String[] retArr = new String[]{code, datepubl, type,
            hLocS, htipLocS, habS, roomHD, bathrHD, serS,
            serSalaComH_1, serSalaH_1, serComedorH_1, serCocinaH_1,
            serCocinaComH_1, serAzotLibH_1, serAzotCompH_1, serTelH_1,
            serBalcH_1, serBarbacH_1, serTerrH_1, serPatH_1,
            serPortH_1, serJardH_1, serPiscH_1, serGaraH_1, serPosGaraH_1,
            serElevH_1, serPuntAltH_1, serGasBalH_1, serGasCallH_1,
            serAg24HH_1, serCarpH_1, derS, munS, munCode, habDummy,
            dirdS, quaUTS, quaBTS, repairdummy1, cperS_1, fobsS, " --- ",
            hLocS2, htipLocS_2, habS2, roomHD2, bathrHD2, serS_2,
            serSalaComH_2, serSalaH_2, serComedorH_2, serCocinaH_2,
            serCocinaComH_2, serAzotLibH_2, serAzotCompH_2, serTelH_2,
            serBalcH_2, serBarbacH_2, serTerrH_2, serPatH_2,
            serPortH_2, serJardH_2, serPiscH_2, serGaraH_2, serPosGaraH_2,
            serElevH_2, serPuntAltH_2, serGasBalH_2, serGasCallH_2,
            serAg24HH_2, serCarpH_2, derS2, munS2, munCode2, habDummy2,
            dirdS2, quaUTS2, quaBTS2, repairdummy2, cperS_2, fobsS2, " --- ",
            hLocS3, htipLocS_3, habS3, roomHD3, bathrHD3, serS_3,
            serSalaComH_3, serSalaH_3, serComedorH_3, serCocinaH_3,
            serCocinaComH_3, serAzotLibH_3, serAzotCompH_3, serTelH_3,
            serBalcH_3, serBarbacH_3, serTerrH_3, serPatH_3,
            serPortH_3, serJardH_3, serPiscH_3, serGaraH_3, serPosGaraH_3,
            serElevH_3, serPuntAltH_3, serGasBalH_3, serGasCallH_3,
            serAg24HH_3, serCarpH_3, derS3, munS3, munCode3, habDummy3,
            dirdS3, quaUTS3, quaBTS3, repairdummy3, cperS_3, fobsS3, " --- ",
            qtypeS, qroomS, qserS, qserSalaComW_1, qserSalaW_1, serComedorW_1,
            serCocinaW_1, serCocinaComW_1, serAzotLibW_1, serAzotCompW_1,
            serTelW_1,
            serBalcW_1, serBarbacW_1, serTerrW_1, serPatW_1, serPortW_1,
            serJardW_1,
            serPiscW_1, serGaraW_1, serPosGaraW_1, serElevW_1, serPuntAltW_1,
            serGasBalW_1, serGasCallW_1, serAg24HW_1, serCarpW_1, qlocS,
            qqualS, qqualQS, qdetS,
            " --- ",
            qtypeS2, qroomS2, qserS2, qserSalaComW_2, qserSalaW_2, serComedorW_2,
            serCocinaW_2, serCocinaComW_2, serAzotLibW_2, serAzotCompW_2,
            serTelW_2,
            serBalcW_2, serBarbacW_2, serTerrW_2, serPatW_2, serPortW_2,
            serJardW_2,
            serPiscW_2, serGaraW_2, serPosGaraW_2, serElevW_2, serPuntAltW_2,
            serGasBalW_2, serGasCallW_2, serAg24HW_2, serCarpW_2, qlocS2,
            qqualS2, qqualQS2,
            qdetS2, " --- ",
            qtypeS3, qroomS3, qserS3, qserSalaComW_3, qserSalaW_3, serComedorW_3,
            serCocinaW_3, serCocinaComW_3, serAzotLibW_3, serAzotCompW_3,
            serTelW_3,
            serBalcW_3, serBarbacW_3, serTerrW_3, serPatW_3, serPortW_3,
            serJardW_3,
            serPiscW_3, serGaraW_3, serPosGaraW_3, serElevW_3, serPuntAltW_3,
            serGasBalW_3, serGasCallW_3, serAg24HW_3, serCarpW_3,
            qlocS3, qqualS3, qqualQS3,
            qdetS3, " --- ",
            cnS, ownS, addrS, habanaDummy, phnS, emlS, othS, sidepaydummyGive,
            sidepaydummyReceive, sidepayUnspecifiedDirection};

        //System.out.println("retArr length: " + retArr.length);
        //System.out.println(retArr[1]);


        for (int p = 0; p < retArr.length; p++) {
            //System.out.println(p);
            if ((retArr[p].contains("vuelto") || retArr[p].contains("vuelto") || retArr[p].contains("vuelto")) && (retArr[p].contains("doy") || retArr[p].contains("doy") || retArr[p].contains("doy")
                    || retArr[p].contains("ofrecemos") || retArr[p].contains("ofrecemos") || retArr[p].contains("ofrecemos")
                    || retArr[p].contains("ofrezco") || retArr[p].contains("ofrezco") || retArr[p].contains("ofrezco"))) {
                /*
                 * sidepaydummyGive
                 */ retArr[223] = "1";
            }

            if ((retArr[p].contains("vuelto") || retArr[p].contains("vuelto") || retArr[p].contains("vuelto")) && (retArr[p].contains("pido") || retArr[p].contains("pido") || retArr[p].contains("pido")
                    || retArr[p].contains("pedimos") || retArr[p].contains("pedimos") || retArr[p].contains("pedimos")
                    || retArr[p].contains("pedimos") || retArr[p].contains("pedimos") || retArr[p].contains("pedimos"))) {
                /*
                 * sidepaydummyReceive
                 */ retArr[224] = "1";
            }

            if (retArr[p].contains("vuelto") || retArr[p].contains("vuelto") || retArr[p].contains("vuelto")) {
                /*
                 * sidepayUnspecifiedDirection
                 */ retArr[225] = "1";
            }

            if (retArr[p].contains("sin vuelto") || retArr[p].contains("sin vuelto") || retArr[p].contains("sin vuelto")) {
                /*
                 * sidepaydummyGive
                 */ retArr[223] = "0";
                /*
                 * sidepaydummyReceive
                 */ retArr[224] = "0";
                /*
                 * sidepayUnspecifiedDirection
                 */ retArr[225] = "0";
            }
        }

        String retStr = "";

        for (int i = 0; i < retArr.length; i++) {
            retArr[i] = retArr[i].replace(",", " y");
            retArr[i] = fixer.accentFix(retArr[i]);
            retArr[i] = retArr[i].toLowerCase();
            /*
             * if (retArr[i].equals("")) retArr[i] = "0";
             */
            retStr = retStr + retArr[i] + ",";
        }

        return retStr;
    }

    public String accentFix(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = str.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        str = str.trim(); //removes trailing and leading whitespace
        str = str.replace("½", "");

        return str;
    }

    public String qualityreplacer(String a) {
        a = accentFix(a);
        a = a.replace("optimo | muy bueno | bueno", "3");
        a = a.replace("regular", "2");
        a = a.replace("malo", "1");
        a = a.replace("ESTADO FÍSICO DE LA VIVIENDA: ", "");
        a = a.replace("ESTADO FÍSICO DE LA VIVIENDA:", "");
        a = a.replace("reparable", "");
        if (a.equals("")) {
            a = "0";
        }
        return a;
    }

    public void textCreator() throws IOException, ParseException {
        Permutas_Main rvp1 = new Permutas_Main();

        // Declare some variables
        String date = "";
        String towrite = "";
        String path = "";

        // GET ROOT FILE
        File dir1 = new File(onMac);
        //"C:/Documents and Settings/fsimon0/My Documents/Cubisima Final/"
        String[] datefolderChildren = dir1.list();
        //System.out.println(datefolderChildren[2]);

        String allstring = "";

        // GET ALL date FOLDERS
        for (int i = 0; i < datefolderChildren.length; i++) {
            if(datefolderChildren[i].contains("$")){
                continue;
            }
            date = datefolderChildren[i];
            // APPLY ALL ALGORITHM TO ALL datefolders
            String enclosing = onMac + date + "/permutas/anuncios/";
            //"C:/Documents and Settings/fsimon0/My Documents/Cubisima Final/"+date+"/permutas/anuncios/"
            System.out.println("enclosing" + enclosing);
            File dir = new File(enclosing);
            String[] individualHTMLChildren = dir.list();

            File theTXTFile = new File(onMac + date + "/Cubisima -" + date + " - Permutas.csv");
            //"C:/Documents and Settings/fsimon0/My Documents/Cubisima Final/"+date+"/Cubisima -"+date+" - Permutas.csv"
            
            FileWriter aFileWriter = null;
            try {
                aFileWriter = new FileWriter(theTXTFile);
            } catch (IOException e1) {
            }
            if (aFileWriter != null) {
                aFileWriter.write(headers);
            }

            if (individualHTMLChildren != null) {
                for (int j = 0; j < individualHTMLChildren.length; j++) {
                    String fn = enclosing + individualHTMLChildren[j];
                    System.out.println("filename:" + fn + "progress:"
                            + (float) j / individualHTMLChildren.length + "%"
                            + "-" + i + "/" + datefolderChildren.length + "%");

                    if (aFileWriter != null) {
                        path = date.replace(",", ";") + "," + individualHTMLChildren[j].replace(",", ";");
                        if (individualHTMLChildren[j].startsWith(".") || 
                                individualHTMLChildren[j].endsWith(".csv")) {
                            continue;
                        }
                        towrite = rvp1.myStringExtract(fn) + path + "\n";

                        ///// MAKE UNIQUE HASHTABLE
                        String code = getCodeFromURL(fn);
                        makeUniqueHashTable(code, date, towrite);
                        /////---------------

                        aFileWriter.write(towrite);
                        allstring = allstring + towrite;
                    }
                }
                aFileWriter.close();
            }
        }
        File theAllTXTFile = new File(onMac + "/CubisimaCB - AllPermutas.csv");
        //"C:/Documents and Settings/fsimon0/My Documents/Cubisima Final/CubisimaCB - AllPermutas.csv"
        FileWriter allFileWriter = new FileWriter(theAllTXTFile);
        allFileWriter.write(headers);
        allFileWriter.write(allstring);
    }
}