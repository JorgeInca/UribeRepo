package mx.com.rmsh.horusControl.dao;

import java.util.List;

import mx.com.rmsh.horusControl.vo.SensorRequest;
import mx.com.rmsh.horusControl.vo.SensorRow;


public interface SensorDao {

	public List<SensorRow> getSensorArgosTemp(SensorRequest request);
	public List<SensorRow> getSensorArgosHum(SensorRequest request);
	public List<SensorRow> getSensorOpcomLSO(SensorRequest request);
	public List<SensorRow> getSensorBallufPresion(SensorRequest request);

}
