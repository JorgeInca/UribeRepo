package mx.com.rmsh.horusControl.vo;

public class SensorRequest {

	public String fechaInicio;
	public String tipoSensor;

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getTipoSensor() {
		return tipoSensor;
	}

	public void setTipoSensor(String tipoSensor) {
		this.tipoSensor = tipoSensor;
	}

	@Override
	public String toString() {
		return "SensorRequest [fechaInicio=" + fechaInicio + ", tipoSensor=" + tipoSensor + "]";
	}

}
