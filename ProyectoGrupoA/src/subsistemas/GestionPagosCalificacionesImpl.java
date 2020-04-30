package subsistemas;

import java.util.ArrayList;
import java.util.Date;

import bean.Bandeja;
import bean.Factura;
import bean.BaseEstadistica;
import interfaces.ItfGestionPagosCalificaciones;
import interfaces.ItfGestorDatos;
import interfaces.ItfSensores;

public class GestionPagosCalificacionesImpl implements ItfGestionPagosCalificaciones {

	private ItfSensores s;
	
	@Override
	public void pagarMenu() throws Exception {
		
		// Variables iniciales
		ItfGestorDatos gd = new GestionDatosImpl();
		
		// Se obtiene el vale a usar de los sensores
		Integer vale = this.s.obtenerVale();
		
		// Se obtiene el ID de la bandeja a pagar
		Bandeja bandeja = this.s.obtenerUltimaBandeja();
		
		if (vale < 0) {
			throw new Exception("ID de vale leÌdo inv·lido.");
		}
		
		if (bandeja == null) {
			throw new Exception("Bandeja obtenida inv·lida.");
		}
		
		int plato1 = bandeja.getPlatos().get(0).getId();
		int plato2 = bandeja.getPlatos().get(1).getId();
		int postre = bandeja.getPlatos().get(2).getId();
		String bebida = bandeja.getBebida();
		
		// Verifico malformacion de los datos
		if (plato1 < 0) {
			throw new Exception("ID del primer plato de la bandeja errÛneo");
		}
		
		if (plato2 < 0) {
			throw new Exception("ID del segundo plato de la bandeja errÛneo");
		}
		
		if (postre < 0) {
			throw new Exception("ID del postre plato de la bandeja errÛneo");
		}
		
		if (bebida == null || bebida.equals("")) {
			throw new Exception("Bebida con nombre errÛneo");
		}
		
		// Creo la factura
		Factura f = new Factura(vale, bandeja.getId(), plato1, plato2, postre, bebida);
		
		// Almaceno la factura
		gd.almacenarFactura(f);
		
		// Llamar a espera por consumiciÛn
		System.out.println("Esperando a fin de la consumiciÛn.");
		this.s.terminarConsumicion(bandeja);
	}

	@Override
	public void valorarPlatos(String comensal, int nota_primero, int nota_segundo, int nota_postre) throws Exception {
		
		// Variables iniciales
		ItfGestorDatos gd = new GestionDatosImpl();
		
		// Obtener bandeja del sensor
		Bandeja bandeja = this.s.obtenerUltimaBandeja();
		
		// Comprobacion de las calificaciones
		if (nota_primero < 0 || nota_primero > 10) {
			throw new Exception("Nota del primer plato fuera del rango aceptable.");
		}
		
		if (nota_segundo < 0 || nota_segundo > 10) {
			throw new Exception("Nota del segundo plato fuera del rango aceptable.");
		}
		
		if (nota_postre < 0 || nota_postre > 10) {
			throw new Exception("Nota del postre fuera del rango aceptable.");
		}
		
		// Comprobacion del nombre del comensal
		if (comensal == null || comensal.equals("")) {
			throw new Exception("Nombre de comensal se encuentra malformado.");
		}
		
		// Comprobar validez de la bandeja
		if (bandeja == null) {
			throw new Exception("No se ha introducido una bandeja v√°lida.");
		}
		
		// Contruir el array de calificaciones en base al input
		ArrayList<Integer> calificaciones = new ArrayList<>();
		calificaciones.add(nota_primero);
		calificaciones.add(nota_segundo);
		calificaciones.add(nota_postre);
		
		// Contruir base estadistica a partir de los datos
		BaseEstadistica b = new BaseEstadistica(bandeja.getId(), new Date(),bandeja.getHoraRecogida(), comensal, calificaciones);
		
		try {
			gd.almacenarBaseEstadistica(b);
		} catch (Exception e) {
			throw e;
		}
	}
	
	// AÒade la referencia al subsistema sensores
	public void setSensores(ItfSensores s) {
		this.s = s;
	}
}
