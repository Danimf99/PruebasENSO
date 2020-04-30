package interfaces;

import java.util.ArrayList;

import bean.Bandeja;
import bean.Plato;

public interface ItfSeleccionMenu {
	
	/**
	 * Método para que un comensal cree su consumición (bandeja) a partir del menú del día
	 * @param plato1, primero plato de la bandeja.
	 * @param plato2, segundo plato de la bandeja.
	 * @param plato3, postre plato de la bandeja.
	 * @param bebida.
	 * @return, bandeja (consumición del cliente)
	 */
	public Bandeja seleccionarMenu(Plato plato1, Plato plato2, Plato plato3, String bebida) 
			throws Exception;
	
	/**
	 * Devuelve las bebidas que un comensal puede elegir.
	 * @return bebidas
	 */
	public ArrayList<String> obtenerBebidas() throws Exception;
	
}
