package mx.com.rmsh.horusControl.service;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;

import mx.com.rmsh.horusControl.vo.Investigacion;
import mx.com.rmsh.horusControl.vo.InvestigacionRequest;
import mx.com.rmsh.horusControl.vo.Masiva;
import mx.com.rmsh.horusControl.vo.MasivaRequest;
import mx.com.rmsh.horusControl.vo.OrigenesBorradoRequest;
import mx.com.rmsh.horusControl.vo.ReporteRequest;
import mx.com.rmsh.horusControl.vo.RiesgoRequest;
import mx.com.rmsh.horusControl.vo.UserHorus;
import mx.com.rmsh.horusControl.vo.ca.CampaniaCA;
import mx.com.rmsh.horusControl.vo.ca.ClienteCA;


public interface InvestigacionService {
	
	public List<Investigacion> getReportes(ReporteRequest request);
	
	public List<Investigacion> getReportesPorFiltro(ReporteRequest request);
	
	public Long guardaInvestigacion(InvestigacionRequest request);
			
	public List<UserHorus> getUser();
	
	public Long guardaUsuario(UserHorus user);
	
	public Long guardaInvestigacionMasiva(MasivaRequest masivaRequest) throws IOException;
	
	public UserHorus getUsuarioById(Long id_usuario);
	
	public long eliminiarUsuario(Long id_usuario);
	
	public UserHorus editUser(Long id_usuario);
	
	public long updateUser(UserHorus user);
	
	public ResponseEntity<ByteArrayResource> getPDFInvestigacion(Long idInvestigacion,String campaign, boolean s3Exists);
	
	public List<Masiva> getMasivas(MasivaRequest request);
	
	public void setRiesgoById(RiesgoRequest request);
	
	public void updateRiesgoFinalById(RiesgoRequest request);
	
	public void updateEliminadosOrigen( OrigenesBorradoRequest request );
	
	public void updateEliminadosMention( OrigenesBorradoRequest request );
	
	public List<InvestigacionRequest> getMasivasToAWS(Integer limit);
	
	public void setJsonById( Long idInvestigacion , String json );
	
	public void finishInvestigacionTask( Long idInvestigacion );
	
	public void errorInvestigacionTask( Long idInvestigacion );
	
	public String getNombreClientePorUsuario(Long idUsuario);
	
	public void updateCampanias();
	
	public void eliminaRegistrioById(RiesgoRequest request);

	public List<CampaniaCA> getCampaniaCA(ReporteRequest request);
	
	public List<ClienteCA> getClientesCatalogo(ReporteRequest request);
	
}
