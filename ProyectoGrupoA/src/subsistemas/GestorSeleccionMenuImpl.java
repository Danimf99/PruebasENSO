package subsistemas;

import java.util.ArrayList;
import bean.Menu;
import bean.Bandeja;
import bean.Plato;
import interfaces.ItfGestionMenus;
import interfaces.ItfSeleccionMenu;
import interfaces.ItfSensores;

public class GestorSeleccionMenuImpl implements ItfSeleccionMenu{
	
	private ArrayList<String> bebidas;
	private ItfGestionMenus gm;
	private ItfSensores sensores;
	private static int id=0;
	
	public GestorSeleccionMenuImpl(ItfGestionMenus gm, ItfSensores s) {
		bebidas = new ArrayList<String>();
		bebidas.add("agua");
		bebidas.add("vino");
		bebidas.add("cerveza");
		bebidas.add("cocacola");
		bebidas.add("kas");
		bebidas.add("nestea");
		
		this.gm=gm;
		this.sensores=s;
	}

	@Override
	public Bandeja seleccionarMenu(Plato plato1, Plato plato2, Plato plato3, String bebida) throws Exception {
		Menu menu = gm.obtenerMenuDelDia();
		
		
		/*Si el plato1 no pertenece a los primeros del menu -> excepicon*/
		if(!menu.getPrimeros().contains(plato1)) {
			throw new Exception(plato1+ " no está disponible entre los primeros");
		}
		
		/*Si el plato2 no pertenece a los segundos del menu -> excepicon*/
		if(!menu.getSegundos().contains(plato2)) {
			throw new Exception(plato2+ " no está disponible entre los segundos");
		}
		
		/*Si el postre no pertenece a los postres del menu -> excepicon*/
		if(!menu.getPostres().contains(plato3)) {
			throw new Exception(plato3+ " no está disponible entre los postre");
		}
		
		/*No hay bebidas*/
		if(this.bebidas==null || this.bebidas.size()==0){
			throw new Exception("No hay bebidas en la concesionaria");
		}
		
		if(!this.bebidas.contains(bebida)) {
			throw new Exception("La bebida "+bebida+ " no está disponible");
		}
		
		ArrayList<Plato> misPlatos = new ArrayList<>();
		misPlatos.add(plato1);
		misPlatos.add(plato2);
		misPlatos.add(plato3);
		Bandeja b = this.sensores.obtenerBandeja();
		b.setBebida(bebida);
		b.setPlatos(misPlatos);
		this.id++;
		return b;
	}

	@Override
	public ArrayList<String> obtenerBebidas() throws Exception {
		return(this.bebidas);
	}
	
	
	
	
}
