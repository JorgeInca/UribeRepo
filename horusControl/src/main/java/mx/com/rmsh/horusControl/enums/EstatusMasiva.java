package mx.com.rmsh.horusControl.enums;

public enum EstatusMasiva {

	EN_PROCESO(1, "EN PROCESO"),
	TERMINADA(2, "TERMINADA"),
	CANCELADA(3, "CANCELADA"),
	ERROR(4, "ERROR");

	EstatusMasiva(Integer idEstatus, String name) {
		this.idEstatus = idEstatus;
		this.name = name;
	}

	private Integer idEstatus;
	private String name;

	public static String getNameyId(Integer id) {
		
		System.out.println( "33 id " + id  );

		for (EstatusMasiva e : values()) {
			if (e.idEstatus.equals(id))
				return e.getName();
		}
		return "Desconocido";

	}

	public static Integer getIdByName(String name) {

		for (EstatusMasiva e : values()) {
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
