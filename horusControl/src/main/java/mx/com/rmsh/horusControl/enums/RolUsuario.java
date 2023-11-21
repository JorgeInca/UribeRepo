package mx.com.rmsh.horusControl.enums;

public enum RolUsuario {
	
	ADMIN(0, "ADMIN"),
	USER(1, "USER");

	RolUsuario(Integer idRol, String name) {
		this.idRol = idRol;
		this.name = name;
	}

	private Integer idRol;
	private String name;

	public static String getNameyId(Integer id) {
		
		//System.out.println(" tha fak " + id);
		
		if( id == null ) {
			return USER.getName();
		}

		for (RolUsuario e : values()) {
			
			if( e.idRol == null ) {
				if( id == null) {
					return e.getName();
				}
			}else {

				if (e.idRol.equals(id))
					return e.getName();
				
			}
						
		}
		return "USER";

	}

	public static Integer getIdByName(String name) {

		for (RolUsuario e : values()) {
			if (e.name.equals(name))
				return e.getIdRol();
		}
		
		return 0;

	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}

