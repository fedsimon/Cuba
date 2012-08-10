
package Test;
import org.htmlparser.NodeFilter;
import org.htmlparser.beans.FilterBean;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasChildFilter;
import org.htmlparser.filters.TagNameFilter;

public class CubisimaCBP {
    
    public static void main (String[] args)
    {
            CubisimaCBP cbp = new CubisimaCBP ();

            FilterBean casa = new FilterBean ();
            HasAttributeFilter casaAttribute = new HasAttributeFilter ("id", "renta_detalles_confoto_izquierda");
            casa.setFilters(new NodeFilter[] {casaAttribute});
            casa.setURL ("file://localhost/Users/federicocsimon/Desktop/ADAIR/Jan14/casas/1-cuc-apartamento-de-2-cuarto-en-ciudad-habana-10-de-octubre-vibora!582.htm");
            String casa_s = casa.getText();
            System.out.println(casa_s);
        if(casa_s.length() != 0) System.out.println("HAS A PICTURE");


    }
/*	public String[] left (String theFileName) {

            ////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\
            // CASA
            FilterBean casa = new FilterBean ();
            TagNameFilter casaTNF = new TagNameFilter("span");
            HasAttributeFilter casaAttribute = new HasAttributeFilter ("id", "ctl00_MainPlaceHolder_LabelBasicInfo");
            AndFilter casaAND = new AndFilter (casaTNF, casaAttribute);
            casa.setFilters(new NodeFilter[] {casaAND});
            casa.setURL (theFileName);
            String casa_s = casa.getText();
            //System.out.println(casa_s);

            /////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\
            // PRECIO
            FilterBean precio = new FilterBean ();
            TagNameFilter precioTNF = new TagNameFilter("span");
            HasAttributeFilter precioAttribute = new HasAttributeFilter ("id", "ctl00_MainPlaceHolder_LabelPrecio0");
            AndFilter precioAND = new AndFilter (precioTNF, precioAttribute);
            precio.setFilters(new NodeFilter[] {precioAND});
            precio.setURL (theFileName);
            String precio_s = precio.getText();
            //System.out.println(precio_s);

            ////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\
            // METROS
            FilterBean metros = new FilterBean ();
            TagNameFilter metrosTNF = new TagNameFilter("span");
            HasAttributeFilter metrosAttribute = new HasAttributeFilter ("id", "ctl00_MainPlaceHolder_LabelMetros0");
            AndFilter metrosAND = new AndFilter (metrosTNF, metrosAttribute);
            metros.setFilters(new NodeFilter[] {metrosAND});
            metros.setURL (theFileName);
            String metros_s = metros.getText();
            //System.out.println(metros_s);

            ///////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
            // CANTIDAD DE PERSONAS
            FilterBean cpersonas = new FilterBean ();
            TagNameFilter cpTNF = new TagNameFilter("span");
            HasAttributeFilter cpAttribute = new HasAttributeFilter ("id", "ctl00_MainPlaceHolder_LabelCantPers0");
            AndFilter cpAND = new AndFilter (cpTNF, cpAttribute);
            cpersonas.setFilters(new NodeFilter[] {cpAND});
            cpersonas.setURL (theFileName);
            String cp_s = cpersonas.getText();
            //System.out.println(cp_s);

            //////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
            // ESTADO
            FilterBean estado = new FilterBean ();
            TagNameFilter estadoTNF = new TagNameFilter("span");
            HasAttributeFilter estadoAttribute = new HasAttributeFilter ("id", "ctl00_MainPlaceHolder_LabelEstado0");
            AndFilter estadoAND = new AndFilter (estadoTNF, estadoAttribute);
            estado.setFilters(new NodeFilter[] {estadoAND});
            estado.setURL (theFileName);
            String estado_s = estado.getText();
            //System.out.println(estado_s);

            /////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
            // DIRECCION
            FilterBean direccion = new FilterBean ();
            TagNameFilter dTNF = new TagNameFilter("span");
            HasAttributeFilter dAttribute = new HasAttributeFilter ("id", "ctl00_MainPlaceHolder_LabelDireccion0");
            AndFilter dAND = new AndFilter (dTNF, dAttribute);
            direccion.setFilters(new NodeFilter[] {dAND});
            direccion.setURL (theFileName);
            String d_s = direccion.getText();
            //System.out.println(d_s);

            ///////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\
            // OBSERVACIONES
            FilterBean observaciones = new FilterBean ();
            TagNameFilter oTNF = new TagNameFilter("span");
            HasAttributeFilter oAttribute = new HasAttributeFilter ("id", "ctl00_MainPlaceHolder_LabelObservaciones0");
            AndFilter oAND = new AndFilter (oTNF, oAttribute);
            observaciones.setFilters(new NodeFilter[] {oAND});
            observaciones.setURL (theFileName);
            String o_s = observaciones.getText();
            //System.out.println(o_s);

            ///////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\
            String[] returnlist = new String[] {casa_s, precio_s, metros_s, cp_s, estado_s, d_s, o_s};
            System.out.println(theFileName);
            System.out.println("RETURNLIST" + returnlist[0] + returnlist[1] + returnlist[2] + returnlist[3]);
            return returnlist;
    }*/


    public String middle (String theFileName){
            // Set Filters
            TagNameFilter theTD = new TagNameFilter("td");
            HasAttributeFilter theIMG = new HasAttributeFilter ("src", "http://www.cubisima.com/Images/checked.png");
            HasChildFilter childOfTD = new HasChildFilter (theIMG);
            AndFilter both = new AndFilter (theTD, childOfTD);

            // Set FilterBean
            FilterBean fb = new FilterBean ();
            fb.setFilters(new NodeFilter[] {both});
            fb.setURL (theFileName);
            String s = fb.getText();
            System.out.println("\n");
            //System.out.println("midstart" + s);

            return s;
    }
}
