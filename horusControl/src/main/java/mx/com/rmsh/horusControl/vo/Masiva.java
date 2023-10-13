package mx.com.rmsh.horusControl.vo;

import java.util.Date;

import mx.com.rmsh.horusControl.enums.EstatusMasiva;

public class Masiva {

	private Long idInvestigacionMasiva;
	private String nombre;
	private Long idUsuario;
	private String nombreUsuario;
	private Date fechaCreacion;
	private Integer estatus;
	private String estatusText;

	public Masiva() {
		super();
	}

	public Masiva(Long idInvestigacionMasiva, String nombre, Long idUsuario, String nombreUsuario, Date fechaCreacion,
			Integer estatus) {
		super();
		this.idInvestigacionMasiva = idInvestigacionMasiva;
		this.nombre = nombre;
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.fechaCreacion = fechaCreacion;
		this.estatus = estatus;
		this.setEstatusText( EstatusMasiva.getNameyId(estatus) );
	}

	public Long getIdInvestigacionMasiva() {
		return idInvestigacionMasiva;
	}

	public void setIdInvestigacionMasiva(Long idInvestigacionMasiva) {
		this.idInvestigacionMasiva = idInvestigacionMasiva;
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

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getEstatusText() {
		return estatusText;
	}

	public void setEstatusText(String estatusText) {
		this.estatusText = estatusText;
	}

	@Override
	public String toString() {
		return "Masiva [idInvestigacionMasiva=" + idInvestigacionMasiva + ", nombre=" + nombre + ", idUsuario="
				+ idUsuario + ", nombreUsuario=" + nombreUsuario + ", fechaCreacion=" + fechaCreacion + ", estatus="
				+ estatus + ", estatusText=" + estatusText + "]";
	}

}
