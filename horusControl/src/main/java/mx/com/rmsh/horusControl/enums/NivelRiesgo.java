package mx.com.rmsh.horusControl.enums;

public enum NivelRiesgo {

	BAJO(1, "BAJO"),
	MEDIO(2, "MEDIO"),
	ALTO(3, "ALTO");

	NivelRiesgo(Integer idEstatus, String name) {
		this.idEstatus = idEstatus;
		this.name = name;
	}

	private Integer idEstatus;
	private String name;

	public static String getNameyId(Integer id) {

		for (NivelRiesgo e : values()) {
			if (e.idEstatus.equals(id))
				return e.getName();
		}
		return "BAJO";

	}

	public static Integer getIdByName(String name) {

		for (NivelRiesgo e : values()) {
			if (e.name.equals(name))
				return e.getIdEstatus();
		}
		
		return 0;

	}

	public Integer getIdEstatus() {
		return idEstatus;
	}

	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
