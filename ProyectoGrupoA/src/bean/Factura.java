package bean;

public class Factura {

	private Integer vale;
	private Integer idBandeja;
	private Integer plato1;
	private Integer plato2;
	private Integer postre;
	private String bebida;
	
	public Factura(Integer vale, Integer bandeja, Integer plato1, Integer plato2, Integer postre, String bebida) {
		super();
		this.vale = vale;
		this.idBandeja = bandeja;
		this.plato1 = plato1;
		this.plato2 = plato2;
		this.postre = postre;
		this.bebida = bebida;
	}

	public Integer getVale() {
		return vale;
	}

	public void setVale(Integer vale) {
		this.vale = vale;
	}

	public Integer getIdBandeja() {
		return idBandeja;
	}

	public void setBandeja(Integer bandeja) {
		this.idBandeja = bandeja;
	}

	public Integer getPlato1() {
		return plato1;
	}

	public void setPlato1(Integer plato1) {
		this.plato1 = plato1;
	}

	public Integer getPlato2() {
		return plato2;
	}

	public void setPlato2(Integer plato2) {
		this.plato2 = plato2;
	}

	public Integer getPostre() {
		return postre;
	}

	public void setPostre(Integer postre) {
		this.postre = postre;
	}

	public String getBebida() {
		return bebida;
	}

	public void setBebida(String bebida) {
		this.bebida = bebida;
	}

	@Override
	public String toString() {
		return "Factura [vale=" + vale + ", bandeja=" + idBandeja + ", plato1=" + plato1 + ", plato2=" + plato2
				+ ", postre=" + postre + ", bebida=" + bebida + "]";
	}
	
	
}
