package subsistemas;

import bean.Menu;
import bean.Plato;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import interfaces.ItfGestionMenus;
import interfaces.ItfGestorDatos;

public class GestionMenus implements ItfGestionMenus {

	private ItfGestorDatos gd;
	private Menu[] menus;
	private ArrayList<Plato> platos;
	
	public GestionMenus(ItfGestorDatos gd) {
		this.gd=gd;
		menus = new Menu[5];
	}
	@Override
	public Menu[] obtenerMenus() {
		// TODO Auto-generated method stub
		return this.menus;
	}

	@Override
	public Menu obtenerMenuDelDia() throws Exception{
		if(this.menus[0] == null) {
			throw new Exception("Aún no hay menú para este día");
		}
		
		return this.menus[0];
	}

	@Override
	public ArrayList<Plato> crearMenu(String concesionaria, int dia) throws Exception{
		//Recogemos los platos de esa concesionaria
		try {
			this.platos = gd.obtenerPlatos(concesionaria);
		}
		catch(Exception e) {
			throw e;
		}
		if(this.platos.size()==0) {
			throw new Exception("Esa concesionaria aun no tiene platos");
		}
		//Se comrpueba si es válido
		if(dia > 5 || dia <= 0) {
			throw new Exception("El numero de dia introducido no es válido");
		}
		else {
			Date d = this.traduceFecha(dia);
			//Se comprueba que no haya ya un menu para ese dia
			if(this.menus[dia-1] == null) {
				//Se genera el id del menu
				int aleatorio = this.dameEnteroAleatorio();
				this.menus[dia-1] = new Menu(aleatorio,d, new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
			}
			else {
				throw new Exception("Ya existe un menú para esa fecha, no se permiten cambios");
			}
			
		}
		return(this.platos);
	}
	
	//Metodo para pasar del dia de la seman introducido a la fecha correspondiente
	private Date traduceFecha(int dia) {
		
		Date hoy=new Date();//Se obtiene el dia actual
	    int numeroDia;
	    Calendar cal= Calendar.getInstance();
	    cal.setTime(hoy);//Se asigna al calendario el dia actual
	    while(true) {
	    	// Domingo 0 Sabado 6 
	    	numeroDia=cal.get(Calendar.DAY_OF_WEEK)-1;//Se obtiene elnuemro de dia de la semana
	    	if(dia == numeroDia) {//Si coinciden, se guarda el dia de la semana
	    		hoy=cal.getTime();
	    		break;
	    	}
	    	else {//Si no, se aumenta en 1 el dia y se vuelve a comprobar
	    		cal.add(Calendar.DAY_OF_YEAR, 1);
	    	}
	    
	    }
	     
		return hoy;
	}
	
	@Override
	public void escogerPlatos(int id, int menu, int tipo) throws Exception{
		//Mostramos los platos del tipo que nos interesa
		if(this.menus[menu-1]==null) {
			throw new Exception("Ese menu aun no esta creado");
		}
			int existe=0;
			for(Plato plato : platos) {
				if(plato.getId() == id) {
					//Si se encuentra, se comprueba que el tipo sea el correcto
					if(plato.getCategoriaPlato()==tipo) {
						try {
							menus[menu-1].addPlato(plato);
						}catch(Exception e) {throw e;}
						existe=1;
						break;
					}
					else {
						throw new Exception("Ese plato no es del tipo de plato solicitado");
					}
				}
			}
			//Si no se ha encontrado el plato
			if(existe==0) {
				throw new Exception("Ese plato no existe");
			}
			//Se comprueba se el menu esta completo
			if(menus[menu-1].getPrimeros().size()==3 && menus[menu-1].getSegundos().size()==3 && menus[menu-1].getPostres().size()==3) {
				int error=0;
				//Se intentara insertar el menu, en caso de que su id 
				//ya este, saltara excepción y se le dará otra id.
				do {
				try {
					this.gd.almacenarMenu(this.menus[menu-1]);
					error=0;
				}catch(Exception e) {
					error=1;
					int aleatorio = this.dameEnteroAleatorio();
					this.menus[menu-1].setId(aleatorio);
				}
				}while(error==1);
				
			}
		}
		
	//Función que devuelve un entero positivo aleatorio
	private int dameEnteroAleatorio() {
		int max = (int)(Math.pow(2, 31))-1;
		int min = 0;
		int rango = max - min + 1;
		int aleatorio;
		do {
		aleatorio = (int)(Math.random()*rango);
		}while(aleatorio <= 0);
		
		return aleatorio;
	}
	

}
