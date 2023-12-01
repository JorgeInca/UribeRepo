package mx.com.rmsh.horusControl.vo.ca;

public class CampaniaCA {

	public Long idCampania;
	public String nombreCampania;

	public CampaniaCA() {
		super();
	}

	public CampaniaCA(Long idCampania, String nombreCampania) {
		super();
		this.idCampania = idCampania;
		this.nombreCampania = nombreCampania;
	}

	public Long getIdCampania() {
		return idCampania;
	}

	public void setIdCampania(Long idCampania) {
		this.idCampania = idCampania;
	}

	public String getNombreCampania() {
		return nombreCampania;
	}

	public void setNombreCampania(String nombreCampania) {
		this.nombreCampania = nombreCampania;
	}

	@Override
	public String toString() {
		return "CampaniaCA [idCampania=" + idCampania + ", nombreCampania=" + nombreCampania + "]";
	}
	
	

}