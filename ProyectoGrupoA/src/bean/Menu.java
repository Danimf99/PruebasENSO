package bean;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Menu {
	
	private Integer id;
	private Date fecha;
	private ArrayList<Plato> primeros;
	private ArrayList<Plato> segundos;
	private ArrayList<Plato> postres;
	
	public Menu(Integer id, Date fecha, ArrayList<Plato> primeros, ArrayList<Plato> segundos,
			ArrayList<Plato> postres) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.primeros = primeros;
		this.segundos = segundos;
		this.postres = postres;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public ArrayList<Plato> getPrimeros() {
		return primeros;
	}

	public void setPrimeros(ArrayList<Plato> primeros) {
		this.primeros = primeros;
	}

	public ArrayList<Plato> getSegundos() {
		return segundos;
	}

	public void setSegundos(ArrayList<Plato> segundos) {
		this.segundos = segundos;
	}

	public ArrayList<Plato> getPostres() {
		return postres;
	}

	public void setPostres(ArrayList<Plato> postres) {
		this.postres = postres;
	}

	@Override
	public String toString() {
		Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
		//{"id": 2,"fecha": "2020-02-03T13:14:45+0100","primeros": [1, 2, 3],"segundos": [4,5,6],"postres": [6,7,8]}
		return "{\"id\":" + id + ", \"fecha\":" + (String)gson.toJson(fecha) + ", \"primeros\":[" + primeros.get(0).getId()+", "+primeros.get(1).getId()+", "+primeros.get(2).getId()+"]"
				+ ", \"segundos\":["+ segundos.get(0).getId()+", "+segundos.get(1).getId()+", "+segundos.get(2).getId()+"]"
				+ ", \"postres\":[" + postres.get(0).getId()+", "+postres.get(1).getId()+", "+postres.get(2).getId()+"]}";
	}
	
	public void addPlato(Plato p) throws Exception{
		switch(p.getCategoriaPlato()) {
		case 1:
			if (this.primeros.size()<3) {
				if(!this.primeros.contains(p)) {
					this.primeros.add(p);
				}
				else {
					throw new Exception("Ese plato ya esta en el menu");
				}
			}
			else {
				throw new Exception("Ya no puede haber mas platos de ese tipo");
			}
			break;
		case 2:
			if (this.segundos.size()<3) {
				if(!this.segundos.contains(p)) {
					this.segundos.add(p);
				}
				else {
					throw new Exception("Ya no puede haber mas platos de ese tipo");
					
				}
			}
			else {
				throw new Exception("Ese plato ya esta en el menu");
			}
			break;
		case 3:
			if (this.postres.size()<3 && !this.postres.contains(p)) {
				this.postres.add(p);
			}
			else {
				throw new Exception("Ese plato ya esta en el menu");
			}
			break;
		}
		
	}
	
	public boolean contiene(Plato plato) {
		if(this.primeros.contains(plato) || this.segundos.contains(plato)||this.postres.contains(plato)) {
			return(true);
		}
		else {
			return(false);
		}
	}
	
	
	
	
}
