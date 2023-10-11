package mx.com.rmsh.horusControl.service;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;

import mx.com.rmsh.horusControl.vo.Empresas;
import mx.com.rmsh.horusControl.vo.Investigacion;
import mx.com.rmsh.horusControl.vo.InvestigacionRequest;
import mx.com.rmsh.horusControl.vo.MasivaRequest;
import mx.com.rmsh.horusControl.vo.ReporteRequest;
import mx.com.rmsh.horusControl.vo.UserHorus;

public interface CatalagosService {
	
	
	public List<Empresas> getEmpresa(Empresas request);
	
	
	

}
