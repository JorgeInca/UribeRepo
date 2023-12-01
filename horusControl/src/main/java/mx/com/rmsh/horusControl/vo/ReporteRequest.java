package mx.com.rmsh.horusControl.vo;

import java.util.Arrays;

public class ReporteRequest {

	public Long idUserHorus;
	public String rolUser;

	public String filtro_nombre;
	public String filtro_apellidos;
	public String filtro_fechas;
	public String[] filtro_nivelesRiesgo;
	public String[] filtro_campañas;
	public String[] filtro_empresas;

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

	public String getFiltro_nombre() {
		return filtro_nombre;
	}

	public void setFiltro_nombre(String filtro_nombre) {
		this.filtro_nombre = filtro_nombre;
	}

	public String getFiltro_apellidos() {
		return filtro_apellidos;
	}

	public void setFiltro_apellidos(String filtro_apellidos) {
		this.filtro_apellidos = filtro_apellidos;
	}

	public String getFiltro_fechas() {
		return filtro_fechas;
	}

	public void setFiltro_fechas(String filtro_fechas) {
		this.filtro_fechas = filtro_fechas;
	}

	public String[] getFiltro_nivelesRiesgo() {
		return filtro_nivelesRiesgo;
	}

	public void setFiltro_nivelesRiesgo(String[] filtro_nivelesRiesgo) {
		this.filtro_nivelesRiesgo = filtro_nivelesRiesgo;
	}

	public String[] getFiltro_campañas() {
		return filtro_campañas;
	}

	public void setFiltro_campañas(String[] filtro_campañas) {
		this.filtro_campañas = filtro_campañas;
	}

	public String[] getFiltro_empresas() {
		return filtro_empresas;
	}

	public void setFiltro_empresas(String[] filtro_empresas) {
		this.filtro_empresas = filtro_empresas;
	}

	@Override
	public String toString() {
		return "ReporteRequest [idUserHorus=" + idUserHorus + ", rolUser=" + rolUser + ", filtro_nombre="
				+ filtro_nombre + ", filtro_apellidos=" + filtro_apellidos + ", filtro_fechas=" + filtro_fechas
				+ ", filtro_nivelesRiesgo=" + Arrays.toString(filtro_nivelesRiesgo) + ", filtro_campañas="
				+ Arrays.toString(filtro_campañas) + ", filtro_empresas=" + Arrays.toString(filtro_empresas) + "]";
	}

}