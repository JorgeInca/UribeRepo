package mx.com.rmsh.horusControl.vo;

public class InvestigacionRequest {

	public String firstname;
	public String lastname;
	public String rfc;
	public String pais;
	public String investigacionJson;
	public String json;

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

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
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

	@Override
	public String toString() {
		return "InvestigacionRequest [firstname=" + firstname + ", lastname=" + lastname + ", rfc=" + rfc + ", pais="
				+ pais + ", investigacionJson=" + investigacionJson + ", json=" + json + "]";
	}

}