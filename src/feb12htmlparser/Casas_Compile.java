// THIS FILE AGGREGATES INDIVDUAL SCRAPES INTO TOTAL SHEET ALLCASAS.CSV


package feb12htmlparser;

import java.io.*;
import java.util.Scanner;
import java.util.Hashtable;


public class Casas_Compile {
    
    public static String onMac = "/Users/fsimon/Desktop/ADAIR/CubaData/";
    public static String onPC = "C:/Documents and Settings/fsimon0/My Documents/CubaData Original/";
    
    public static String headers = "Apartamento Dummy, Casa Dummy, "
            + "Terreno Dummy, Propiedad Horizontal Dummy, Penthouse Dummy"
            + ", Rooms, Bathrooms, Precio (They are all cuc), Suspicious Price dummy,"
            + "Metros,Cantidad de Personas, Estado Fisico,Direccion,"
            + "Observaciones, School, Park, Beach,"

            + "Sala,Patio,Agua,Comedor,Portal,"
            + "Carposhe,Cocina,Jardin,Azotea Compartida,Sala-comedor,"
            + "Piscina,Hall,Cocina-comedor,Garaje,Saleta,Barbacoa,"
            + "Posibilidad de Garaje,Pasillo,Azotea Libre,"
            + "Puntal Alto,Puerta Calle,Elevador,Telefono,Tanque Instalado,"
            + "Balcon,Gas de la Calle,Patinejo,Terraza,Gas de Balon \n";
    
    public static void main (String[] args) throws FileNotFoundException, IOException
    {
        String allString = "";
        File dir1 = new File(onMac);
            //"C:/Documents and Settings/fsimon0/My Documents/Cubisima Final/"
        
        String[] datefolderChildren = dir1.list();
        
        for (int i=0; i< datefolderChildren.length; i++){
            if(!datefolderChildren[i].startsWith(".") && !datefolderChildren[i].endsWith(".csv")){
                String date = datefolderChildren[i];
                String enclosing = onMac + date;
                    //System.out.println(enclosing);
                    //"C:/Documents and Settings/fsimon0/My Documents/Cubisima Final/"+date;
                File dir = new File(enclosing);
                    System.out.println("enclosing:" + enclosing);
                String[] individualHTMLChildren = dir.list();
                
                if(individualHTMLChildren!=null){
                    for (int j =0; j<individualHTMLChildren.length; j++){
                        String oneDateFile = "";
                        if (individualHTMLChildren[j].contains("Casas.csv")){
                            System.out.println("here" + individualHTMLChildren[j]);
                            String fn = onMac + datefolderChildren[i] + "/" + individualHTMLChildren[j] + "/";
                            //"C:/Documents and Settings/fsimon0/My Documents/Cubisima Final/"+datefolderChildren[i]+"/"+individualHTMLChildren[j]+"/"
                            BufferedReader areader = new BufferedReader(new FileReader(fn));
                            String sCurrentLine;
                            while ((sCurrentLine = areader.readLine()) != null) {
                                if(!sCurrentLine.contains("Dummy")){
                                    oneDateFile = oneDateFile + sCurrentLine + "\n";}
                            }
                            allString = allString + oneDateFile;
                            //System.out.println(oneDateFile);
                        }
                        else{continue;}
                    }
                }
            }
        }
        //System.out.println("AllString" + allString);
        //"C:/Documents and Settings/fsimon0/My Documents/AllCasas.csv"
        File theAllTXTFile = new File (onMac + "/AllCasas.csv");
        FileWriter allFileWriter = null;
        try {allFileWriter = new FileWriter(theAllTXTFile);} catch (IOException e1) {}
        allFileWriter.write(headers);
        allFileWriter.write(allString);
    }
}