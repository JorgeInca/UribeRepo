package mx.com.rmsh.horusControl.enums;

public enum Pais {

	MEXICO(14, "Mexico");

	Pais(Integer idPais, String nombrePais) {
		this.idPais = idPais;
		this.nombrePais = nombrePais;
	}

	private Integer idPais;
	private String nombrePais;

	public static String getNameyId(Integer id) {

		for (Pais e : values()) {
			if (e.idPais.equals(id))
				return e.getNombrePais();
		}
		return "Desconocido";

	}

	public static Integer getIdByName(String name) {

		for (Pais e : values()) {
			if (e.nombrePais.equals(name))
				return e.getIdPais();
		}
		
		return 0;

	}

	public Integer getIdPais() {
		return idPais;
	}

	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}

	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

}
