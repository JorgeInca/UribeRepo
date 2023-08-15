package mx.com.rmsh.horusControl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.rmsh.horusControl.dao.InvestigacionDao;
import mx.com.rmsh.horusControl.vo.Investigacion;
import mx.com.rmsh.horusControl.vo.ReporteRequest;

@Service
public class InvestigacionServiceImpl implements InvestigacionService {

	@Autowired
	InvestigacionDao dao;

	@Override
	public List<Investigacion> getReportes(ReporteRequest request) {
		
		return dao.getReportes(request);
	}


}
