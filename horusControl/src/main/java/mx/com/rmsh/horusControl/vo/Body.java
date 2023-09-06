package mx.com.rmsh.horusControl.vo;

import java.util.ArrayList;

public class Body {

	public ArrayList<Origen> origen;
	public Integer nivel_riesgo;
	public ArrayList<String> parametros_busqueda;
	public ArrayList<String> fuentes;
	
	

	public ArrayList<Origen> getOrigen() {
		return origen;
	}



	public void setOrigen(ArrayList<Origen> origen) {
		this.origen = origen;
	}



	public Integer getNivel_riesgo() {
		return nivel_riesgo;
	}



	public void setNivel_riesgo(Integer nivel_riesgo) {
		this.nivel_riesgo = nivel_riesgo;
	}



	public ArrayList<String> getParametros_busqueda() {
		return parametros_busqueda;
	}



	public void setParametros_busqueda(ArrayList<String> parametros_busqueda) {
		this.parametros_busqueda = parametros_busqueda;
	}



	public ArrayList<String> getFuentes() {
		return fuentes;
	}



	public void setFuentes(ArrayList<String> fuentes) {
		this.fuentes = fuentes;
	}



	@Override
	public String toString() {
		return "Body [origen=" + origen + ", nivel_riesgo=" + nivel_riesgo + ", parametros_busqueda="
				+ parametros_busqueda + ", fuentes=" + fuentes + "]";
	}

}