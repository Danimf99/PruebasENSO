package interfaces;

import bean.Bandeja;

/*
 * Interfaz de gesti�n de pagos
 * */
public interface ItfGestionPagosCalificaciones {

	public void pagarMenu() throws Exception;
	public void valorarPlatos(String comensal, int nota_primero, int nota_segundo, int nota_postre) throws Exception;
	
}
