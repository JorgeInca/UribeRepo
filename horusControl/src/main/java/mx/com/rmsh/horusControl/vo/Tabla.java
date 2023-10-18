package mx.com.rmsh.horusControl.vo;

public class Tabla {
	public String datoTabla;
	public String valorTabla;

	public Tabla() {
		super();
	}

	public Tabla(String datoTabla, String valorTabla) {
		super();
		this.datoTabla = datoTabla;
		this.valorTabla = valorTabla;
	}

	public String getDatoTabla() {
		return datoTabla;
	}

	public void setDatoTabla(String datoTabla) {
		this.datoTabla = datoTabla;
	}

	public String getValorTabla() {
		return valorTabla;
	}

	public void setValorTabla(String valorTabla) {
		this.valorTabla = valorTabla;
	}

	@Override
	public String toString() {
		return "Tabla [datoTabla=" + datoTabla + ", valorTabla=" + valorTabla + "]";
	}

}
