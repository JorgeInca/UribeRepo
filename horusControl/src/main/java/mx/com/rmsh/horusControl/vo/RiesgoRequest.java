package mx.com.rmsh.horusControl.vo;

public class RiesgoRequest {

	public Long idUsuario;
	public Long idInvestigacion;
	public Integer riesgo;
	public String nombreCliente;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdInvestigacion() {
		return idInvestigacion;
	}

	public void setIdInvestigacion(Long idInvestigacion) {
		this.idInvestigacion = idInvestigacion;
	}

	public Integer getRiesgo() {
		return riesgo;
	}

	public void setRiesgo(Integer riesgo) {
		this.riesgo = riesgo;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	@Override
	public String toString() {
		return "RiesgoRequest [idUsuario=" + idUsuario + ", idInvestigacion=" + idInvestigacion + ", riesgo=" + riesgo
				+ ", nombreCliente=" + nombreCliente + "]";
	}

	
}