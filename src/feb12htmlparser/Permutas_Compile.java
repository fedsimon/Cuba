package feb12htmlparser;

import java.io.*;
import java.util.Scanner;
import java.util.Hashtable;


public class Permutas_Compile {
    
    public static String onMac = "/Users/fsimon/Desktop/ADAIR/CubaData/";
    public static String onPC = "C:/Documents and Settings/fsimon0/My Documents/CubaData Original/";
    
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
                +"Title (Have), TipoLocal(Have)_2, Rooms_2 (Have),"
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
                +"Title (Have), TipoLocal (Have)_3, Rooms_3 (Have),"
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
            + "Side Payments Give Dummy, Side Payments Receive Dummy, Side Payments In Unspecified Direction Dummy  \n";
                //</editor-fold>

public static void main (String[] args) throws FileNotFoundException, IOException
    {
        String allString = "";
        File dir1 = new File(onMac);
            //"C:/Documents and Settings/fsimon0/My Documents/Cubisima Final/"
        
        String[] datefolderChildren = dir1.list();
        for (int i=0; i< datefolderChildren.length; i++){
            if(!datefolderChildren[i].equals("03Feb2012 - CB")){
                String date = datefolderChildren[i]; 
                String enclosing = onMac+date;
                    //"C:/Documents and Settings/fsimon0/My Documents/Cubisima Final/"
                File dir = new File(enclosing);
                String[] individualHTMLChildren = dir.list();
                //System.out.println("indivasdasda" + individualHTMLChildren[0]);
                if(individualHTMLChildren!=null){
                    for (int j =0; j<individualHTMLChildren.length; j++){
                        String oneDateFile = "";
                        if (individualHTMLChildren[j].contains("Permutas.csv")){
                            System.out.println(individualHTMLChildren[j]);
                            String fn = onMac+datefolderChildren[i]+"/"+individualHTMLChildren[j]+"/";
                                //"C:/Documents and Settings/fsimon0/My Documents/Cubisima Final/"+datefolderChildren[i]+"/"+individualHTMLChildren[j]+"/";
                            BufferedReader areader = new BufferedReader(new FileReader(fn));
                            String sCurrentLine;
                            while ((sCurrentLine = areader.readLine()) != null) {
                                if(!sCurrentLine.contains("Dummy")){
                                    oneDateFile = oneDateFile+sCurrentLine+"\n";}
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
        File theAllTXTFile = new File (onMac + "/AllPermutas.csv");
            //"C:/Documents and Settings/fsimon0/My Documents/AllPermutas.csv"
        FileWriter allFileWriter = null;
        try {allFileWriter = new FileWriter(theAllTXTFile);} catch (IOException e1) {}
        allFileWriter.write(headers);
        allFileWriter.write(allString);
    }
}