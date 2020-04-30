package bean;

import java.util.ArrayList;

public class Plato {
	
	private Integer id;
	private String nombre;
	private String descripcion;
	private String concesionaria;
	private Integer categoriaPlato;
	private String tipoCocina;
	private ArrayList<Ingrediente> ingredientes;
	
	
	public Plato(Integer id, String nombre, String descripcion, String concesionaria, Integer categoriaPlato,
			String tipoCocina, ArrayList<Ingrediente> ingredientes) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.concesionaria = concesionaria;
		this.categoriaPlato = categoriaPlato;
		this.tipoCocina = tipoCocina;
		this.ingredientes = ingredientes;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getConcesionaria() {
		return concesionaria;
	}
	public void setConcesionaria(String concesionaria) {
		this.concesionaria = concesionaria;
	}
	public Integer getCategoriaPlato() {
		return categoriaPlato;
	}
	public void setCategoriaPlato(Integer categoriaPlato) {
		this.categoriaPlato = categoriaPlato;
	}
	public String getTipoCocina() {
		return tipoCocina;
	}
	public void setTipoCocina(String tipoCocina) {
		this.tipoCocina = tipoCocina;
	}
	public ArrayList<Ingrediente> getIngredientes() {
		return ingredientes;
	}
	public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	@Override
	public String toString() {
		return "Plato [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", concesionaria="
				+ concesionaria + ", categoriaPlato=" + categoriaPlato + ", tipoCocina=" + tipoCocina
				+ ", ingredientes=" + ingredientes + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plato other = (Plato) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
		
	
	
	
	
}
