package subsistemas;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.rowset.BaseRowSet;

import bean.BaseEstadistica;
import bean.Factura;
import bean.Menu;
import bean.Plato;
import interfaces.ItfGestorDatos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GestionDatosImpl implements ItfGestorDatos {

	public GestionDatosImpl() {
		super();
	}
	
	/*Obtener platos de una concesionaria*/
	@Override
	public ArrayList<Plato> obtenerPlatos(String concesionaria) {
		ArrayList<Plato> platos = new ArrayList<>();
		
		JsonParser parser = new JsonParser();
	    Object obj=null;
	    try {
	    	obj = parser.parse(new FileReader("src/data/platos.json"));
	    } catch (IOException e) {
	       e.printStackTrace();
	    }
	    
	    JsonArray data = (JsonArray) obj;
	    
	    for(int i=0;i<data.size();i++){
	    	try {
	    		Plato plato = new Gson().fromJson(data.get(i), Plato.class);
	    		if(plato.getConcesionaria().equals(concesionaria)){
	    			platos.add(plato);
	    		}
	    	}catch(Exception e) {
	    		System.out.println("Fallo al convertir plato " + i+1 + "\nMotivo: ");
	    		System.out.println(e.getMessage());
	    	}
	    }
	    
	    return(platos);
	}
	
	/*Obtener todos los platos del sistema*/
	@Override
	public ArrayList<Plato> obtenerPlatos() {
		ArrayList<Plato> platos = new ArrayList<>();
		
		JsonParser parser = new JsonParser();
	    Object obj=null;
	    try {
	    	obj = parser.parse(new FileReader("src/data/platos.json"));
	    } catch (IOException e) {
	       e.printStackTrace();
	    }
	    
	    JsonArray data = (JsonArray) obj;
	    
	    for(int i=0;i<data.size();i++){
	    	try {
	    		Plato plato = new Gson().fromJson(data.get(i), Plato.class);
	    		platos.add(plato);
	    	}catch(Exception e) {
	    		System.out.println("Fallo al convertir plato " + i+1 + "\nMotivo: ");
	    		System.out.println(e.getMessage());
	    	}
	    }
	    
	    return(platos);
	}
	
	
	/*Almacenar factura*/
	@Override
	public void almacenarFactura(Factura factura) throws Exception{
		/*Obtenemos todas las facturas*/
		ArrayList<Factura> facturas= this.obtenerFacturas();
		
		/*Comprobamos que no exista una factura con el mismo id_bandeja o mismo vale*/
		for(Factura aux : facturas) {
			if(aux.getIdBandeja()==factura.getIdBandeja()) {
				throw new Exception("Una factura con el mismo id_bandeja ya se encuentra almacenada");
			}
			if(aux.getVale()==factura.getVale()) {
				throw new Exception("Una factura canjeada con el mismo vale ya se encuentra almacenada");
			}
		}
		
		facturas.add(factura);
		
		String json = new Gson().toJson(facturas);
		
		try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/data/facturas.json"));
            bw.write(json);
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Fallo al escribir archivo");
        }
	}
	
	/*Almacenar base estadistica*/
	@Override
	public void almacenarBaseEstadistica(BaseEstadistica baseEstadistica) throws Exception{
		ArrayList<BaseEstadistica> bases= this.obtenerBases();
		
		/*Comprobacion no id_bandeja igual*/
		for(BaseEstadistica aux : bases) {
			if(aux.getIdBandeja()== baseEstadistica.getIdBandeja()) {
				throw new Exception("Una base con el mismo id_bandeja ya se encuentra almacenada");
			}
		}
		
		bases.add(baseEstadistica);
		Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
		String json = gson.toJson(bases);
		
		try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/data/base_estadistica.json"));
            bw.write(json);
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Fallo al escribir archivo");
        }
		
	}
	
	/*Obtener bases estadisticas*/
	@Override
	public ArrayList<BaseEstadistica> obtenerBases() {
		ArrayList<BaseEstadistica> bases = new ArrayList<>();
		
		JsonParser parser = new JsonParser();
	    Object obj=null;
	    try {
	    	obj = parser.parse(new FileReader("src/data/base_estadistica.json"));
	    }catch (IOException e) {
	    	e.printStackTrace();
	    }
	    
	    JsonArray data = (JsonArray) obj;
	    
	    Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
	    
	    for(int i=0;i<data.size();i++){
	    	try {
	    		BaseEstadistica base = gson.fromJson(data.get(i), BaseEstadistica.class);
	    		bases.add(base);
	    	}catch(Exception e) {
	    		System.out.println("Fallo al convertir base " + i+1 + "\nMotivo: ");
	    		System.out.println(e.getMessage());
	    	}
	    }
	    
		return bases;
	}
	
	/*Obtener facturas*/
	@Override
	public ArrayList<Factura> obtenerFacturas() {
		
		ArrayList<Factura> facturas= new ArrayList<>();
		
		JsonParser parser = new JsonParser();
	    Object obj=null;
	    try {
	    	obj = parser.parse(new FileReader("src/data/facturas.json"));
	    }catch (IOException e) {
	    	e.printStackTrace();
	    }
	    
	    JsonArray data = (JsonArray) obj;
	    
	    for(int i=0;i<data.size();i++){
	    	Factura aux = new Gson().fromJson(data.get(i), Factura.class);
	    	facturas.add(aux);
	    }
		
		return(facturas);
	}
	/*Solo almacena el id del plato. No toda su información*/
	@Override
	public void almacenarMenu(Menu menu) throws Exception{
		
		/*Comprobaciones iniciales para excepciones*/
		ArrayList<Menu_> menus = this.obtenerMenus();
		System.out.println(menus);
		
		for(Menu_ aux : menus) {
			if(aux.getId()==menu.getId()) {
				throw new Exception("Un menú con mismo id ya está almacenado");
			}
		}
		
		
		/*Almacenar menú propiamente dicho*/
		JsonParser parser = new JsonParser();
	    Object obj=null;
	    try {
	    	obj = parser.parse(new FileReader("src/data/menu.json"));
	    }catch (IOException e) {
	    	e.printStackTrace();
	    }
	    
	    String json = obj.toString();
	    json=json.substring(0,json.length()-1);
	    if (json.length() != 1) { 
	    	json += ",";
	    }
	    json += menu.toString()+"]";
	    try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/data/menu.json"));
            bw.write(json);
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Fallo al escribir archivo");
        }
		
	}
	
	/*Funcion auxiliar*/
	private ArrayList<Menu_> obtenerMenus() {
		ArrayList<Menu_> menus = new ArrayList<>();
		
		JsonParser parser = new JsonParser();
	    Object obj=null;
	    try {
	    	obj = parser.parse(new FileReader("src/data/menu.json"));
	    } catch (IOException e) {
	       e.printStackTrace();
	    }
	    
	    JsonArray data = (JsonArray) obj;
	    Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
	    
	    for(int i=0;i<data.size();i++){
	    	try {
	    		Menu_ men = gson.fromJson(data.get(i), Menu_.class);
	    		menus.add(men);
	    	}catch(Exception e) {
	    		System.out.println("Fallo al convertir menu " + i+1 + "\nMotivo: ");
	    		System.out.println(e.getMessage());
	    	}
	    }
	    
	    return(menus);
	}
	
	private class Menu_{
		private Integer id;
		private Date fecha;
		private ArrayList<Integer> primeros;
		private ArrayList<Integer> segundos;
		private ArrayList<Integer> postres;
		
		public Menu_(Integer id, Date fecha, ArrayList<Integer> primeros, ArrayList<Integer> segundos, ArrayList<Integer> postres) {
			this.id = id;
			this.fecha = fecha;
			this.primeros = primeros;
			this.segundos = segundos;
			this.postres = postres;
		}
		
		public Integer getId() {
			return(this.id);
		}
		public Date getFecha() {
			return(this.fecha);
		}

		@Override
		public String toString() {
			return "Menu_ [id=" + id + ", fecha=" + fecha + ", primeros=" + primeros + ", segundos=" + segundos
					+ ", postres=" + postres + "]";
		}
		
		
	}

	
}
