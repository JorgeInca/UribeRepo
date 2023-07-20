package mx.com.rmsh.horusControl.service;

import java.util.List;

import mx.com.rmsh.horusControl.vo.SensorRequest;
import mx.com.rmsh.horusControl.vo.SensorRow;

public interface SensorService {
	
	public List<SensorRow> getSensorArgosTemp(SensorRequest request);
	public List<SensorRow> getSensorArgosHum(SensorRequest request);
	public List<SensorRow> getSensorOpcomLSO(SensorRequest request);
	public List<SensorRow> getSensorBallufPresion(SensorRequest request);
	
	public List<SensorRow> getSensorTempAndPressure(SensorRequest request);
	
}
