package mx.com.rmsh.horusControl.vo;

public class OrigenesBorradoRequest {

	public Long idInvestigacion;
	public Long idUsuario;
	public String nuevoValor;
	public boolean esMention;

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

	public String getNuevoValor() {
		return nuevoValor;
	}

	public void setNuevoValor(String nuevoValor) {
		this.nuevoValor = nuevoValor;
	}

	public boolean isEsMention() {
		return esMention;
	}

	public void setEsMention(boolean esMention) {
		this.esMention = esMention;
	}
	

	@Override
	public String toString() {
		return "OrigenesBorradoRequest [idInvestigacion=" + idInvestigacion + ", idUsuario=" + idUsuario
				+ ", nuevoValor=" + nuevoValor + ", esMention=" + esMention + "]";
	}

}