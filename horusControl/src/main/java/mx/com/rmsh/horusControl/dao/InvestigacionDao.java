package mx.com.rmsh.horusControl.dao;

import java.util.List;

import mx.com.rmsh.horusControl.vo.Investigacion;
import mx.com.rmsh.horusControl.vo.InvestigacionRequest;
import mx.com.rmsh.horusControl.vo.ReporteRequest;
import mx.com.rmsh.horusControl.vo.UserHorus;


public interface InvestigacionDao {

	public List<Investigacion> getReportes(ReporteRequest request);
	
	public Long guardaInvestigacion(InvestigacionRequest request);

}
