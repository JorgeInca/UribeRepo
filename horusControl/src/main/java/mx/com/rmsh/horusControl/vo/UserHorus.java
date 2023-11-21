package mx.com.rmsh.horusControl.vo;

import java.util.Date;

public class UserHorus {

	public Long id_usuario;
	public String name;
	public String email;
	public Integer estatus;
	public Long idEmpresa;
	private String nombreEmpresa;
	public Integer rol;
	public String rolText;
	public String password;

	public UserHorus() {
		super();
	}

	public UserHorus(Long id_usuario, String name, String email, Integer estatus, Long idEmpresa, String nombreEmpresa,
			Integer rol, String password) {
		super();
		this.id_usuario = id_usuario;
		this.name = name;
		this.email = email;
		this.estatus = estatus;
		this.idEmpresa = idEmpresa;
		this.nombreEmpresa = nombreEmpresa;
		this.rol = rol;
		this.password = password;
	}

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public Integer getRol() {
		return rol;
	}

	public void setRol(Integer rol) {
		this.rol = rol;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRolText() {
		return rolText;
	}

	public void setRolText(String rolText) {
		this.rolText = rolText;
	}

	@Override
	public String toString() {
		return "UserHorus [id_usuario=" + id_usuario + ", name=" + name + ", email=" + email + ", estatus=" + estatus
				+ ", idEmpresa=" + idEmpresa + ", nombreEmpresa=" + nombreEmpresa + ", rol=" + rol + ", rolText="
				+ rolText + ", password=" + password + "]";
	}

}