package mx.com.rmsh.horusControl.vo;

import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class Origen {
	public String nombre;
	public String url;
	public int resultado;
	@SerializedName("texto")
	public Map<String, String> texto;

	@SerializedName("free_text")
	public String free_text;
	public String summary;
	public String fuente;
	public String score;
	public String description;
	public String risk_level;
	public Integer isJSON;
	public String category;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getResultado() {
		return resultado;
	}

	public void setResultado(int resultado) {
		this.resultado = resultado;
	}

	public String getFree_text() {
		return free_text;
	}

	public void setFree_text(String free_text) {
		this.free_text = free_text;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRisk_level() {
		return risk_level;
	}

	public void setRisk_level(String risk_level) {
		this.risk_level = risk_level;
	}

	public Map<String, String> getTexto() {
		return texto;
	}

	public void setTexto(Map<String, String> texto) {
		this.texto = texto;
	}

	public Integer getIsJSON() {
		return isJSON;
	}

	public void setIsJSON(Integer isJSON) {
		this.isJSON = isJSON;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Origen [nombre=" + nombre + ", url=" + url + ", resultado=" + resultado + ", texto=" + texto
				+ ", free_text=" + free_text + ", summary=" + summary + ", fuente=" + fuente + ", score=" + score
				+ ", description=" + description + ", risk_level=" + risk_level + ", isJSON=" + isJSON + ", category="
				+ category + "]";
	}

}