package mx.com.rmsh.horusControl.dao;

import java.util.List;

import mx.com.rmsh.horusControl.vo.Investigacion;
import mx.com.rmsh.horusControl.vo.ReporteRequest;


public interface InvestigacionDao {

	public List<Investigacion> getReportes(ReporteRequest request);

}
