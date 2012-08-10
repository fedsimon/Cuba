
package Test;

import java.io.*;

public class OrganizerCB {
    
    public static void main (String[] args) throws IOException
    {
        // Declare some variables
        String date = "";
        String headers = "";
        String towrite = "";
        String path = "";
        
        // GET ROOT FILE
        File dir1 = new File("/Users/federicocsimon/Dropbox/CubaHousing/CubaData/Cubisima/");
        String[] datefolderChildren = dir1.list();
        
        String allstring = "";
        
        // GET ALL date FOLDERS
        for (int i=1; i< datefolderChildren.length; i++){
            if (!datefolderChildren[i].contains("NCB")/*&& !children1[i].contains("CB?")*/){
                date = datefolderChildren[i]; 
                // APPLY ALL ALGORITHM TO ALL datefolders
                String enclosing = "/Users/federicocsimon/Dropbox/CubaHousing/CubaData/Cubisima/"+date+"/casas/";
                File dir = new File(enclosing);
                String[] individualHTMLChildren = dir.list();
                //System.out.println("THISTHISTHIS:" + children[1]);


                File theTXTFile = new File ("/Users/federicocsimon/Dropbox/CubaHousing/CubaData/Cubisima/"+date+"/Cubisima -"+date+" - Casas.csv");
                FileWriter aFileWriter = null;
                try {aFileWriter = new FileWriter(theTXTFile);} catch (IOException e1) {}

                headers = "Rooms, Bathrooms,"
                        + "Precio (They are all cuc), Suspicious Price dummy,"
                        + "Metros,Cantidad de Personas, Estado Fisico,Direccion,"
                        + "Observaciones, School, Park, Beach,"
                        + "Sala,Patio,Agua,Comedor,Portal,"
                        + "Carposhe,Cocina,Jardin,Azotea Compartida,Sala-comedor,"
                        + "Piscina,Hall,Cocina-comedor,Garaje,Saleta,Barbacoa,"
                        + "Posibilidad de Garaje,Pasillo,Azotea Libre,"
                        + "Puntal Alto,Puerta Calle,Elevador,Telefono,Tanque Instalado,"
                        + "Balcon,Gas de la Calle,Patinejo,Terraza,Gas de Balon \n";
                
                if (aFileWriter != null) {try {aFileWriter.write(headers);} catch (IOException e) {}}

                // Create algorithm object
                CubisimaCB cwcn = new CubisimaCB ();    

                for (int j=0; j< individualHTMLChildren.length; j++){
                    String fn = "file://localhost" + enclosing + individualHTMLChildren[j];

                    // Call left on actual website
                    String[] leftret = cwcn.left(fn);
                    // there are some empty sites that add a line but are actually empty and ruin data set.
                    if (leftret == null){System.out.println("leftret == null");continue;}

                    //Call middle on actual website
                    String midret = cwcn.middle(fn);
                    if (midret == null){System.out.println("midret == null");continue;}

                    //Get the characteristics
                    String [] characteristicList = new String[29];
                    //Set all of the above array to NO
                    for (int k = 0; k<= 28; k++){characteristicList[k] = "0";}

                    //Individual Characteristics
                    if (midret.contains("sala")) characteristicList[0] = "1";
                    if (midret.contains("patio")) characteristicList[1] = "1";
                    if (midret.contains("agua las 24 horas")) characteristicList[2] = "1";
                    if (midret.contains("comedor")) characteristicList[3] = "1";
                    if (midret.contains("portal")) characteristicList[4] = "1";
                    if (midret.contains("carposhe")) characteristicList[5] = "1";
                    if (midret.contains("cocina")) characteristicList[6] = "1"; 
                    if (midret.contains("jardin")) characteristicList[7] = "1"; 
                    if (midret.contains("azotea compartida")) characteristicList[8] = "1";
                    if (midret.contains("sala-comedor")) characteristicList[9] = "1";
                    if (midret.contains("piscina")) characteristicList[10] = "1";
                    if (midret.contains("hall")) characteristicList[11] = "1";
                    if (midret.contains("cocina-comedor")) characteristicList[12] = "1";
                    if (midret.contains("garaje")) characteristicList[13] = "1";
                    if (midret.contains("saleta")) characteristicList[14] = "1";
                    if (midret.contains("barbacoa")) characteristicList[15] = "1";
                    if (midret.contains("posibilidad de garaje")) characteristicList[16] = "1";
                    if (midret.contains("pasillo")) characteristicList[17] = "1";
                    if (midret.contains("azotea libre")) characteristicList[18] = "1";
                    if (midret.contains("puntal alto")) characteristicList[19] = "1"; 
                    if (midret.contains("puerta calle")) characteristicList[20] = "1";
                    if (midret.contains("elevador")) characteristicList[21] = "1";
                    if (midret.contains("telefono")) characteristicList[22] = "1";
                    if (midret.contains("tanque instalado")) characteristicList[23] = "1";
                    if (midret.contains("balcon")) characteristicList[24] = "1";
                    if (midret.contains("gas de la calle")) characteristicList[25] = "1";
                    if (midret.contains("patinejo")) characteristicList[26] = "1";
                    if (midret.contains("terraza")) characteristicList[27] = "1";
                    if (midret.contains("gas de balon")) characteristicList[28] = "1";

                    String midchar = "";
                    for (i=0;i<characteristicList.length;i++) {midchar = characteristicList[i] +",";}
                            
                            

                    String leftchar = "";
                    for (int n=0; n<leftret.length; n++){
                        leftchar = leftchar + leftret[n] + ",";
                        System.out.println(leftchar);
                    }

                    if (aFileWriter != null) {
                        try {
                            if(leftret!=null)
                                path = individualHTMLChildren[j].replace(",", ";");
                                towrite = leftchar + midchar + path + "\n";
                                aFileWriter.write(towrite);
                                allstring = allstring + towrite;
                            } catch (IOException e) {}}
                }
                try {aFileWriter.close();} catch (IOException e) {}
            }
        }
        File theAllTXTFile = new File ("/Users/federicocsimon/Dropbox/CubaHousing/CubaData/CubisimaCB - AllCSV.csv");
        FileWriter allFileWriter = null;
        try {allFileWriter = new FileWriter(theAllTXTFile);} catch (IOException e1) {}
        allFileWriter.write(headers);
        allFileWriter.write(allstring);
    }
}

