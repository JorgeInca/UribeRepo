package mx.com.rmsh.horusControl.vo;

import java.util.Map;

import org.springframework.web.servlet.function.ServerRequest.Headers;

import com.google.gson.annotations.SerializedName;

public class InvestigacionLAMBDA {

	public Long idInvestigacion;
	public int statusCode;
	public Long created;	
	public String jsonBD;
	public Body body;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public Long getCreated() {
		return created;
	}

	public void setCreated(Long created) {
		this.created = created;
	}

	public String getJsonBD() {
		return jsonBD;
	}

	public Long getIdInvestigacion() {
		return idInvestigacion;
	}

	public void setIdInvestigacion(Long idInvestigacion) {
		this.idInvestigacion = idInvestigacion;
	}

	public void setJsonBD(String jsonBD) {
		this.jsonBD = jsonBD;
	}

	@Override
	public String toString() {
		return "InvestigacionLAMBDA [idInvestigacion=" + idInvestigacion + ", statusCode=" + statusCode + ", created="
				+ created + ", jsonBD=" + jsonBD + ", body=" + body + "]";
	}

	

}



class Origen {
	public String nombre;
	public String url;
	public int resultado;
	@SerializedName("texto")
	public Map<String , String> texto;
	
	@SerializedName("free_text")
	public String free_text;
	public String summary;
	public String fuente;
	public String score;
	public String description;
	public String risk_level;
	public Integer isJSON;
	
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
	@Override
	public String toString() {
		return "Origen [nombre=" + nombre + ", url=" + url + ", resultado=" + resultado + ", texto=" + texto
				+ ", free_text=" + free_text + ", summary=" + summary + ", fuente=" + fuente + ", score=" + score
				+ ", description=" + description + ", risk_level=" + risk_level + ", isJSON=" + isJSON + "]";
	}
	
	

	

}

class Mentions {
	public String title;
	@SerializedName("type")
	public String link;	
	public String description;
	public String engine;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEngine() {
		return engine;
	}
	public void setEngine(String engine) {
		this.engine = engine;
	}
	@Override
	public String toString() {
		return "Mentions [title=" + title + ", link=" + link + ", description=" + description + ", engine=" + engine
				+ "]";
	}
	
	

}

class Root {
	public int statusCode;
	public Headers headers;
	public Body body;
}