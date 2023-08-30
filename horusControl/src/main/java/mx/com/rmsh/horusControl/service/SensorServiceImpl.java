package mx.com.rmsh.horusControl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.rmsh.horusControl.dao.SensorDao;
import mx.com.rmsh.horusControl.vo.SensorRequest;
import mx.com.rmsh.horusControl.vo.SensorRow;

@Service
public class SensorServiceImpl implements SensorService {

	@Autowired
	SensorDao dao;

	public List<SensorRow> getSensorArgosTemp(SensorRequest request) {
		// TODO Auto-generated method stub
		List<SensorRow> response = dao.getSensorArgosTemp(request);

		System.out.println("*********getSensorArgosTemp [Service] " + response);

		return response;
	}

	public List<SensorRow> getSensorArgosHum(SensorRequest request) {
		// TODO Auto-generated method stub
		System.out.println("*********getSensorArgosHum [Service] ");
		List<SensorRow> response = dao.getSensorArgosHum(request);

		return response;
	}

	public List<SensorRow> getSensorOpcomLSO(SensorRequest request) {
		// TODO Auto-generated method stub
		System.out.println("*********getSensorOpcomLSO [Service] ");

		List<SensorRow> response = dao.getSensorOpcomLSO(request);

		return response;
	}

	public List<SensorRow> getSensorBallufPresion(SensorRequest request) {
		// TODO Auto-generated method stub
		List<SensorRow> response = dao.getSensorBallufPresion(request);

		System.out.println("*********getSensorBallufPresion [Service] " + response);

		return response;
	}

	public List<SensorRow> getSensorTempAndPressure(SensorRequest request) {
		// TODO Auto-generated method stub
		// Temp -> Pressure

		List<SensorRow> response = dao.getSensorArgosTemp(request);
		List<SensorRow> responsePress = dao.getSensorBallufPresion(request);

		if (response.size() > 0)
			response.get(0).setValue2(responsePress.get(0).getValue());

		System.out.println("*********getSensorTempAndPressure [Service] " + response);

		return response;
	}

}
