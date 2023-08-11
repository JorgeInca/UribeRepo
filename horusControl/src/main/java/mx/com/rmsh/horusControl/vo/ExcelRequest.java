package mx.com.rmsh.horusControl.vo;

public class ExcelRequest {
	
	public String name;
	public String firstname;
	public String criteria;
	public String country;
	public String rfc;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	@Override
	public String toString() {
		return "ExcelRequest [name=" + name + ", firstname=" + firstname + ", criteria=" + criteria + ", country="
				+ country + ", rfc=" + rfc + "]";
	}

}
