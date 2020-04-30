package bean;

import java.util.ArrayList;
import java.util.Date;

public class BaseEstadistica {

	private Integer idBandeja;
	private Date horaEntrega;
	private Date horaRecogida;
	private String comensal;
	private ArrayList<Integer> valoraciones;
	
	
	public BaseEstadistica(Integer idBandeja, Date horaEntrega, Date horaRecogida, String comensal,
			ArrayList<Integer> valoraciones) {
		super();
		this.idBandeja = idBandeja;
		this.horaEntrega = horaEntrega;
		this.horaRecogida = horaRecogida;
		this.comensal = comensal;
		this.valoraciones = valoraciones;
	}


	public Integer getIdBandeja() {
		return idBandeja;
	}


	public void setIdBandeja(Integer idBandeja) {
		this.idBandeja = idBandeja;
	}


	public Date getHoraEntrega() {
		return horaEntrega;
	}


	public void setHoraEntrega(Date horaEntrega) {
		this.horaEntrega = horaEntrega;
	}


	public Date getHoraRecogida() {
		return horaRecogida;
	}


	public void setHoraRecogida(Date horaRecogida) {
		this.horaRecogida = horaRecogida;
	}


	public String getComensal() {
		return comensal;
	}


	public void setComensal(String comensal) {
		this.comensal = comensal;
	}


	public ArrayList<Integer> getValoraciones() {
		return valoraciones;
	}


	public void setValoraciones(ArrayList<Integer> valoraciones) {
		this.valoraciones = valoraciones;
	}


	@Override
	public String toString() {
		return "BaseEstadistica [idBandeja=" + idBandeja + ", horaEntrega=" + horaEntrega + ", horaRecogida="
				+ horaRecogida + ", comensal=" + comensal + ", valoraciones=" + valoraciones.get(0) + " "+valoraciones.get(1)
				+ " " + valoraciones.get(2) + "]";
	}
	
	
	
}
