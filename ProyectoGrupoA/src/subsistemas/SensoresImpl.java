package subsistemas;

import java.util.ArrayList;
import java.util.Date;

import bean.Bandeja;
import bean.Plato;
import interfaces.*;

public class SensoresImpl implements ItfSensores {

	private ItfGestionPagosCalificaciones gpc;
	private Bandeja ultimaBandeja;
	
	public SensoresImpl(ItfGestionPagosCalificaciones gpc) {
		this.gpc=gpc;
		this.ultimaBandeja = null;
	}
	
	@Override
	public Bandeja obtenerBandeja() {
		int max = (int)(Math.pow(2, 31))-1;
		int min = 0;
		int rango = max - min + 1;
		int aleatorio;
		do {
			aleatorio = (int)(Math.random()*rango);
		} while (aleatorio <= 0);
		
		ArrayList<Plato> platos = new ArrayList<>();
		platos.add(new Plato(1, null, null, null, null, null, null));
		platos.add(new Plato(1, null, null, null, null, null, null));
		platos.add(new Plato(1, null, null, null, null, null, null));
		
		Bandeja j = new Bandeja(null,platos,"agua", null);
		j.setId(aleatorio);
		j.setHoraRecogida(new Date());
		
		this.ultimaBandeja = j;
		
		return(j);
	}

	@Override
	public Integer obtenerVale() {
		int max = (int)(Math.pow(2, 31))-1;
		int min = 0;
		int rango = max - min + 1;
		int aleatorio;
		do {
		aleatorio = (int)(Math.random()*rango);
		}while(aleatorio <= 0);
		return aleatorio;
	}
	
	public void terminarConsumicion(Bandeja bandeja) {
		int max = 6000;
		int min = 1000;
		int rango = max - min + 1;
		int aleatorio = (int)(Math.random()*rango);
		
		try {
			Thread.sleep(aleatorio);
		}
		catch(Exception e) {
			
		}
	}
	
	public Bandeja obtenerUltimaBandeja() {
		return this.ultimaBandeja;
	}
}
