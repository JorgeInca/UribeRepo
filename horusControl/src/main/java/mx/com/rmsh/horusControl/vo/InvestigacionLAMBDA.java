package mx.com.rmsh.horusControl.vo;

import org.springframework.web.servlet.function.ServerRequest.Headers;

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
	public String texto;
	public String summary;
	public String fuente;
	public String score;
	public String description;
	public String risk_level;

	@Override
	public String toString() {
		return "Origen [nombre=" + nombre + ", url=" + url + ", resultado=" + resultado + ", texto=" + texto
				+ ", summary=" + summary + ", fuente=" + fuente + ", score=" + score + ", description=" + description
				+ ", risk_level=" + risk_level + "]";
	}

}

class Root {
	public int statusCode;
	public Headers headers;
	public Body body;
}