package interfaces;

import java.util.ArrayList;

import bean.*;

public interface ItfGestorDatos {
	
	/**
	 * Devuelve los platos de la concesionaria indicada
	 * @param concesionaria de la que buscar sus platos.
	 * @return platos
	 */
	public ArrayList<Plato> obtenerPlatos(String concesionaria);
	
	/**
	 * Devuelve todos los platos del sistema.
	 * @return platos
	 */
	public ArrayList<Plato> obtenerPlatos();
	
	/**
	 * Almacnar una factura pasada.
	 * @param factura
	 * @exception | factura con mismo id_bandeja ya contenida en el fichero | 2) mismo vale
	 */
	public void almacenarFactura(Factura factura) throws Exception;
	/**
	 * Almacenar base estadistica
	 * @param baseEstadistica
	 * @exception | 1) base con mismo id_bandeja ya contenida en el fichero
	 */
	public void almacenarBaseEstadistica(BaseEstadistica baseEstadistica) throws Exception;
	
	/**
	 * Devuelve todas las bases estadisticas
	 * @return bases_estadisticas
	 */
	public ArrayList<BaseEstadistica> obtenerBases();
	
	/**
	 * Devuelve todas las facturas
	 * @return
	 */
	public ArrayList<Factura> obtenerFacturas();
	
	/**
	 * Almacenar menu.
	 * Solo almacena el id de los platos NO toda su información.
	 * @param menu
	 * @exception | 1) mismo id 
	 */
	public void almacenarMenu(Menu menu) throws Exception;
	
}
