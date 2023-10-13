package mx.com.rmsh.horusControl.vo;

import java.util.ArrayList;

/**
 * 
 */
public class Body {

	public ArrayList<Origen> origen;
	public Integer nivel_riesgo;
	public Integer nivel_riesgo_editado;
	public ArrayList<String> parametros_busqueda;
	public ArrayList<String> fuentes;
	public ArrayList<Mentions> mentions;
	public String eliminadosOrigenes;
	public String eliminadosMentions;

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

	public ArrayList<Mentions> getMentions() {
		return mentions;
	}

	public void setMentions(ArrayList<Mentions> mentions) {
		this.mentions = mentions;
	}

	public Integer getNivel_riesgo_editado() {
		return nivel_riesgo_editado;
	}

	public void setNivel_riesgo_editado(Integer nivel_riesgo_editado) {
		this.nivel_riesgo_editado = nivel_riesgo_editado;
	}

	public String getEliminadosOrigenes() {
		return eliminadosOrigenes;
	}

	public void setEliminadosOrigenes(String eliminadosOrigenes) {
		this.eliminadosOrigenes = eliminadosOrigenes;
	}

	public String getEliminadosMentions() {
		return eliminadosMentions;
	}

	public void setEliminadosMentions(String eliminadosMentions) {
		this.eliminadosMentions = eliminadosMentions;
	}

	@Override
	public String toString() {
		return "Body [origen=" + origen + ", nivel_riesgo=" + nivel_riesgo + ", nivel_riesgo_editado="
				+ nivel_riesgo_editado + ", parametros_busqueda=" + parametros_busqueda + ", fuentes=" + fuentes
				+ ", mentions=" + mentions + ", eliminadosOrigenes=" + eliminadosOrigenes + ", eliminadosMentions="
				+ eliminadosMentions + "]";
	}

}