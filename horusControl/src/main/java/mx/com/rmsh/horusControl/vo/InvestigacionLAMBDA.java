package mx.com.rmsh.horusControl.vo;

import java.util.ArrayList;

import org.springframework.web.servlet.function.ServerRequest.Headers;

public class InvestigacionLAMBDA {
	
	public int statusCode;	
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
	@Override
	public String toString() {
		return "InvestigacionLAMBDA [statusCode=" + statusCode + ", body=" + body + "]";
	}
	
	
}

class Body {
	
	public ArrayList<Origen> origen;
	public Integer nivel_riesgo;
	public ArrayList<String> parametros_busqueda;
	public ArrayList<String> fuentes;
	
	@Override
	public String toString() {
		return "Body [origen=" + origen + ", nivel_riesgo=" + nivel_riesgo + ", parametros_busqueda="
				+ parametros_busqueda + ", fuentes=" + fuentes + "]";
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
	@Override
	public String toString() {
		return "Origen [nombre=" + nombre + ", url=" + url + ", resultado=" + resultado + ", texto=" + texto
				+ ", summary=" + summary + ", fuente=" + fuente + ", score=" + score + "]";
	}
	
	
}

class Root {
	public int statusCode;
	public Headers headers;
	public Body body;
}