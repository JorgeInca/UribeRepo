package mx.com.rmsh.horusControl.vo;

import java.util.Date;

public class Investigacion {

	private Long idInvestigacion;
	private String nombre;
	private Long idUsuario;
	private Long idEmpresa;
	private String json;
	private Integer riesgoInicial;
	private Integer riesgoFinal;
	private Date fechaCreacion;
	private Integer estatus;

	public Investigacion() {
		super();
	}

	public Investigacion(Long idInvestigacion, String nombre, Long idUsuario, Long idEmpresa, String json,
			Integer riesgoInicial, Integer riesgoFinal, Date fechaCreacion, Integer estatus) {
		super();
		this.idInvestigacion = idInvestigacion;
		this.nombre = nombre;
		this.idUsuario = idUsuario;
		this.idEmpresa = idEmpresa;
		this.json = json;
		this.riesgoInicial = riesgoInicial;
		this.riesgoFinal = riesgoFinal;
		this.fechaCreacion = fechaCreacion;
		this.estatus = estatus;
	}

	public Long getIdInvestigacion() {
		return idInvestigacion;
	}

	public void setIdInvestigacion(Long idInvestigacion) {
		this.idInvestigacion = idInvestigacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public Integer getRiesgoInicial() {
		return riesgoInicial;
	}

	public void setRiesgoInicial(Integer riesgoInicial) {
		this.riesgoInicial = riesgoInicial;
	}

	public Integer getRiesgoFinal() {
		return riesgoFinal;
	}

	public void setRiesgoFinal(Integer riesgoFinal) {
		this.riesgoFinal = riesgoFinal;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	@Override
	public String toString() {
		return "Investigacion [idInvestigacion=" + idInvestigacion + ", nombre=" + nombre + ", idUsuario=" + idUsuario
				+ ", idEmpresa=" + idEmpresa + ", json=" + json + ", riesgoInicial=" + riesgoInicial + ", riesgoFinal="
				+ riesgoFinal + ", fechaCreacion=" + fechaCreacion + ", estatus=" + estatus + "]";
	}

}