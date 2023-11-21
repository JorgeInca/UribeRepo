package mx.com.rmsh.horusControl.dao;

import java.util.List;

import mx.com.rmsh.horusControl.vo.Investigacion;
import mx.com.rmsh.horusControl.vo.InvestigacionRequest;
import mx.com.rmsh.horusControl.vo.Masiva;
import mx.com.rmsh.horusControl.vo.MasivaRequest;
import mx.com.rmsh.horusControl.vo.OrigenesBorradoRequest;
import mx.com.rmsh.horusControl.vo.ReporteRequest;
import mx.com.rmsh.horusControl.vo.RiesgoRequest;

public interface InvestigacionDao {

	public List<Investigacion> getReportesByAdmin(ReporteRequest request);
	
	public List<Investigacion> getReportesByUser(ReporteRequest request);

	public Long guardaInvestigacion(InvestigacionRequest request);	

	public String getInvestigacionById(Long investigacionid);
	
	public void guardaInvestigacionMasiva(List<InvestigacionRequest> request);
	
	public List<Masiva> getMasivas(MasivaRequest request);
	
	public Long guardaMasiva(MasivaRequest request);
	
	public List<InvestigacionRequest> getMasivasToAWS(Integer limit);
	
	public void updateCampanias();
	
	public void setJsonById( Long idInvestigacion , String json );
	
	public void finishInvestigacionTask( Long idInvestigacion );
	
	public void errorInvestigacionTask( Long idInvestigacion );
	
	public void setRiesgoInicialById( RiesgoRequest request );
	
	public void updateRiesgoFinalById( RiesgoRequest request );
	
	public Integer getRiesgoFInal( Long idInvestigacion );
	
	public String getEliminadosOrigen( Long idInvestigacion );
	
	public String getEliminadoMentions( Long idInvestigacion );
	
	public void updateEliminadosOrigen( OrigenesBorradoRequest request );
	
	public void updateEliminadosMention( OrigenesBorradoRequest request );
	
	public String getNombreClientePorUsuario(Long idUsuario);
	
	public Long getIdUserByInvestigacionId(Long investigacionid);
	
	public void eliminaRegistrioById(RiesgoRequest request);

}
