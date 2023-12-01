package mx.com.rmsh.horusControl.vo.ca;

public class ClienteCA {

	public Long idCliente;
	public String Nombre;

	public ClienteCA() {
		super();
	}

	public ClienteCA(Long idCliente, String nombre) {
		super();
		this.idCliente = idCliente;
		Nombre = nombre;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	@Override
	public String toString() {
		return "ClienteCA [idCliente=" + idCliente + ", Nombre=" + Nombre + "]";
	}

}