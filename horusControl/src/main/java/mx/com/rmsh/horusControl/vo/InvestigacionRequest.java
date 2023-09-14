package mx.com.rmsh.horusControl.vo;

public class InvestigacionRequest {

	public Long idInvestigacion;
	public String firstname;
	public String lastname;
	public Long idUsuario;
	public String rfc;
	public Integer pais;
	public Integer nivel_riesgo;
	public String investigacionJson;
	public String json;
	public Long idMasiva;
	public Integer idEstatus;
	public String estatusText;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getInvestigacionJson() {
		return investigacionJson;
	}

	public void setInvestigacionJson(String investigacionJson) {
		this.investigacionJson = investigacionJson;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public Integer getNivel_riesgo() {
		return nivel_riesgo;
	}

	public void setNivel_riesgo(Integer nivel_riesgo) {
		this.nivel_riesgo = nivel_riesgo;
	}

	public Long getIdInvestigacion() {
		return idInvestigacion;
	}

	public void setIdInvestigacion(Long idInvestigacion) {
		this.idInvestigacion = idInvestigacion;
	}

	public Integer getPais() {
		return pais;
	}

	public void setPais(Integer pais) {
		this.pais = pais;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdMasiva() {
		return idMasiva;
	}

	public void setIdMasiva(Long idMasiva) {
		this.idMasiva = idMasiva;
	}

	public Integer getIdEstatus() {
		return idEstatus;
	}

	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}

	public String getEstatusText() {
		return estatusText;
	}

	public void setEstatusText(String estatusText) {
		this.estatusText = estatusText;
	}

	@Override
	public String toString() {
		return "InvestigacionRequest [idInvestigacion=" + idInvestigacion + ", firstname=" + firstname + ", lastname="
				+ lastname + ", idUsuario=" + idUsuario + ", rfc=" + rfc + ", pais=" + pais + ", nivel_riesgo="
				+ nivel_riesgo + ", investigacionJson=" + investigacionJson + ", json=" + json + ", idMasiva="
				+ idMasiva + ", idEstatus=" + idEstatus + ", estatusText=" + estatusText + "]";
	}

}