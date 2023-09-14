package mx.com.rmsh.horusControl.vo;

import java.util.Date;

public class Investigacion {

	private Long idInvestigacion;
	private Long idUsuario;
	private String nombreUsuario;
	private Long idEmpresa;
	private String nombreEmpresa;
	private String apellidos;
	private String primer_nombre;
	private String rfc;
	private String json;
	private Integer riesgoInicial;
	private Integer riesgoFinal;
	private Integer pais;
	private Date fechaCreacion;
	private Integer estatus;

	public Investigacion() {
		super();
	}

	public Investigacion(Long idInvestigacion, Long idUsuario, String nombreUsuario, Long idEmpresa,
			String nombreEmpresa, String apellidos, String primer_nombre, String json,
			Integer riesgoInicial, Integer riesgoFinal, Date fechaCreacion, Integer estatus) {
		super();
		this.idInvestigacion = idInvestigacion;
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.idEmpresa = idEmpresa;
		this.nombreEmpresa = nombreEmpresa;
		this.apellidos = apellidos;
		this.primer_nombre = primer_nombre;
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

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getPrimer_nombre() {
		return primer_nombre;
	}

	public void setPrimer_nombre(String primer_nombre) {
		this.primer_nombre = primer_nombre;
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

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public Integer getPais() {
		return pais;
	}

	public void setPais(Integer pais) {
		this.pais = pais;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	@Override
	public String toString() {
		return "Investigacion [idInvestigacion=" + idInvestigacion + ", idUsuario=" + idUsuario + ", nombreUsuario="
				+ nombreUsuario + ", idEmpresa=" + idEmpresa + ", nombreEmpresa=" + nombreEmpresa + ", apellidos="
				+ apellidos + ", primer_nombre=" + primer_nombre + ", rfc=" + rfc + ", json=" + json
				+ ", riesgoInicial=" + riesgoInicial + ", riesgoFinal=" + riesgoFinal + ", pais=" + pais
				+ ", fechaCreacion=" + fechaCreacion + ", estatus=" + estatus + "]";
	}

}