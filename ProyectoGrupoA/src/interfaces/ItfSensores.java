package interfaces;

import bean.Bandeja;

public interface ItfSensores {
	/**
	 * Asigna un id aleatorio a la bandeja recibida.
	 * @param bandeja
	 */
	public Bandeja obtenerBandeja();
	
	/**
	 * Devuelve un id aleatorio para un vale.
	 * @return
	 */
	public Integer obtenerVale();
	
	/**
	 * Pasado un tiempo (1-6 sec), indica a gpc que inicie valorar
	 * platos, pasandole la bandeja recibida.
	 * @param bandeja
	 */
	public void terminarConsumicion(Bandeja bandeja);
	
	/**
	 * Devuelve la última bandeja leída.
	 * @return bandeja
	 */
	public Bandeja obtenerUltimaBandeja();
}
