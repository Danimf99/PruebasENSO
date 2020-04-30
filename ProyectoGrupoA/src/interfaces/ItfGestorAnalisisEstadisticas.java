package interfaces;

import java.util.ArrayList;
import java.util.Date;

import bean.Plato;

public interface ItfGestorAnalisisEstadisticas {

	public Float obtenerTiempoMedioComida();
	public Integer obtenerHoraPunta();
	public Plato obtenerPlatoMejorValorado();
	public Plato obtenerPlatoPeorValorado();
	public Plato obtenerPlatoMasSeleccionado();
	public Plato obtenerPlatoMenosSeleccionado();
	public Integer obtenerOcupacion();
	public ArrayList<Integer> obtenerDistribucionDeLaOcupacion(Date dia);
	
}
