package mx.com.rmsh.horusControl.enums;

public enum EstatusInvestigacion {

	FINALIZADO(1, "FINALIZADO"),
	PENDIENTE(2, "PENDIENTE"),
	DESCARTADA(3, "DESCARTADO");

	EstatusInvestigacion(Integer idEstatus, String name) {
		this.idEstatus = idEstatus;
		this.name = name;
	}

	private Integer idEstatus;
	private String name;

	public static String getNameyId(Integer id) {

		for (EstatusInvestigacion e : values()) {
			if (e.idEstatus.equals(id))
				return e.getName();
		}
		return "Desconocido";

	}

	public static Integer getIdByName(String name) {

		for (EstatusInvestigacion e : values()) {
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
