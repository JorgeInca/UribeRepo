package mx.com.rmsh.horusControl.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import mx.com.rmsh.horusControl.vo.Investigacion;
import mx.com.rmsh.horusControl.vo.InvestigacionRequest;
import mx.com.rmsh.horusControl.vo.Masiva;
import mx.com.rmsh.horusControl.vo.MasivaRequest;
import mx.com.rmsh.horusControl.vo.OrigenesBorradoRequest;
import mx.com.rmsh.horusControl.vo.ReporteRequest;
import mx.com.rmsh.horusControl.vo.RiesgoRequest;

@Repository
public class InvestigacionDaoImpl implements InvestigacionDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	String QUERY_GET_REPORTE="SELECT "
			+ " A.id_investigacion as id_investigacion,"
			+ " B.id_usuario as idUsuario,"
			+ " B.nombre as nombreUsuario,"
			+ " C.id_empresa as idEmpresa,"
			+ " C.nombre as nombreEmpresa,"
			+ " A.apellidos as apellidos,"
			+ " A.primer_nombre as primerNombre,"			
			+ " A.json_desc as json_desc,"
			+ " A.nivel_riesgo_inicial as riesgoInicial,"
			+ " A.nivel_riesgo_final as riesgoFinal,"
			+ " A.fecha_creacion as fecha_creacion,"
			+ " D.TITULO as nombreCampania, "
			+ " A.origenes_eliminados, "
			+ " A.menciones_eliminadas, "
			+ " A.estatus as estatus "
			+ " FROM investigacion A inner join usuario B on  A.id_usuario = B.id_usuario "
			+ " left join empresa C on  B.id_empresa = C.id_empresa "
			+ " left join investigacion_masiva D on  A.id_masiva = D.id_investigacion_masiva "
			+ " where A.estatus != 4 order by A.fecha_creacion desc ";
	
	String QUERY_CREATE_REPORTEE=
			"INSERT INTO `horusDatabase`.`investigacion`"
			+ "("
			+ "`apellidos`,"
			+ "`primer_nombre`,"			
			+ "`id_usuario`,"
			+ "`id_empresa`,"
			+ "`json_desc`,"
			+ "`nivel_riesgo_inicial`,"
			+ "`fecha_creacion`,"
			+ "`id_masiva`,"
			+ "`estatus`,"
			+ "`rfc`"
			+ ")"
			+ "VALUES"
			+ "(?,?,?,?,?,?,?,?,?,?)";

	String QUERY_CREATE_MASIVA=
			"INSERT INTO `horusDatabase`.`investigacion_masiva`"
			+ "("
			+ "`ID_USUARIO`,"
			+ "`TITULO`,"
			+ "`estatus`"
			+ ")"
			+ "VALUES"
			+ "(?,?,1)";
	
	String QUERY_GET_JSON_INVESTIGACION_BYID =
			"Select json_desc from investigacion A where A.id_investigacion = ? ";
	
	String QUERY_GET_JSON_INVESTIGACION_MASIVA = "select A.* , B.nombre as nombreUsuario from investigacion_masiva A  inner join usuario B on B.id_usuario = A.id_usuario where A.estatus in (1,2) ";
	
	String QUERY_GET_INVESTIGACION_REQUEST_FOR_AWS = "Select * from investigacion A inner join investigacion_masiva B on A.id_masiva = B.id_investigacion_masiva where A.id_masiva != 0 and (A.estatus = 2 or A.estatus = 3) order by A.id_investigacion desc limit 1000 ";
	
	String QUERY_UPDATE_MASIVAEND = "update investigacion_masiva A set A.estatus = 2 where A.id_investigacion_masiva in ( SELECT * FROM (select B.id_investigacion_masiva from investigacion_masiva B LEFT JOIN investigacion C ON B.id_investigacion_masiva = C.id_masiva WHERE (C.estatus != 2 OR C.estatus IS NULL) AND B.estatus = 1) D)";
	
	String QUERY_SET_JSON_BY_ID = "update investigacion A set A.json_desc = ? where A.id_investigacion = ?";
	
	String QUERY_ERROR_INVESTIGACION = "update investigacion A set A.estatus = 3 where A.id_investigacion = ?";
	
	String QUERY_FINISH_INVESTIGACION = "update investigacion A set A.estatus = 1 where A.id_investigacion = ?";
	
	String QUERY_SET_RIESGO_BYID = "update investigacion A set A.nivel_riesgo_inicial = ? where A.id_investigacion = ?";
	
	String QUERY_ELIMINA_BYID = "update investigacion A set A.ESTATUS = 4 where A.id_investigacion = ?";
	
	String QUERY_UPDATE_RIESGO_FINALBYID = "update investigacion A set A.nivel_riesgo_final = ? where A.id_investigacion = ?";
	
	String QUERY_GET_RIESGO_FINAL_BYID = "Select nivel_riesgo_final from investigacion A where A.id_investigacion = ? ";
	
	String QUERY_GET_DEL_ORIGEN_BYID = "Select origenes_eliminados from investigacion A where A.id_investigacion = ? ";
	
	String QUERY_GET_DEL_MENTIONS_BYID = "Select menciones_eliminadas from investigacion A where A.id_investigacion = ? ";
	
	String QUERY_UPDATE_ORIGENESELIMINADOS = "update investigacion A set A.origenes_eliminados = ? where A.id_investigacion = ?";
	
	String QUERY_UPDATE_MENTIONSELIMINADOS = "update investigacion A set A.menciones_eliminadas = ? where A.id_investigacion = ?";
	
	String QUERY_GET_EMPRESA_USUARIO_BYID =
			"select b.nombre from usuario A inner join empresa b on A.id_empresa = b.id_empresa where A.id_usuario = ? ";
	
	String QUERY_GET_USER_INVESTIGACION_BYID =
			"select id_usuario from investigacion  where id_investigacion = ? ";

	@SuppressWarnings("deprecation")
	@Override
	public List<Investigacion> getReportes(ReporteRequest request) {

		return jdbcTemplate.query(QUERY_GET_REPORTE,
				
				(rs, rowNum) -> new Investigacion(
						rs.getLong("id_investigacion"), 
						rs.getLong("idUsuario"),
						rs.getString("nombreUsuario"), 
						rs.getLong("idEmpresa"), 
						rs.getString("nombreEmpresa"), 
						rs.getString("apellidos"), 
						rs.getString("primerNombre"),						 
						rs.getString("json_desc"), 
						(Integer) rs.getObject("riesgoInicial"), 
						(Integer) rs.getObject("riesgoFinal"), 
						rs.getTimestamp("fecha_creacion"),
						rs.getString("nombreCampania"),
						rs.getString("origenes_eliminados"), 
						rs.getString("menciones_eliminadas"),
						rs.getInt("estatus")
						));
	}

	@Override
	public Long guardaInvestigacion(InvestigacionRequest request) {
		// TODO Auto-generated method stub
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
	    jdbcTemplate.update(connection -> {
	        PreparedStatement ps = connection
	          .prepareStatement(QUERY_CREATE_REPORTEE,Statement.RETURN_GENERATED_KEYS);
	          ps.setString(1, request.getLastname());
	          ps.setString(2, request.getFirstname());	          
	          ps.setLong(3, request.getIdUsuario());
	          ps.setLong(4, 121l);
	          ps.setString(5, request.getInvestigacionJson());
	          ps.setLong(6, request.getNivel_riesgo());
	          ps.setTimestamp(7, new Timestamp( new Date().getTime() ) );
	          ps.setLong(8, 0L );
	          ps.setLong(9, request.getIdEstatus() );
	          ps.setString(10, request.getRfc() );
	          return ps;
	        }, keyHolder);

	        return (long) keyHolder.getKey().longValue();
	    }

	@Override
	@SuppressWarnings("deprecation")
	public String getInvestigacionById(Long investigacionid) {
		// TODO Auto-generated method stub
		 
		String jsonInvestigacion = (String) jdbcTemplate.queryForObject(
				 QUERY_GET_JSON_INVESTIGACION_BYID, new Object[] { investigacionid }, String.class);
		 
		 return jsonInvestigacion;
	}

	@Override
	public void guardaInvestigacionMasiva(List<InvestigacionRequest> request) {		

        jdbcTemplate.batchUpdate(QUERY_CREATE_REPORTEE, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
            	InvestigacionRequest entity = request.get(i);
                preparedStatement.setString(1, entity.getLastname());
                preparedStatement.setString(2, entity.getFirstname());
                preparedStatement.setLong(3, entity.getIdUsuario());
                preparedStatement.setLong(4, 121l);
                preparedStatement.setString(5, entity.getInvestigacionJson());
                
                if( ! (entity.getNivel_riesgo() == null) ) {
                	preparedStatement.setLong(6, entity.getNivel_riesgo());
                }else {
                	preparedStatement.setNull(6, Types.TINYINT);
				}
                
                preparedStatement.setTimestamp(7,new Timestamp( new Date().getTime() ) );
                preparedStatement.setLong(8, entity.getIdMasiva());  
                preparedStatement.setLong(9, entity.getIdEstatus() );
                preparedStatement.setString(10, entity.getRfc());
                
            }

            @Override
            public int getBatchSize() {
                return request.size();
            }
        });
	}

	
	@SuppressWarnings("deprecation")
	@Override
	public List<Masiva> getMasivas(MasivaRequest request) {

		return jdbcTemplate.query(QUERY_GET_JSON_INVESTIGACION_MASIVA,
				
				(rs, rowNum) -> new Masiva(
						rs.getLong("id_investigacion_masiva"),
						rs.getString("TITULO"), 
						rs.getLong("ID_USUARIO"),
						rs.getString("nombreUsuario"),
						rs.getTimestamp("fecha_creacion"), 
						rs.getInt("estatus")						
						));
	}
	
	@Override
	public Long guardaMasiva(MasivaRequest request) {
		// TODO Auto-generated method stub
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
	    jdbcTemplate.update(connection -> {
	        PreparedStatement ps = connection
	          .prepareStatement(QUERY_CREATE_MASIVA,Statement.RETURN_GENERATED_KEYS);
	          ps.setLong(1, request.getIdUsuario());
	          ps.setString(2, request.getNombreCampaña());
	          return ps;
	        }, keyHolder);

	        return (long) keyHolder.getKey().longValue();
	    }

	@Override
	public List<InvestigacionRequest> getMasivasToAWS(Integer limit) {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(QUERY_GET_INVESTIGACION_REQUEST_FOR_AWS,
				
				(rs, rowNum) -> new InvestigacionRequest(
						rs.getLong("id_investigacion"),
						rs.getString("apellidos"), 
						rs.getString("primer_nombre"),
						14,
						rs.getString("titulo")
						));
	}

	@Override
	public void updateCampanias() {
		
		// TODO Auto-generated method stub
		jdbcTemplate.update(connection -> {
	        PreparedStatement ps = connection
	          .prepareStatement(QUERY_UPDATE_MASIVAEND);	         
	          return ps;
	        });
		
	}

	@Override
	public void setJsonById(Long idInvestigacion, String json) {
		// TODO Auto-generated method stub
				jdbcTemplate.update(connection -> {
			        PreparedStatement ps = connection
			          .prepareStatement(QUERY_SET_JSON_BY_ID);
			        
			        	ps.setString(1, json);
			        	ps.setLong(2,idInvestigacion);	        	
			        	
			          return ps;
			        });
		
	}

	@Override
	public void finishInvestigacionTask(Long idInvestigacion) {
		// TODO Auto-generated method stub
				jdbcTemplate.update(connection -> {
			        PreparedStatement ps = connection
			          .prepareStatement(QUERY_FINISH_INVESTIGACION);
			        
			         ps.setLong(1,idInvestigacion);
			         
			          return ps;
			        });
		
	}
	
	@Override
	public void errorInvestigacionTask(Long idInvestigacion) {
		// TODO Auto-generated method stub
				jdbcTemplate.update(connection -> {
			        PreparedStatement ps = connection
			          .prepareStatement(QUERY_ERROR_INVESTIGACION);
			        
			         ps.setLong(1,idInvestigacion);
			         
			          return ps;
			        });
		
	}

	@Override
	public void updateRiesgoFinalById(RiesgoRequest request) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(connection -> {
	        PreparedStatement ps = connection
	          .prepareStatement(QUERY_UPDATE_RIESGO_FINALBYID);
	        
	        	ps.setInt(1, request.getRiesgo());
	        	ps.setLong(2,request.getIdInvestigacion());	        	
	        	
	          return ps;
	        });		
	}
	
	@Override
	public void setRiesgoInicialById(RiesgoRequest request) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(connection -> {
	        PreparedStatement ps = connection
	          .prepareStatement(QUERY_SET_RIESGO_BYID);
	        
	        	ps.setInt(1, request.getRiesgo());
	        	ps.setLong(2,request.getIdInvestigacion());	        	
	        	
	          return ps;
	        });		
	}

	@Override
	public Integer getRiesgoFInal(Long idInvestigacion) {
		Integer riesgoFinalInt = (Integer) jdbcTemplate.queryForObject(
				QUERY_GET_RIESGO_FINAL_BYID, new Object[] { idInvestigacion }, Integer.class);
		 
		 return riesgoFinalInt;
		
	}

	@Override
	public String getEliminadosOrigen(Long idInvestigacion) {
		// TODO Auto-generated method stub
		String resultado = (String) jdbcTemplate.queryForObject(
				QUERY_GET_DEL_ORIGEN_BYID, new Object[] { idInvestigacion }, String.class);
		 
		 return resultado;
	}

	@Override
	public String getEliminadoMentions(Long idInvestigacion) {
		// TODO Auto-generated method stub
		String resultado = (String) jdbcTemplate.queryForObject(
				QUERY_GET_DEL_MENTIONS_BYID, new Object[] { idInvestigacion }, String.class);
		 
		 return resultado;
	}

	@Override
	public void updateEliminadosOrigen(OrigenesBorradoRequest request) {
		jdbcTemplate.update(connection -> {
	        PreparedStatement ps = connection
	          .prepareStatement(QUERY_UPDATE_ORIGENESELIMINADOS);
	        
	        	ps.setString(1, request.getNuevoValor());
	        	ps.setLong(2,request.getIdInvestigacion());	        	
	        	
	          return ps;
	        });		
		
	}

	@Override
	public void updateEliminadosMention(OrigenesBorradoRequest request) {
		jdbcTemplate.update(connection -> {
	        PreparedStatement ps = connection
	          .prepareStatement(QUERY_UPDATE_MENTIONSELIMINADOS);
	        
	        	ps.setString(1, request.getNuevoValor());
	        	ps.setLong(2,request.getIdInvestigacion());	        	
	        	
	          return ps;
	        });		
		
	}

	@Override
	public String getNombreClientePorUsuario(Long idUsuario) {
		// TODO Auto-generated method stub
		 
		String nombreUsuario = (String) jdbcTemplate.queryForObject(
				QUERY_GET_EMPRESA_USUARIO_BYID, new Object[] { idUsuario }, String.class);
		 
		 return nombreUsuario;
		
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public Long getIdUserByInvestigacionId(Long investigacionid) {
		// TODO Auto-generated method stub
		 
		Long idUsuario = (Long) jdbcTemplate.queryForObject(
				QUERY_GET_USER_INVESTIGACION_BYID, new Object[] { investigacionid }, Long.class);
		 
		 return idUsuario;
	}
	
	@Override
	public void eliminaRegistrioById(RiesgoRequest request) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(connection -> {
	        PreparedStatement ps = connection
	          .prepareStatement(QUERY_ELIMINA_BYID);
	                	
	        	ps.setLong(1,request.getIdInvestigacion());	        	
	        	
	          return ps;
	        });		
	}
	
	
}