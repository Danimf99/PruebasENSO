package subsistemas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import bean.Bandeja;
import bean.BaseEstadistica;
import bean.Factura;
import bean.Menu;
import bean.Plato;
import interfaces.ItfGestionMenus;
import interfaces.ItfGestionPagosCalificaciones;
import interfaces.ItfGestorDatos;
import interfaces.ItfSeleccionMenu;
import interfaces.ItfSensores;

@SuppressWarnings("deprecation")
public class Main {

	public static void main(String[] args) {
		
		/************************
		/* Variables iniciales */
		GestionPagosCalificacionesImpl gpc = new GestionPagosCalificacionesImpl();
		SensoresImpl s = new SensoresImpl(gpc);
		gpc.setSensores(s);
		Bandeja b = s.obtenerBandeja();
		ItfGestorDatos gd = new GestionDatosImpl();
		ItfGestionMenus gm = new GestionMenus(gd);
		ItfSeleccionMenu sm = new GestorSeleccionMenuImpl(gm, s);

		/*************
		/*crear menu*/
		try {
			gm.crearMenu("Albor",1);
			gm.escogerPlatos(1, 1, 1);
			gm.escogerPlatos(2, 1, 1);
			gm.escogerPlatos(3, 1, 1);
			gm.escogerPlatos(4, 1, 2);
			gm.escogerPlatos(5, 1, 2);
			gm.escogerPlatos(6, 1, 2);
			gm.escogerPlatos(7, 1, 3);
			gm.escogerPlatos(8, 1, 3);
			gm.escogerPlatos(9, 1, 3);
			Menu m = gm.obtenerMenuDelDia();
			System.out.println(m.toString());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			gm.crearMenu("Albor",1);
			gm.escogerPlatos(2, 2, 1);
			gm.escogerPlatos(2, 2, 1);
			gm.escogerPlatos(1, 2, 1);
			gm.escogerPlatos(1, 2, 1);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		/*******************
		/*Seleccionar menu*/
		Plato plato1 = new Plato(1, "ensalada", "Ensalada rica rica", null, 1, null, null);
		Plato plato2 = new Plato(4, "pollO", "pollolaksd", null, 2, null, null);
		Plato plato3 = new Plato(7, "tarta", "tartasijdk", null, 3, null, null);
		String bebida = new String("agua");
		 
		try {
			b = sm.seleccionarMenu(plato1, plato2, plato3, bebida);
			System.out.println(b);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		/*******************************
		/*Pruebas pagos calificaciones*/
		
		/*********************
		/*Pruebas pagar menú*/
		
		try {
			gpc.pagarMenu();
			gpc.valorarPlatos("futfi", 3, 5, 7);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		/************************
		/*Prueba valorar platos*/
		
		try {
			gpc.valorarPlatos("max_power",11,10,10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			gpc.valorarPlatos("billy_elish",10,11,10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			gpc.valorarPlatos("tommy_wiseau",10,10,11);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			gpc.valorarPlatos("tommy_wiseau",-1,10,10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			gpc.valorarPlatos("tommy_wiseau",10,-1,10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			gpc.valorarPlatos("tommy_wiseau",10,10,-1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			gpc.valorarPlatos("",10,10,1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			gpc.valorarPlatos(null,10,10,1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		

		/****************
		/*Estadisticas*/
		System.out.println(b.getId());
		System.out.println(s.obtenerVale());
		
		System.out.println("Esperando por fin de consumición"); 
		s.terminarConsumicion(b);
		GestionAnalisisEstadisticasImpl gae = new GestionAnalisisEstadisticasImpl();
		System.out.println("Minutos: " + gae.obtenerTiempoMedioComida().toString());
		System.out.println("Hora punta: " + gae.obtenerHoraPunta());
		System.out.println("Plato mejor valorado: " + gae.obtenerPlatoMejorValorado().getId());
		System.out.println("Plato peor valorado: " + gae.obtenerPlatoPeorValorado().getId());
		System.out.println("Plato mas seleccionado: " + gae.obtenerPlatoMasSeleccionado().getId());
		System.out.println("Plato menos seleccionado: " + gae.obtenerPlatoMenosSeleccionado().getId());
		Calendar cal = Calendar.getInstance();
		
		// OJponed el año y el día del año
		cal.set(Calendar.YEAR, 2020);
		cal.set(Calendar.DAY_OF_YEAR, 117);
		Date d = cal.getTime();
		System.out.println("Ocupación actual: " + gae.obtenerOcupacion());
		System.out.println(gae.obtenerDistribucionDeLaOcupacion(d));
		
		
		System.out.println("Fin de la ejecuciÃ³n.");
	}

}
