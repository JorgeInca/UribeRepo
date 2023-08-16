package mx.com.rmsh.horusControl.vo;

import java.sql.Date;

public class InvestigacionReporte {
	
	private int folio;
	private String nombre;
	private String cliente;
	private String fecha;
	public InvestigacionReporte() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InvestigacionReporte(int folio, String nombre, String cliente, String fecha) {
		super();
		this.folio = folio;
		this.nombre = nombre;
		this.cliente = cliente;
		this.fecha = fecha;
	}
	public int getFolio() {
		return folio;
	}
	public void setFolio(int folio) {
		this.folio = folio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	@Override
	public String toString() {
		return "InvestigacionReporte [folio=" + folio + ", nombre=" + nombre + ", cliente=" + cliente + ", fecha="
				+ fecha + "]";
	}
	
	

	

}
