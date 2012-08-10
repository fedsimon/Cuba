package Casas;

import java.io.*;
import java.text.Normalizer;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.htmlparser.NodeFilter;
import org.htmlparser.beans.FilterBean;
import org.htmlparser.filters.*;

// ADDED SOLD DUMMY AFTER PRICE COLUMN HEADER BEFORE SUSPICIOUS
public class Casas_Main {

    public static String onMac = "/Users/fsimon/Desktop/ADAIR/CubaData/";
    public static String onPC = "C:/Documents and Settings/fsimon0/My Documents/CubaData Original/";
    public static Hashtable<String, String> hasht1 = new Hashtable<String, String>();
    public static Hashtable<String, String> hasht2 = new Hashtable<String, String>();
    public static String headers = "Green Format Dummy, Date Published (From Dictionary),"
            + "I'm Buying Dummy (1 means they are looking to buy),"
            + "Apartamento Dummy, Casa Dummy, "
            + "Terreno Dummy, Propiedad Horizontal Dummy, Penthouse Dummy"
            + ", Rooms, Bathrooms, Precio (They are all cuc), Sold Dummy, Suspicious Price dummy,"
            + "Metros,Cantidad de Personas, Estado Fisico,Direccion,"
            + "Observaciones, School, Park, Beach,"
            + "Sala,Patio,Agua,Comedor,Portal,"
            + "Carposhe,Cocina,Jardin,Azotea Compartida,Sala-comedor,"
            + "Piscina,Hall,Cocina-comedor,Garaje,Saleta,Barbacoa,"
            + "Posibilidad de Garaje,Pasillo,Azotea Libre,"
            + "Puntal Alto,Puerta Calle,Elevador,Telefono,Tanque Instalado,"
            + "Balcon,Gas de la Calle,Patinejo,Terraza,Gas de Balon, "
            + "Contact(Green Format Only), Publish Date (Green Format Only), Date of Scrape, Filepath \n";

    public static void main(String[] args) throws IOException {
        Casas_Main cubc = new Casas_Main();
        cubc.createDictionary();
        cubc.textCreator();
        System.out.println("Done");
    }

    //********
    //Reads the two dictionaries (municipio and date) and makes it into a hashtable.
    public static void createDictionary() throws FileNotFoundException,
            IOException {
        Casas_Main fixer2 = new Casas_Main();
        String fn = onMac + "Casas_Date_Dictionary.csv";
        //"C:/Documents and Settings/fsimon0/My Documents/CubaData Original/RentasDateDictionary.csv";
        String municfn = onMac + "MunicipioDictionaryaccentfix.csv";
        //"C:/Documents and Settings/fsimon0/My Documents/CubaData Original/MunicipioDictionaryaccentfix.csv";

        BufferedReader areader = new BufferedReader(new FileReader(fn));
        BufferedReader munireader = new BufferedReader(new FileReader(municfn));

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
    //**********

    public String getDateFromDict(String theURL) {
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
        return dateFromDict;
    }

    public String getGreenIf(String theURL) {
        FilterBean green = new FilterBean();
        TagNameFilter greenTNF = new TagNameFilter("span");
        HasAttributeFilter greenAttribute = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelDetalle");
        AndFilter greenAND = new AndFilter(greenTNF, greenAttribute);
        green.setFilters(new NodeFilter[]{greenAND});
        green.setURL(theURL);
        String greenif = green.getText();
        return greenif;
    }

    public String[] getCasa_s(String theURL) {
        FilterBean casa = new FilterBean();
        TagNameFilter casaTNF = new TagNameFilter("span");
        HasAttributeFilter casaAttribute = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelBasicInfo0");
        HasAttributeFilter casaAttributeWP = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelBasicInfo");
        OrFilter anOrFilter = new OrFilter(casaAttribute, casaAttributeWP);
        AndFilter casaAND = new AndFilter(casaTNF, anOrFilter);
        casa.setFilters(new NodeFilter[]{casaAND});
        casa.setURL(theURL);
        String casa_s = casa.getText();
        casa_s = casa_s.replace(",", "-");
        casa_s = casa_s.replace("\n", ";");
        casa_s = casa_s.replace("½", ".5");
        casa_s = casa_s.replace("cuartos", "");
        casa_s = casa_s.replace("cuarto", "");
        casa_s = casa_s.replace("Habitaciones", "");
        casa_s = casa_s.replace("Habitacion", "");
        casa_s = casa_s.replace("Habitaciónes", "");
        casa_s = casa_s.replace("Habitación", "");
        casa_s = casa_s.replace("cuartos", "");
        casa_s = casa_s.replace("terreno", "");
        casa_s = casa_s.replace("?", "");
        casa_s = casa_s.replace("baños", "");
        casa_s = casa_s.replace("baño", "");
        //System.out.println("jkjb"+casa_s);
        String apdum_s = "0";
        String casdum_s = "0";
        String terdum_s = "0";
        String prophdum_s = "0";
        String penthdum_s = "0";

        if ((casa_s.contains("Apartamento")) || casa_s.contains("apartamento")) {
            apdum_s = "1";
        }
        if ((casa_s.contains("Casa")) || casa_s.contains("casa")) {
            casdum_s = "1";
        }
        if ((casa_s.contains("Terreno")) || casa_s.contains("terreno")) {
            terdum_s = "1";
        }
        if ((casa_s.contains("Prop horizontal")) || casa_s.contains("prop horizontal")) {
            prophdum_s = "1";
        }
        if ((casa_s.contains("Penthouse")) || casa_s.contains("penthouse")) {
            penthdum_s = "1";
        }

        casa_s = casa_s.replace("Apartamento", "");
        casa_s = casa_s.replace("Casa", "");
        casa_s = casa_s.replace("Terreno", "");
        casa_s = casa_s.replace("Prop horizontal", "");
        casa_s = casa_s.replace("Penthouse", "");

        String[] retarr = {casa_s, apdum_s, casdum_s, terdum_s, prophdum_s,
            penthdum_s};

        return retarr;
    }

    public String[] getPrices(String theURL) {

        FilterBean precio = new FilterBean();
        TagNameFilter precioTNF = new TagNameFilter("span");
        HasAttributeFilter precioAttribute = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelPrecio0");
        HasAttributeFilter precioAttributeWP = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelPrecio");
        OrFilter precioOR = new OrFilter(precioAttribute, precioAttributeWP);
        AndFilter precioAND = new AndFilter(precioTNF, precioOR);
        precio.setFilters(new NodeFilter[]{precioAND});
        precio.setURL(theURL);
        String precio_s = precio.getText();
        String precio_vendido = "0";

        if (precio_s.contains("vendido")) {
            precio_vendido = "1";
        }

        precio_s = precio_s.replace("(vendido)", "");
        precio_s = precio_s.replace(",", ";");
        precio_s = precio_s.replace("\n", "");
        precio_s = precio_s.replace("Precio:", "");
        precio_s = precio_s.replace("cuc", "");
        String precio_sdummy = "";
        int precio_i = 0;
        //System.out.println("hbhjbh" + precio_s);
        precio_s = precio_s.trim();
        String precio_a = precio_s;
        precio_a = precio_a.replaceAll("\\D+", "");

        if (!precio_a.equals("")) {
            precio_i = java.lang.Integer.parseInt(precio_a);
        }

        if ((precio_i < 100) || (precio_s.equals("")) || precio_s.equals(" ")) {
            precio_sdummy = "1";
        }

        String[] retarr = {precio_s, precio_vendido, precio_sdummy};
        return retarr;
    }

    public String getMetros_s(String theURL) {
        FilterBean metros = new FilterBean();
        TagNameFilter metrosTNF = new TagNameFilter("span");
        HasAttributeFilter metrosAttribute = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelMetros0");
        HasAttributeFilter metrosAttributeWP = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelMetros");
        OrFilter metrosOR = new OrFilter(metrosAttribute, metrosAttributeWP);
        AndFilter metrosAND = new AndFilter(metrosTNF, metrosOR);
        metros.setFilters(new NodeFilter[]{metrosAND});
        metros.setURL(theURL);
        String metros_s = metros.getText();
        metros_s = metros_s.replace(",", ";");
        metros_s = metros_s.replace("\n", "");
        metros_s = metros_s.replace("Metros²:", "");
        metros_s = metros_s.replace("-", "0");

        return metros_s;
    }

    public String getCp_s(String theURL) {
        FilterBean cpersonas = new FilterBean();
        TagNameFilter cpTNF = new TagNameFilter("span");
        HasAttributeFilter cpAttribute = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelCantPers0");
        HasAttributeFilter cpAttributeWP = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelCantPers");
        OrFilter cpOR = new OrFilter(cpAttribute, cpAttributeWP);
        AndFilter cpAND = new AndFilter(cpTNF, cpOR);
        cpersonas.setFilters(new NodeFilter[]{cpAND});
        cpersonas.setURL(theURL);
        String cp_s = cpersonas.getText();
        cp_s = cp_s.replace(",", ";");
        cp_s = cp_s.replace("-", "0");
        cp_s = cp_s.replace("\n", ";");
        cp_s = cp_s.replace("Cantidad de personas que pueden vivir:", "");
        return cp_s;
    }

    public String getEstado_s(String theURL) {
        FilterBean estado = new FilterBean();
        TagNameFilter estadoTNF = new TagNameFilter("span");
        HasAttributeFilter estadoAttribute = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelEstado0");
        HasAttributeFilter estadoAttributeWP = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelEstado");
        OrFilter estadoOR = new OrFilter(estadoAttribute, estadoAttributeWP);
        AndFilter estadoAND = new AndFilter(estadoTNF, estadoOR);
        estado.setFilters(new NodeFilter[]{estadoAND});
        estado.setURL(theURL);
        String estado_s = estado.getText();
        estado_s = estado_s.replace(",", ";");
        estado_s = estado_s.replace("\n", ";");
        estado_s = estado_s.replace("Estado físico:", "");
        estado_s = estado_s.replace("Ó", "O");
        estado_s = estado_s.replace("ó", "o");
        estado_s = estado_s.replace("-", "0");
        return estado_s;
    }

    public String getD_s(String theURL) {
        FilterBean direccion = new FilterBean();
        TagNameFilter dTNF = new TagNameFilter("span");
        HasAttributeFilter dAttribute = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelDireccion0");
        HasAttributeFilter dAttributeWP = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelDireccion");
        OrFilter dOr = new OrFilter(dAttribute, dAttributeWP);
        AndFilter dAND = new AndFilter(dTNF, dOr);
        direccion.setFilters(new NodeFilter[]{dAND});
        direccion.setURL(theURL);
        String d_s = direccion.getText();
        d_s = d_s.replace(",", ";");
        //System.out.println("before" + d_s);
        d_s = d_s.replaceAll("\\r|\\n", " ");
        //System.out.println("after" + d_s);
        d_s = d_s.replace("Dirección: ", "");
        //System.out.println("d_s" + d_s);

        return d_s;
    }

    public String getO_s(String theURL) {
        FilterBean observaciones = new FilterBean();
        TagNameFilter oTNF = new TagNameFilter("span");
        HasAttributeFilter oAttribute = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelObservaciones0");
        HasAttributeFilter oAttributeWP = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelObservaciones");
        OrFilter oOr = new OrFilter(oAttribute, oAttributeWP);
        AndFilter oAND = new AndFilter(oTNF, oOr);
        observaciones.setFilters(new NodeFilter[]{oAND});
        observaciones.setURL(theURL);
        String o_s = observaciones.getText();
        //System.out.println("o_s" + o_s);
        o_s = o_s.replace(",", ";");
        o_s = o_s.replace("\n", ";");
        o_s = o_s.replace("Observaciones: ", "");
        o_s = o_s.replace("-", "0");

        return o_s;
    }

    public String myStringExtract(String theURL) {

        //Publish date from the dictionary
        String dateFromDict = getDateFromDict(theURL);
        
        //For using an accentfixer later
        Casas_Main fixer = new Casas_Main();
        
        //Check we're in green format
        String greenif = getGreenIf(theURL);
        
        // Rooms & bathrooms & dummys
        String[] aret = getCasa_s(theURL);
        String casa_s = aret[0];
        String apdum_s = aret[1];
        String casdum_s = aret[2];
        String terdum_s = aret[3];
        String prophdum_s = aret[4];
        String penthdum_s = aret[5];
        
        //Bathrooms
        String rooms_s = "0";
        String bathrooms_s = "0";
        String[] smth = casa_s.split("-");
        rooms_s = smth[0];
        
        //*********
        //If the array contains more than one thing, then the second thing is the number of bathrooms.
        if (smth.length > 1) {bathrooms_s = smth[1];} 
        //*********
        // Prices
        String[] pret = getPrices(theURL);
        String precio_s = pret[0];
        String precio_vendido = pret[1];
        String precio_sdummy = pret[2];
        //Metros
        String metros_s = getMetros_s(theURL);
        //Cantidad de Personas
        String cp_s = getCp_s(theURL);
        //Estado Fisico
        String estado_s = getEstado_s(theURL);
        //Direccion
        String d_s = getD_s(theURL);
        //Observaciones
        //<editor-fold>
        String o_s = getO_s(theURL);
        String sch_s = "0";
        String pk_s = "0";
        String bch_s = "0";
        if ((o_s.contains("escuela")) || (o_s.contains("Escuela"))) {
            sch_s = "1";
        }
        if ((pk_s.contains("parque")) || (o_s.contains("Parque"))) {
            pk_s = "1";
        }
        if ((bch_s.contains("playa")) || (o_s.contains("Playa"))) {
            bch_s = "1";
        }
        //</editor-fold>

        //Characteristics
        //<editor-fold>
        // Set Filters
        TagNameFilter theTD = new TagNameFilter("td");
        HasAttributeFilter theIMG = new HasAttributeFilter("src", "http://www.cubisima.com/Images/checked.png");
        HasChildFilter childOfTD = new HasChildFilter(theIMG);
        AndFilter both = new AndFilter(theTD, childOfTD);

        // Set FilterBean
        FilterBean fb = new FilterBean();
        fb.setFilters(new NodeFilter[]{both});
        fb.setURL(theURL);
        String s = fb.getText();
        //</editor-fold>

        String meters_green_proc = "";
        String bathrooms_green_proc = "";

        String bathrooms_green = "";
        String direction_green = "";
        String published_green = "";
        String contact_green = "";
        String observations_green = "";

        if (!greenif.equals("")) { //WE'RE ON GREEN FORMAT
            String[] greenArray = greenif.split("\n");

            for (int i = 0; i < greenArray.length; i++) {
                //System.out.println("greenArray[i] = " + greenArray[i]);
                if ((greenArray[i].contains("baño.") || greenArray[i].contains("baños."))
                        && greenArray[i].contains("con") && !greenArray[i].contains("Observaciones")) {
                    bathrooms_green = greenArray[i];
                } else if (greenArray[i].contains("Dirección:")) {
                    direction_green = greenArray[i].replace("Dirección:", "");
                } else if (greenArray[i].contains("Publicada")) {
                    published_green = greenArray[i].replace("Publicada: ", "");
                } else if (greenArray[i].contains("Contacto")) {
                    contact_green = greenArray[i].replace("Contacto: ", "");
                } else if (greenArray[i].contains("Observaciones")) {
                    observations_green = greenArray[i].replace("Observaciones del anunciante: ", "");
                }
            }

            if (bathrooms_green.indexOf("metros²") != -1) {
                meters_green_proc = bathrooms_green.substring(0, bathrooms_green.indexOf("metros²"));
            }

            if (bathrooms_green.indexOf("con") > 0 && bathrooms_green.indexOf("baño") > 0) {
                bathrooms_green_proc = bathrooms_green.substring(bathrooms_green.indexOf("con") + 3, bathrooms_green.indexOf("baño"));
            }

            if (!direction_green.equals("")) {
                direction_green = direction_green.replace("Dirección: ", "");
            }
        }

        String bathrooms_choice = "";

        if (!bathrooms_green_proc.equals("") && bathrooms_s.equals("")) {
            bathrooms_choice = bathrooms_green_proc;
        } else {
            bathrooms_choice = bathrooms_s;
        }

        String direction_choice = "";

        if (!direction_green.equals("") && d_s.equals("")) {
            direction_choice = direction_green;
        } else {
            direction_choice = d_s;
        }

        String observation_choice = "";
        if (!observations_green.equals("") && o_s.equals("")) {
            observation_choice = observations_green;
        } else {
            observation_choice = o_s;
        }

        String metros_choice = "";
        if (!meters_green_proc.equals("") && metros_s.equals("")) {
            metros_choice = meters_green_proc;
        } else {
            metros_choice = metros_s;
        }

        //Get green_price
        String precio_choice = "";

        FilterBean price_green = new FilterBean();
        TagNameFilter prgTNF = new TagNameFilter("span");
        HasAttributeFilter prgAttribute = new HasAttributeFilter("id", "ctl00_MainPlaceHolder_LabelTitulo");
        AndFilter pgAND = new AndFilter(prgTNF, prgAttribute);
        price_green.setFilters(new NodeFilter[]{pgAND});
        price_green.setURL(theURL);
        String title_green = price_green.getText();

        String price_green_s = "";
        if (title_green.indexOf("cuc") != -1) {
            price_green_s = title_green.substring(0, title_green.indexOf("cuc"));
        }

        String buying_dummy = "0";
        if (title_green.contains("compro") || title_green.contains("Compro")) {
            buying_dummy = "1";
        }

        if (!price_green_s.equals("") && precio_s.equals("")) {
            precio_choice = price_green_s;
        } else {
            precio_choice = precio_s;
        }

        String greenformat_dummy = "0";
        if (!greenif.equals("")) {
            greenformat_dummy = "1";
        }

        if (s.equals("")) {
            s = greenif + " " + title_green;
        }

        // Create property string
        //<editor-fold>
        String sala = "0";
        String patio = "0";
        String agua = "0";
        String comedor = "0";
        String portal = "0";
        String carposhe = "0";
        String cocina = "0";
        String jardin = "0";
        String azotea_compartida = "0";
        String sala_comedor = "0";
        String piscina = "0";
        String hall = "0";
        String cocina_comedor = "0";
        String garaje = "0";
        String saleta = "0";
        String barbacoa = "0";
        String posibilidad_de_garaje = "0";
        String pasillo = "0";
        String azotea_Libre = "0";
        String puntal_alto = "0";
        String puerta_calle = "0";
        String elevador = "0";
        String telefono = "0";
        String tanque_Instalado = "0";
        String balcon = "0";
        String gas_de_la_calle = "0";
        String patinejo = "0";
        String terraza = "0";
        String gas_de_Balon = "0";

        //</editor-fold>

        //Check property
        //<editor-fold>
        if (s.contains("sala")) {
            sala = "1";
        }
        if (s.contains("patio")) {
            patio = "1";
        }
        if (s.contains("agua las 24 horas")) {
            agua = "1";
        }
        if (s.contains("comedor")) {
            comedor = "1";
        }
        if (s.contains("portal")) {
            portal = "1";
        }
        if (s.contains("carposhe")) {
            carposhe = "1";
        }
        if (s.contains("cocina")) {
            cocina = "1";
        }
        if (s.contains("jardin")) {
            jardin = "1";
        }
        if (s.contains("azotea compartida")) {
            azotea_compartida = "1";
        }
        if (s.contains("sala-comedor")) {
            sala_comedor = "1";
        }
        if (s.contains("piscina")) {
            piscina = "1";
        }
        if (s.contains("hall")) {
            hall = "1";
        }
        if (s.contains("cocina-comedor")) {
            cocina_comedor = "1";
        }
        if (s.contains("garaje")) {
            garaje = "1";
        }
        if (s.contains("saleta")) {
            saleta = "1";
        }
        if (s.contains("barbacoa")) {
            barbacoa = "1";
        }
        if (s.contains("posibilidad de garaje")) {
            posibilidad_de_garaje = "1";
        }
        if (s.contains("pasillo")) {
            pasillo = "1";
        }
        if (s.contains("azotea libre")) {
            azotea_Libre = "1";
        }
        if (s.contains("puntal alto")) {
            puntal_alto = "1";
        }
        if (s.contains("puerta calle")) {
            puerta_calle = "1";
        }
        if (s.contains("elevador")) {
            elevador = "1";
        }
        if (s.contains("telefono")) {
            telefono = "1";
        }
        if (s.contains("tanque instalado")) {
            tanque_Instalado = "1";
        }
        if (s.contains("balcon")) {
            balcon = "1";
        }
        if (s.contains("gas de la calle")) {
            gas_de_la_calle = "1";
        }
        if (s.contains("patinejo")) {
            patinejo = "1";
        }
        if (s.contains("terraza")) {
            terraza = "1";
        }
        if (s.contains("gas de balon")) {
            gas_de_Balon = "1";
        }
        //</editor-fold>


        String[] retArr = new String[]{greenformat_dummy, dateFromDict,
            buying_dummy, apdum_s, casdum_s, terdum_s,
            prophdum_s, penthdum_s, rooms_s, bathrooms_choice, precio_choice,
            precio_vendido, precio_sdummy,
            metros_choice, cp_s, estado_s, direction_choice, observation_choice,
            sch_s, pk_s, bch_s, sala, patio, agua,
            comedor, portal, carposhe, cocina, jardin, azotea_compartida,
            sala_comedor,
            piscina, hall, cocina_comedor, garaje, saleta, barbacoa,
            posibilidad_de_garaje,
            pasillo, azotea_Libre, puntal_alto, puerta_calle, elevador, telefono,
            tanque_Instalado, balcon, gas_de_la_calle, patinejo, terraza,
            gas_de_Balon, contact_green, published_green};


        String retStr = "";

        for (int i = 0; i < retArr.length; i++) {
            retArr[i] = retArr[i].replace(",", " y");
            retArr[i] = retArr[i].replace(".", "");
            retArr[i] = Casas_Main.accentFix(retArr[i]);
            if (retArr[i].equals("")) {
                retArr[i] = "0";
            }
            retStr = retStr + retArr[i] + ",";
        }

        return retStr;
    }

    public static String accentFix(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = str.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        str = str.trim(); //removes trailing and leading whitespace
        str = str.replace("½", "");

        return str;
    }

    public void textCreator() throws IOException {
        Casas_Main rvp1 = new Casas_Main();
        String date = "";
        String towrite = "";
        String path = "";
        //*******
        //Lists all {2011-sept1, etc......}
        File dir1 = new File(onMac);
        String[] datefolderChildren = dir1.list();
        //********

        String allstring = "";

        for (int i = 1; i < datefolderChildren.length; i++) {
            if (!datefolderChildren[i].contains("NCB")) {
                date = datefolderChildren[i];
                String enclosing = onMac + date + "/casas/";

                //************
                //Makes array {anuncios, fotos, and all of the html files in that scrape date}
                File dir = new File(enclosing);
                String[] individualHTMLChildren = dir.list();
                //************

                //************
                //Create text file and writer
                File theTXTFile = new File(onMac + date + "/Cubisima -" + date + " - Casas.csv");
                FileWriter aFileWriter = new FileWriter(theTXTFile);
                //*************

                //**************
                //Write the header
                aFileWriter.write(headers);
                //**************

                if (individualHTMLChildren != null) {
                    for (int j = 0; j < individualHTMLChildren.length; j++) {
                        if (individualHTMLChildren[j].startsWith(".")
                                && !individualHTMLChildren[j].equals("anuncios")
                                && !individualHTMLChildren[j].equals("administrar.html")) {
                            continue; //A.K.A. skip this iteration of the above forloop
                        }

                        String fn = enclosing + individualHTMLChildren[j];

                        //********************
                        //This spits out the progress of casas_main
                        System.out.println("filename:" + fn + "progress:"
                                + (float) j / individualHTMLChildren.length * 100 + "%" + "-"
                                + i + "/" + datefolderChildren.length * 100 + "%");
                        //*********************

                        path = date.replace(",", ";") + "," + individualHTMLChildren[j].replace(",", ";");
                        towrite = rvp1.myStringExtract(fn) + path + "\n";
                        aFileWriter.write(towrite);
                        allstring = allstring + towrite;
                    }
                }
            }

            //try {aFileWriter.close();} catch (IOException e) {}
            File theAllTXTFile = new File(onMac + "CubisimaCB - AllCasas.csv");
            //"C:/Documents and Settings/fsimon0/My Documents/Cubisima Final/CubisimaCB - AllCasas.csv"
            FileWriter allFileWriter = new FileWriter(theAllTXTFile);
            allFileWriter.write(headers);
            allFileWriter.write(allstring);
        }
    }
}
