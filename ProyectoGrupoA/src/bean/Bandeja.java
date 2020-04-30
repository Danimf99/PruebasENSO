package bean;

import java.util.Date;
import java.util.ArrayList;

public class Bandeja {

	private Integer id;
	private ArrayList<Plato> platos;
	private String bebida;
	private Date horaRecogida;
	
	public Bandeja(Integer id, ArrayList<Plato> platos, String bebida, Date horaRecogida) {
		super();
		this.id = id;
		this.platos = platos;
		this.bebida = bebida;
		this.horaRecogida = horaRecogida;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ArrayList<Plato> getPlatos() {
		return platos;
	}

	public void setPlatos(ArrayList<Plato> platos) {
		this.platos = platos;
	}

	public String getBebida() {
		return bebida;
	}

	public void setBebida(String bebida) {
		this.bebida = bebida;
	}

	public Date getHoraRecogida() {
		return horaRecogida;
	}

	public void setHoraRecogida(Date horaRecogida) {
		this.horaRecogida = horaRecogida;
	}
	
	public void addPlato(Plato plato) {
		this.platos.add(plato);
	}

	@Override
	public String toString() {
		return "Bandeja [id=" + id + ", platos=" + platos + ", bebida=" + bebida + ", horaRecogida=" + horaRecogida
				+ "]";
	}
	
	
}
