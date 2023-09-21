package mx.com.rmsh.horusControl.service;

import java.io.IOException;
import java.util.List;

import mx.com.rmsh.horusControl.vo.Investigacion;
import mx.com.rmsh.horusControl.vo.InvestigacionRequest;
import mx.com.rmsh.horusControl.vo.MasivaRequest;
import mx.com.rmsh.horusControl.vo.ReporteRequest;
import mx.com.rmsh.horusControl.vo.UserHorus;


public interface InvestigacionService {
	
	public List<Investigacion> getReportes(ReporteRequest request);
	
	public Long guardaInvestigacion(InvestigacionRequest request);
			
	public List<UserHorus> getUser();
	
	public Long guardaUsuario(UserHorus user);
	
	public List<InvestigacionRequest> guardaInvestigacionMasiva(MasivaRequest masivaRequest) throws IOException;
	
	public UserHorus getUsuarioById(Long id_usuario);
	
	public long eliminiarUsuario(Long id_usuario);
	
}
