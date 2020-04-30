package subsistemas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import bean.BaseEstadistica;
import bean.Factura;
import bean.Plato;
import interfaces.ItfGestorAnalisisEstadisticas;

public class GestionAnalisisEstadisticasImpl implements ItfGestorAnalisisEstadisticas {

	@Override
	public Float obtenerTiempoMedioComida() {
		ArrayList<Float> tiemposComida = new ArrayList<>();
		Float tiempoMedio = 0f;
		GestionDatosImpl gestionDatos = new GestionDatosImpl();
		ArrayList<BaseEstadistica> bases = gestionDatos.obtenerBases();
		
		for (BaseEstadistica b : bases) {
			Float f = new Float(b.getHoraRecogida().getTime() - b.getHoraEntrega().getTime());
			tiemposComida.add(f);
		}
		
		for (int i = 0; i < tiemposComida.size(); i++) {
			tiempoMedio += tiemposComida.get(i);
		}
		
		tiempoMedio = tiempoMedio / tiemposComida.size();
		
		
		return tiempoMedio / 60000;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Integer obtenerHoraPunta() {
		GestionDatosImpl gestionDatos = new GestionDatosImpl();
		ArrayList<BaseEstadistica> bases = gestionDatos.obtenerBases();
		HashMap<Integer, Integer> horasEntrega = new HashMap<Integer,Integer>();
		int valorMasAlto = 0;
		int hora = 0;
		
		for (int i = 0; i < 24; i++) {
			horasEntrega.put(i, 0);
		}
		
		for (BaseEstadistica b : bases) {
			switch ((b.getHoraEntrega().getHours())) {
				case 0: horasEntrega.merge(0, 1, Integer::sum); break;
				case 1: horasEntrega.merge(1, 1, Integer::sum); break;
				case 2: horasEntrega.merge(2, 1, Integer::sum); break;
				case 3: horasEntrega.merge(3, 1, Integer::sum); break;
				case 4: horasEntrega.merge(4, 1, Integer::sum); break;
				case 5: horasEntrega.merge(5, 1, Integer::sum); break;
				case 6: horasEntrega.merge(6, 1, Integer::sum); break;
				case 7: horasEntrega.merge(7, 1, Integer::sum); break;
				case 8: horasEntrega.merge(8, 1, Integer::sum); break;
				case 9: horasEntrega.merge(9, 1, Integer::sum); break;
				case 10: horasEntrega.merge(10, 1, Integer::sum); break;
				case 11: horasEntrega.merge(11, 1, Integer::sum); break;
				case 12: horasEntrega.merge(12, 1, Integer::sum); break;
				case 13: horasEntrega.merge(13, 1, Integer::sum); break;
				case 14: horasEntrega.merge(14, 1, Integer::sum); break;
				case 15: horasEntrega.merge(15, 1, Integer::sum); break;
				case 16: horasEntrega.merge(16, 1, Integer::sum); break;
				case 17: horasEntrega.merge(17, 1, Integer::sum); break;
				case 18: horasEntrega.merge(18, 1, Integer::sum); break;
				case 19: horasEntrega.merge(19, 1, Integer::sum); break;
				case 20: horasEntrega.merge(20, 1, Integer::sum); break;
				case 21: horasEntrega.merge(21, 1, Integer::sum); break;
				case 22: horasEntrega.merge(22, 1, Integer::sum); break;
				case 23: horasEntrega.merge(23, 1, Integer::sum); break;
			}
		}
		
		for (Map.Entry i : horasEntrega.entrySet()) {
			int k = (int) i.getKey();
			int v = (int) i.getValue();
			if (valorMasAlto < v) {
				valorMasAlto = v;
				hora = k;
			}
		}
		
		return hora;
	}

	@Override
	public Plato obtenerPlatoMejorValorado() {
		Plato plato = null;
		Integer valoracionMasAlta = 0;
		GestionDatosImpl gestionDatos = new GestionDatosImpl(); 
		ArrayList<BaseEstadistica> bases = gestionDatos.obtenerBases();
		ArrayList<Factura> facturas = gestionDatos.obtenerFacturas();
		ArrayList<Plato> platos = gestionDatos.obtenerPlatos();
		HashMap<Integer, ArrayList<Integer>> valoracionBandeja = new HashMap<>();
		HashMap<Integer, Integer> valoracionPlato = new HashMap<>();
		
		// Obtenemos la base estadistica
		for (BaseEstadistica b : bases) {
			valoracionBandeja.put(b.getIdBandeja(), b.getValoraciones());
		}
		
		// Iniciamos la valoracion de cada plato a 0
		for (Plato p : platos) {
			valoracionPlato.put(p.getId(), 0);
		}
		
		// Obtenemos las facturas, que contienen el id de bandeja y el id de cada plato valorado
		for (Factura f : facturas) {
			try {
				Integer b = f.getIdBandeja();
				ArrayList<Integer> idPlatos = new ArrayList<>();
				idPlatos.add(f.getPlato1());
				idPlatos.add(f.getPlato2());
				idPlatos.add(f.getPostre());
				ArrayList<Integer> valoraciones = valoracionBandeja.get(b);
				for (int i = 0; i < valoraciones.size(); i++) {
					valoracionPlato.merge(idPlatos.get(i), valoraciones.get(i), Integer::sum);
				}
			} catch (NullPointerException e) {
				// Al almacenar platos de bandejas inexistentes más adelante se malforman los datos,
				// Este trycatch sería innecesario si el sistema estuviera implementado de verdad
			}
		}
		
		for (Map.Entry i : valoracionPlato.entrySet()) {
			int k = (int) i.getKey();
			int v = (int) i.getValue();
			if (valoracionMasAlta < v) {
				valoracionMasAlta = v;
				for (Plato p : platos)
					if (p.getId().intValue() == k)
						plato = p;
			}
		}
		
		
		return plato;
	}

	@Override
	public Plato obtenerPlatoPeorValorado() {
		Plato plato = null;
		Integer valoracionMasBaja = 2147483647; // valor máximo int
		GestionDatosImpl gestionDatos = new GestionDatosImpl(); 
		ArrayList<BaseEstadistica> bases = gestionDatos.obtenerBases();
		ArrayList<Factura> facturas = gestionDatos.obtenerFacturas();
		ArrayList<Plato> platos = gestionDatos.obtenerPlatos();
		HashMap<Integer, ArrayList<Integer>> valoracionBandeja = new HashMap<>();
		HashMap<Integer, Integer> valoracionPlato = new HashMap<>();
		
		// Obtenemos la base estadistica
		for (BaseEstadistica b : bases) {
			valoracionBandeja.put(b.getIdBandeja(), b.getValoraciones());
		}
		
		// Iniciamos la valoracion de cada plato a 0
		for (Plato p : platos) {
			valoracionPlato.put(p.getId(), 0);
		}
		
		// Obtenemos las facturas, que contienen el id de bandeja y el id de cada plato valorado
		for (Factura f : facturas) {
			try {
				Integer b = f.getIdBandeja();
				ArrayList<Integer> idPlatos = new ArrayList<>();
				idPlatos.add(f.getPlato1());
				idPlatos.add(f.getPlato2());
				idPlatos.add(f.getPostre());
				ArrayList<Integer> valoraciones = valoracionBandeja.get(b);
				for (int i = 0; i < valoraciones.size(); i++) {
					valoracionPlato.merge(idPlatos.get(i), valoraciones.get(i), Integer::sum);
				}
			} catch (NullPointerException e) {
				// Al almacenar platos de bandejas inexistentes más adelante se malforman los datos,
				// Este trycatch sería innecesario si el sistema estuviera implementado de verdad
			}
		}
		
		for (Map.Entry i : valoracionPlato.entrySet()) {
			int k = (int) i.getKey();
			int v = (int) i.getValue();
			if (valoracionMasBaja > v) {
				valoracionMasBaja = v;
				for (Plato p : platos)
					if (p.getId().intValue() == k)
						plato = p;
			}
		}
		
		
		return plato;
	}

	@Override
	public Plato obtenerPlatoMasSeleccionado() {
		Plato plato = null;
		Integer vezMasSeleccionado = 0; 
		GestionDatosImpl gestionDatos = new GestionDatosImpl(); 
		ArrayList<BaseEstadistica> bases = gestionDatos.obtenerBases();
		ArrayList<Factura> facturas = gestionDatos.obtenerFacturas();
		ArrayList<Plato> platos = gestionDatos.obtenerPlatos();
		HashMap<Integer, Integer> seleccionPlato = new HashMap<>();
		
		// Iniciamos la valoracion de cada plato a 0
		for (Plato p : platos) {
			seleccionPlato.put(p.getId(), 0);
		}
		
		// Obtenemos las facturas, que contienen el id de bandeja y el id de cada plato valorado
		for (Factura f : facturas) {
			ArrayList<Integer> idPlatos = new ArrayList<>();
			idPlatos.add(f.getPlato1());
			idPlatos.add(f.getPlato2());
			idPlatos.add(f.getPostre());
			for (int i = 0; i < idPlatos.size(); i++) {
				seleccionPlato.merge(idPlatos.get(i), 1, Integer::sum);
			}
		}
		
		for (Map.Entry i : seleccionPlato.entrySet()) {
			int k = (int) i.getKey();
			int v = (int) i.getValue();
			if (vezMasSeleccionado < v) {
				vezMasSeleccionado = v;
				for (Plato p : platos)
					if (p.getId().intValue() == k)
						plato = p;
			}
		}
		
		
		return plato;
	}

	@Override
	public Plato obtenerPlatoMenosSeleccionado() {
		Plato plato = null;
		Integer vezMasSeleccionado = 2147483647; 
		GestionDatosImpl gestionDatos = new GestionDatosImpl(); 
		ArrayList<BaseEstadistica> bases = gestionDatos.obtenerBases();
		ArrayList<Factura> facturas = gestionDatos.obtenerFacturas();
		ArrayList<Plato> platos = gestionDatos.obtenerPlatos();
		HashMap<Integer, Integer> seleccionPlato = new HashMap<>();
		
		// Iniciamos la valoracion de cada plato a 0
		for (Plato p : platos) {
			seleccionPlato.put(p.getId(), 0);
		}
		
		// Obtenemos las facturas, que contienen el id de bandeja y el id de cada plato valorado
		for (Factura f : facturas) {
			ArrayList<Integer> idPlatos = new ArrayList<>();
			idPlatos.add(f.getPlato1());
			idPlatos.add(f.getPlato2());
			idPlatos.add(f.getPostre());
			for (int i = 0; i < idPlatos.size(); i++) {
				seleccionPlato.merge(idPlatos.get(i), 1, Integer::sum);
			}
		}
		
		for (Map.Entry i : seleccionPlato.entrySet()) {
			int k = (int) i.getKey();
			int v = (int) i.getValue();
			if (vezMasSeleccionado > v) {
				vezMasSeleccionado = v;
				for (Plato p : platos)
					if (p.getId().intValue() == k)
						plato = p;
			}
		}
		
		
		return plato;
	}

	@Override
	public Integer obtenerOcupacion() {
		Integer ocupacion = 0;
		GestionDatosImpl gestionDatos = new GestionDatosImpl(); 
		ArrayList<BaseEstadistica> bases = gestionDatos.obtenerBases();
		HashMap<Integer, Integer> ocupacionDia = new HashMap<>();
		
		for (int i = 0; i < 23; i++)
			ocupacionDia.put(i, 0);
		
		Calendar diaActual = Calendar.getInstance();
		
		// Ojo cambiar por el año, día del año y hora actual
		diaActual.set(Calendar.YEAR, 2020);
		diaActual.set(Calendar.DAY_OF_YEAR, 117);
		diaActual.set(Calendar.HOUR_OF_DAY, 18);
		int horaActual = diaActual.get(Calendar.HOUR_OF_DAY);
		
		for (BaseEstadistica b : bases) {
			Calendar d = Calendar.getInstance();
			d.setTime(b.getHoraEntrega());
			if (diaActual.get(Calendar.DAY_OF_YEAR) == d.get(Calendar.DAY_OF_YEAR) && diaActual.get(Calendar.YEAR) == d.get(Calendar.YEAR)) {
				int horaConcesionaria = d.get(Calendar.HOUR_OF_DAY);	
				if (horaConcesionaria == horaActual)
					ocupacion++;
			}
			
		}		
		
		return ocupacion;
	}

	@Override
	public ArrayList<Integer> obtenerDistribucionDeLaOcupacion(Date dia) {
		ArrayList<Integer> ocupacion = new ArrayList<>();
		GestionDatosImpl gestionDatos = new GestionDatosImpl(); 
		ArrayList<BaseEstadistica> bases = gestionDatos.obtenerBases();
		HashMap<Integer, Integer> ocupacionDia = new HashMap<>();
		
		for (int i = 0; i < 23; i++)
			ocupacionDia.put(i, 0);
		
		Calendar diaSeleccionado = Calendar.getInstance();
		diaSeleccionado.setTime(dia);
		
		for (BaseEstadistica b : bases) {
			Calendar d = Calendar.getInstance();
			d.setTime(b.getHoraEntrega());
			if (diaSeleccionado.get(Calendar.DAY_OF_YEAR) == d.get(Calendar.DAY_OF_YEAR) && diaSeleccionado.get(Calendar.YEAR) == d.get(Calendar.YEAR)) {
				int hora = d.get(Calendar.HOUR_OF_DAY);
				ocupacionDia.merge(hora, 1, Integer::sum);	
			}
			
		}
		
		for (Map.Entry i : ocupacionDia.entrySet()) {
			int v = (int) i.getValue();
			ocupacion.add(v);
		}
		
		return ocupacion;
	}

}
