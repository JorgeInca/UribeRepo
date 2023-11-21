package mx.com.rmsh.horusControl.vo;

public class ReporteRequest {

	public Long idUserHorus;
	public String rolUser;
	
	public Long getIdUserHorus() {
		return idUserHorus;
	}
	public void setIdUserHorus(Long idUserHorus) {
		this.idUserHorus = idUserHorus;
	}
	public String getRolUser() {
		return rolUser;
	}
	public void setRolUser(String rolUser) {
		this.rolUser = rolUser;
	}
	@Override
	public String toString() {
		return "ReporteRequest [idUserHorus=" + idUserHorus + ", rolUser=" + rolUser + "]";
	}

	
}