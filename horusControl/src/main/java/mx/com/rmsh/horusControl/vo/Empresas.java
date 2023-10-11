package mx.com.rmsh.horusControl.vo;

import java.util.Date;

public class Empresas {
	
	public long id_empresa;
	public String nombre;
	public String email;
	public long phone;
	public int estatus;
	
	
	public Empresas() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Empresas(long id_empresa, String nombre, String email, long phone, int estatus) {
		super();
		this.id_empresa = id_empresa;
		this.nombre = nombre;
		this.email = email;
		this.phone = phone;
		this.estatus = estatus;
		
		
	}



	public long getId_empresa() {
		return id_empresa;
	}



	public void setId_empresa(long id_empresa) {
		this.id_empresa = id_empresa;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public long getPhone() {
		return phone;
	}



	public void setPhone(long phone) {
		this.phone = phone;
	}



	public int getEstatus() {
		return estatus;
	}



	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}



	@Override
	public String toString() {
		return "Empresas [id_empresa=" + id_empresa + ", nombre=" + nombre + ", email=" + email + ", phone=" + phone
				+ ", estatus=" + estatus + "]";
	}
	
	
	
	

}
