package bean;

public class Ingrediente {
	
	private String nombreI;
	private Integer proteinas;
	private Integer carbo;
	private Integer grasas;
	private Integer calorias;
	private boolean alergeno;
	
	public Ingrediente(String nombreI, Integer proteinas, Integer carbo, Integer grasas, Integer calorias,
			boolean alergeno) {
		super();
		this.nombreI = nombreI;
		this.proteinas = proteinas;
		this.carbo = carbo;
		this.grasas = grasas;
		this.calorias = calorias;
		this.alergeno = alergeno;
	}

	public String getNombreI() {
		return nombreI;
	}

	public void setNombreI(String nombreI) {
		this.nombreI = nombreI;
	}

	public Integer getProteinas() {
		return proteinas;
	}

	public void setProteinas(Integer proteinas) {
		this.proteinas = proteinas;
	}

	public Integer getCarbo() {
		return carbo;
	}

	public void setCarbo(Integer carbo) {
		this.carbo = carbo;
	}

	public Integer getGrasas() {
		return grasas;
	}

	public void setGrasas(Integer grasas) {
		this.grasas = grasas;
	}

	public Integer getCalorias() {
		return calorias;
	}

	public void setCalorias(Integer calorias) {
		this.calorias = calorias;
	}

	public boolean isAlergeno() {
		return alergeno;
	}

	public void setAlergeno(boolean alergeno) {
		this.alergeno = alergeno;
	}

	@Override
	public String toString() {
		return "Ingrediente [nombreI=" + nombreI + ", proteinas=" + proteinas + ", carbo=" + carbo + ", grasas="
				+ grasas + ", calorias=" + calorias + ", alergeno=" + alergeno + "]";
	}
	
	
}
