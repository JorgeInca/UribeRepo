package mx.com.rmsh.horusControl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.rmsh.horusControl.dao.InvestigacionDao;
import mx.com.rmsh.horusControl.dao.SecurityDao;
import mx.com.rmsh.horusControl.vo.Investigacion;
import mx.com.rmsh.horusControl.vo.InvestigacionRequest;
import mx.com.rmsh.horusControl.vo.ReporteRequest;
import mx.com.rmsh.horusControl.vo.UserHorus;

@Service
public class InvestigacionServiceImpl implements InvestigacionService {

	@Autowired
	InvestigacionDao dao;
	
	@Autowired
	SecurityDao daoUser;
	

	@Override
	public List<Investigacion> getReportes(ReporteRequest request) {
		
		return dao.getReportes(request);
	}

	@Override
	public Long guardaInvestigacion(InvestigacionRequest request) {
		// TODO Auto-generated method stub
		return dao.guardaInvestigacion(request);
	}


	@Override
	public List<UserHorus> getUser() {
		return daoUser.getUser();
	} 

	


}
