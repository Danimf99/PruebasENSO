package bean;

public class Valoracion {
	
	private String comensal;
	private Integer puntuacion;
	private Plato plato;
	
	public Valoracion(String comensal, Integer puntuacion, Plato plato) {
		super();
		this.comensal = comensal;
		this.puntuacion = puntuacion;
		this.plato = plato;
	}

	public String getComensal() {
		return comensal;
	}

	public void setComensal(String comensal) {
		this.comensal = comensal;
	}

	public Integer getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Integer puntuacion) {
		this.puntuacion = puntuacion;
	}

	public Plato getPlato() {
		return plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}
	
}
