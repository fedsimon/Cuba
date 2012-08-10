
package Test;
import org.htmlparser.filters.*;
import org.htmlparser.beans.*;
import org.htmlparser.NodeFilter;

public class CubisimaCB {

    public static void main (String[] args){
    	CubisimaCB cwcn = new CubisimaCB ();
        String fn = "file://localhost/Users/federicocsimon/Desktop/ADAIR/Jan14/casas/Casas.11Jan2012.1-cuc-apartamento-de-2-cuartos-en-ciudad-habana-la-lisa!1581";
        
        //StringBean att = new StringBean();
        //att.setURL("Casas.11Jan2012.1-cuc-apartamento-de-2-cuartos-en-ciudad-habana-la-lisa!1581");
        
    	cwcn.left(fn);
    	cwcn.middle(fn);
    }

    public String[] left (String theFileName) {

        ////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\
        // CASA
        FilterBean casa = new FilterBean ();
        TagNameFilter casaTNF = new TagNameFilter("span");
        HasAttributeFilter casaAttribute = new HasAttributeFilter ("id", "ctl00_MainPlaceHolder_LabelBasicInfo0");
        HasAttributeFilter casaAttributeWP = new HasAttributeFilter ("id", "ctl00_MainPlaceHolder_LabelBasicInfo");
        OrFilter anOrFilter = new OrFilter(casaAttribute, casaAttributeWP);
        AndFilter casaAND = new AndFilter (casaTNF, anOrFilter);
        casa.setFilters(new NodeFilter[] {casaAND});
        casa.setURL (theFileName);
        String casa_s = casa.getText();
        casa_s = casa_s.replace(",", "-");
        casa_s = casa_s.replace("\n", ";");
        casa_s = casa_s.replace("Apartamento", "");
        casa_s = casa_s.replace("½", ".5");
        casa_s = casa_s.replace("cuartos", "");
        casa_s = casa_s.replace("cuarto", "");
        casa_s = casa_s.replace("Habitacion", "");
        casa_s = casa_s.replace("Habitaciones", "");
        casa_s = casa_s.replace("Habitación", "");
        casa_s = casa_s.replace("Habitaciónes", "");
        casa_s = casa_s.replace("casa", "");
        casa_s = casa_s.replace("Casa", "");
        casa_s = casa_s.replace("cuartos", "");
        casa_s = casa_s.replace("terreno", "");
        casa_s = casa_s.replace("?", "");
        casa_s = casa_s.replace("baños", "");
        casa_s = casa_s.replace("baño", "");
        System.out.println("jkjb"+casa_s);
        String rooms_s = "0";
        String bathrooms_s = "0";
        String[] smth = casa_s.split("-");
        rooms_s = smth[0];
            if (smth.length > 1)
                bathrooms_s = smth[1];
        

        /////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\
        // PRECIO
        FilterBean precio = new FilterBean ();
        TagNameFilter precioTNF = new TagNameFilter("span");
        HasAttributeFilter precioAttribute = new HasAttributeFilter ("id", "ctl00_MainPlaceHolder_LabelPrecio0");
        HasAttributeFilter precioAttributeWP = new HasAttributeFilter ("id", "ctl00_MainPlaceHolder_LabelPrecio");
        OrFilter precioOR = new OrFilter(precioAttribute, precioAttributeWP);		
        AndFilter precioAND = new AndFilter (precioTNF, precioOR);
        precio.setFilters(new NodeFilter[] {precioAND});
        precio.setURL (theFileName);
        String precio_s = precio.getText();
        precio_s = precio_s.replace(",", ";");
        precio_s = precio_s.replace("\n", "");
        precio_s = precio_s.replace("Precio:", "");
        precio_s = precio_s.replace("cuc", "");
        String precio_sdummy = "";
        int precio_i=0;
        System.out.println("hbhjbh" + precio_s);
        precio_s = precio_s.trim();
        String precio_a = precio_s;
        precio_a = precio_a.replaceAll("\\D+","");
        if (!precio_a.equals("")) precio_i = java.lang.Integer.parseInt(precio_a);
            
        if ((precio_i < 100) || (precio_s.equals(""))|| precio_s.equals(" "))
            precio_sdummy = "1";
        System.out.println("precios_s" + precio_s);

        ////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        // METROS
        FilterBean metros = new FilterBean ();
        TagNameFilter metrosTNF = new TagNameFilter("span");
        HasAttributeFilter metrosAttribute = new HasAttributeFilter ("id", "ctl00_MainPlaceHolder_LabelMetros0");
        HasAttributeFilter metrosAttributeWP = new HasAttributeFilter ("id", "ctl00_MainPlaceHolder_LabelMetros");
        OrFilter metrosOR = new OrFilter (metrosAttribute, metrosAttributeWP);
        AndFilter metrosAND = new AndFilter (metrosTNF, metrosOR);
        metros.setFilters(new NodeFilter[] {metrosAND});
        metros.setURL (theFileName);
        String metros_s = metros.getText();
        metros_s = metros_s.replace(",", ";");
        metros_s = metros_s.replace("\n", "");
        metros_s = metros_s.replace("Metros²:", "");
        metros_s = metros_s.replace("-", "0");
        System.out.println("metros_s" + metros_s);

        ///////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        // CANTIDAD DE PERSONAS
        FilterBean cpersonas = new FilterBean ();
        TagNameFilter cpTNF = new TagNameFilter("span");
        HasAttributeFilter cpAttribute = new HasAttributeFilter ("id", "ctl00_MainPlaceHolder_LabelCantPers0");
        HasAttributeFilter cpAttributeWP = new HasAttributeFilter ("id", "ctl00_MainPlaceHolder_LabelCantPers");
        OrFilter cpOR = new OrFilter (cpAttribute, cpAttributeWP);
        AndFilter cpAND = new AndFilter (cpTNF, cpOR);
        cpersonas.setFilters(new NodeFilter[] {cpAND});
        cpersonas.setURL (theFileName);
        String cp_s = cpersonas.getText();
        cp_s = cp_s.replace(",", ";");
        cp_s = cp_s.replace("-", "0");
        cp_s = cp_s.replace("\n", ";");
        cp_s = cp_s.replace("Cantidad de personas que pueden vivir:", "");
        System.out.println("cp_s" + cp_s);

        //////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        // ESTADO
        FilterBean estado = new FilterBean ();
        TagNameFilter estadoTNF = new TagNameFilter("span");
        HasAttributeFilter estadoAttribute = new HasAttributeFilter ("id", "ctl00_MainPlaceHolder_LabelEstado0");
        HasAttributeFilter estadoAttributeWP = new HasAttributeFilter ("id", "ctl00_MainPlaceHolder_LabelEstado");
        OrFilter estadoOR = new OrFilter (estadoAttribute,estadoAttributeWP);
        AndFilter estadoAND = new AndFilter (estadoTNF, estadoOR);
        estado.setFilters(new NodeFilter[] {estadoAND});
        estado.setURL (theFileName);
        String estado_s = estado.getText();
        estado_s = estado_s.replace(",", ";");
        estado_s = estado_s.replace("\n", ";");
        estado_s = estado_s.replace("Estado físico:", "");
        estado_s = estado_s.replace("Ó", "O");
        estado_s = estado_s.replace("ó", "o");
        estado_s = estado_s.replace("-", "None");
        System.out.println("estado_s" + estado_s);

        /////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        // DIRECCION
        FilterBean direccion = new FilterBean ();
        TagNameFilter dTNF = new TagNameFilter("span");
        HasAttributeFilter dAttribute = new HasAttributeFilter ("id", "ctl00_MainPlaceHolder_LabelDireccion0");
        HasAttributeFilter dAttributeWP = new HasAttributeFilter ("id", "ctl00_MainPlaceHolder_LabelDireccion");
        OrFilter dOr = new OrFilter (dAttribute, dAttributeWP);
        AndFilter dAND = new AndFilter (dTNF, dOr);
        direccion.setFilters(new NodeFilter[] {dAND});
        direccion.setURL (theFileName);
        String d_s = direccion.getText();
        d_s = d_s.replace(",", ";");
        d_s = d_s.replace("\n", ";");
        d_s = d_s.replace("Dirección: ", "");
        System.out.println("d_s" + d_s);

        ///////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\
        // OBSERVACIONES
        FilterBean observaciones = new FilterBean ();
        TagNameFilter oTNF = new TagNameFilter("span");
        HasAttributeFilter oAttribute = new HasAttributeFilter ("id", "ctl00_MainPlaceHolder_LabelObservaciones0");
        HasAttributeFilter oAttributeWP = new HasAttributeFilter ("id", "ctl00_MainPlaceHolder_LabelObservaciones");
        OrFilter oOr = new OrFilter (oAttribute,oAttributeWP);
        AndFilter oAND = new AndFilter (oTNF, oOr);
        observaciones.setFilters(new NodeFilter[] {oAND});
        observaciones.setURL (theFileName);
        String o_s = observaciones.getText();
        System.out.println("o_s" + o_s);
        o_s = o_s.replace(",", ";");
        o_s = o_s.replace("\n", ";");
        o_s = o_s.replace("Observaciones: ", "");
        o_s = o_s.replace("-", "0");
        String sch_s = "0";
        String pk_s = "0";
        String bch_s = "0";
        if ((o_s.contains("escuela")) || (o_s.contains("Escuela"))) sch_s="1";
        if ((pk_s.contains("parque")) || (o_s.contains("Parque"))) pk_s="1";  
        if ((bch_s.contains("playa")) || (o_s.contains("Playa"))) bch_s="1";
        
        ///////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\
        String[] returnlist = new String[] {rooms_s, bathrooms_s, precio_s, 
            precio_sdummy, metros_s, cp_s, estado_s, d_s, o_s,
            sch_s, pk_s, bch_s};
        //System.out.println(theFileName);
        //System.out.println("RETURNLIST" + returnlist[0] + returnlist[1] + returnlist[2] + returnlist[3]);
        if (casa_s.contains("Terreno") || casa_s.contains("Prop"))
            return null;
        return returnlist;
    }


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
        System.out.println("midstart" + s);

        return s;
    }
}