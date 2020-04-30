package interfaces;

import java.util.ArrayList;

import bean.*;

public interface ItfGestionMenus {
	/**
	 * Devuelve los menus para los siguientes 5 d�as..
	 * @return menus
	 */
	public Menu[] obtenerMenus();
	/**
	 * Devuelve el men� del d�a.
	 * @return menu
	 */
	public Menu obtenerMenuDelDia() throws Exception;
	/**
	 * Crea un menu para la concesionaria indicada en el d�a seleccionado.
	 * Y devuelve la lista de platos de la concesionaria para "simular" que escogerPlatos ser�a sobre esos
	 * @param concesionaria de la que se va a crear el menu y dia en el
	 * @param dia se servira el menu.
	 * 
	 */
	public ArrayList<Plato> crearMenu(String concesionaria, int dia) throws Exception;
	
	/**
	 * Se a�aden los platos al men�, Y SE ALMACENA EN EL ARCHIVO
	 */
	public void escogerPlatos(int id, int menu, int tipo) throws Exception;
	
}
