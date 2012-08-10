
package Test;
import Permutas.Permutas_Main;
import java.awt.Button;
import java.awt.TextField;
import java.io.*;
import org.htmlparser.NodeFilter;
import org.htmlparser.beans.FilterBean;
import org.htmlparser.filters.HasAttributeFilter;
import java.awt.event.*;
import java.lang.Object;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Enumeration;
import java.util.Hashtable;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class Cubisima_Permutas1 extends Frame {
    
    public static myPauser myWindow = new myPauser("Pauser Window");
    public static String headers =
                "Codigo, Type, "
                //<editor-fold>
                + "Title (Have)_1, TipoLocal(Have)_1, Rooms (Have),"
                + "# Rooms _1, # Bathrooms _1, Services(Have),"; //...
                //</editor-fold>
    
    public static void main (String[] args) throws IOException
    {
        Permutas_Main rvp = new Permutas_Main ();
        rvp.textCreator();
        //String theURL = "file://localhost/Users/federicocsimon/Dropbox/CubaHousing/CubaData/Cubisima/"
        //+"02Dec2011 - CB/permutas/anuncios";        
    }
//  
    static class myPauser extends Frame implements WindowListener,ActionListener {
        TextField text = new TextField(25);
        Button tenmin;
        
        private int onOffSwitch = 0;
        private int numMinutes = 0;

        void main(String[] args) {
            System.out.println("myPauser Main Called");
            myWindow.setSize(350,150);
            myWindow.setVisible(true);
        }

        public myPauser(String title) {

            super(title);
            setLayout(new FlowLayout());
            addWindowListener(this);
            tenmin = new Button("Pause For 10 Minutes");

            add(text);
            add(tenmin);
            tenmin.addActionListener(this);
        }

    @Override
        public void actionPerformed(ActionEvent e) {
        
            Calendar calendar = new GregorianCalendar();
            
            int hour = calendar.get(Calendar.HOUR);
            int minute = calendar.get(Calendar.MINUTE);
            int newminute = minute+10;
            int second = calendar.get(Calendar.SECOND);
            
            String newtime = ""+hour+" : "+newminute+" : "+second;

            text.setText("Will Unpause at: " + newtime);
                
            try {
                Thread.sleep(600000);
            } catch (InterruptedException ex) {
                Logger.getLogger(myPauser.class.getName()).log(Level.SEVERE, null, ex);
            }
            text.setText("Program is Running Again");    
        }

    @Override
        public void windowClosing(WindowEvent e) {
            dispose();
            System.exit(0);
        }

    @Override
        public void windowOpened(WindowEvent e) {}
    @Override
        public void windowActivated(WindowEvent e) {}
    @Override
        public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
        public void windowDeactivated(WindowEvent e) {}
    @Override
        public void windowClosed(WindowEvent e) {}

}
        
    public String myStringExtract(String theURL)
    {
        Permutas_Main fixer = new Permutas_Main();

        
        //TENGO 3
            //1.- Location & tipo
        //<editor-fold>
        FilterBean locF3 = new FilterBean();
        HasAttributeFilter locA3 = new HasAttributeFilter("id", "ctl01_LabelTitulo3");
        locF3.setFilters(new NodeFilter[] {locA3});
        locF3.setURL(theURL);
        String hLocS3 = locF3.getText();
        hLocS3 = fixer.accentFix(hLocS3);
        hLocS3 = hLocS3.replace("DIRECCIÓN","");
        //</editor-fold>

            //2.- Habitaciones
        //<editor-fold>
        FilterBean habF3 = new FilterBean();
        HasAttributeFilter habA3 = new HasAttributeFilter("id", "ctl01_LabelHabitaciones3");
        habF3.setFilters(new NodeFilter[] {habA3});
        habF3.setURL(theURL);
        String habS3 = habF3.getText();
        if (habS3.endsWith(","))
            habS3 = habS3.substring(0, habS3.length() - 1);
        /* Another way to write same thing if I decide to include "." and other characters.
         * String str = "whatever";
         * str = str.replaceAll('(,)?(.)?(,)?', '');
         */
        habS3 = habS3.replace("½",".5");

        
        String roomHD3 = "0";
        String bathrHD3 = "0";
        
        String habSEdited3 = habS3;
        habSEdited3 = habSEdited3.replace("cuartos","");
        habSEdited3 = habSEdited3.replace("cuarto","");
        habSEdited3 = habSEdited3.trim();
        
        String [] habSEditedArr3 = habSEdited3.split(",");
        if (habSEditedArr3.length > 1){
            roomHD3 = habSEditedArr3[0];
            bathrHD3 = habSEditedArr3[1];
        }
       
        //</editor-fold>
        
            //3.- Services
        //<editor-fold>
        FilterBean qserF3 = new FilterBean();
        HasAttributeFilter qserA3 = new HasAttributeFilter("id", "ctl01_LabelServicios6");
        qserF3.setFilters(new NodeFilter[] {qserA3});
        qserF3.setURL(theURL);
        String qserS3 = qserF2.getText();
        String qserSalaComW_3 = "0", qserSalaW_3 = "0", serComedorW_3 = "0", 
                serCocinaW_3 = "0", serCocinaComW_3 = "0", serAzotLibW_3 = "0";
        
        //<editor-fold>
        if (qserS3.contains("sala-comedor")) qserSalaComW_3 = "1";
        if (qserS3.contains("sala ")) qserSalaW_3 = "1";
        if (qserS3.contains(" comedor")) serComedorW_3 = "1";
        if (qserS3.contains("cocina")) serCocinaW_3 = "1";

        //</editor-fold>
        
            //5.- Quality (TENGO)
        FilterBean qqualF3 = new FilterBean();
        HasAttributeFilter qqualA3 = new HasAttributeFilter("id", "ctl01_LabelEstadoVivienda6");
        qqualF3.setFilters(new NodeFilter[] {qqualA3});
        qqualF3.setURL(theURL);
        String qqualS3 = qqualF3.getText();
        qqualS3 = qualityreplacer(qqualS3);

        
        //7.- Side Payments Dummy
        String sidepaydummyGive = "0";
        String othSlow = othS.toLowerCase();

        if ((othSlow.contains("vuelto") ||hLocS2low.contains("vuelto")||hLocS3low.contains("vuelto")) && (
                othSlow.contains("doy") ||hLocS2low.contains("doy")||hLocS3low.contains("doy"))){  
            sidepaydummyGive ="1";
        }
        
        String [] retArr = new String[] {code , type ,
            hLocS , htipLocS, habS , roomHD, bathrHD,
            cnS , ownS , addrS , habanaDummy, phnS , emlS , 
            othS, sidepaydummyGive, sidepaydummyReceive, 
            sidepayUnspecifiedDirection};
        
        
        String retStr = "";
        
        for (int i=0;i<retArr.length;i++){
            retArr[i] = retArr[i].replace(","," y");
            retArr[i] = fixer.accentFix(retArr[i]);
            retArr[i] = retArr[i].toLowerCase();
            if (retArr[i].equals(""))
                retArr[i] = "0";
            retStr = retStr+retArr[i]+",";
        } 
        return retStr;
    }
    
    public String accentFix(String str)
    {
        str = Normalizer.normalize(str, Normalizer.Form.NFD); 
        str = str.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        str = str.trim(); //removes trailing and leading whitespace
        return str;
        
        /*
         * Above does the work of:
         * a = a.replace("ñ", "n");
         * a = a.replace("Ñ", "N");
         * a = a.replace("ó", "o");
         * a = a.replace("á", "a");
         * etc...
         */
    }


public class CodigoDater {
    public static void main (String[] args) {
        String fn = "http://www.cubisima.com/permutas/buscador.html";
        FilterBean codeFilter = new FilterBean();
        codeFilter.setURL(fn);
        String code = codeFilter.getText();

        String [] codearray = code.split("\n");
        
        Hashtable<String,String> hasht1 = new Hashtable<String, String>();
        
        for (int j=0; j<codearray.length-1;j++){
            
            String cod1="";
            String cod2="";
            
            if(codearray[j].contains("Código:")){ 
                //System.out.println("in if statement"+codearray[j]);
                cod1 = codearray[j].replace("Código: ", "");
                String[] splitt = cod1.split("\\|");
                //System.out.println("splitt0  "+splitt[0]);
                String key = splitt[0].trim();
                //System.out.println("splitt1  "+splitt[1]);
                String object = splitt[1].replace("Publicado: ", "").trim();
                //System.out.println("putting:"+key+"with"+object);
                hasht1.put(key, object);
            }
        }
    }
}
    public String qualityreplacer (String a){
        a=a.replace("optimo | muy bueno | bueno", "3");
        a=a.replace("regular","2");
        a=a.replace("malo","1");
        a = a.replace("ESTADO FÍSICO DE LA VIVIENDA: ","");
        a=a.replace("reparable","");
        return a;
    }
    
    public void textCreator () throws IOException
    {
        Permutas_Main rvp1 = new Permutas_Main ();

        // Declare some variables
        String date = "";
        String towrite = "";
        String path = "";
        
        
        // GET ROOT FILE
        File dir1 = new File("/Users/federicocsimon/Dropbox/CubaHousing/CubaData/Cubisima/");
        String[] datefolderChildren = dir1.list();
        
        String allstring = "";
        
        // GET ALL date FOLDERS
        for (int i=1; i< datefolderChildren.length; i++){
            if (true/*!datefolderChildren[i].contains("NCB")*//*&& !children1[i].contains("CB?")*/){
                date = datefolderChildren[i]; 
                // APPLY ALL ALGORITHM TO ALL datefolders
                String enclosing = "/Users/federicocsimon/Dropbox/CubaHousing/CubaData/Cubisima/"+date+"/permutas/anuncios/";
                File dir = new File(enclosing);
                String[] individualHTMLChildren = dir.list();

                File theTXTFile = new File ("/Users/federicocsimon/Dropbox/CubaHousing/CubaData/Cubisima/"+date+"/Cubisima -"+date+" - Permutas.csv");
                FileWriter aFileWriter = null;
                try {aFileWriter = new FileWriter(theTXTFile);} catch (IOException e1) {}

                
                if (aFileWriter != null) {try {aFileWriter.write(headers);} catch (IOException e) {}}    

                for (int j=0; j< individualHTMLChildren.length; j++){
                    String fn = "file://localhost" + enclosing + individualHTMLChildren[j];
                    System.out.println("filename:"+fn);

                    if (aFileWriter != null) {
                        try {
                                path = individualHTMLChildren[j].replace(",", ";");
                                towrite = rvp1.myStringExtract(fn) + path + "\n";
                                aFileWriter.write(towrite);
                                allstring = allstring + towrite;
                            } catch (IOException e) {}}
                }
                try {aFileWriter.close();} catch (IOException e) {}
            }
        }
        File theAllTXTFile = new File ("/Users/federicocsimon/Dropbox/CubaHousing/CubaData/CubisimaCB - AllPermutas.csv");
        FileWriter allFileWriter = null;
        try {allFileWriter = new FileWriter(theAllTXTFile);} catch (IOException e1) {}
        allFileWriter.write(headers);
        allFileWriter.write(allstring);
    }
}
