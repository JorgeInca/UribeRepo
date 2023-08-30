package mx.com.rmsh.horusControl.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import mx.com.rmsh.horusControl.vo.Investigacion;
import mx.com.rmsh.horusControl.vo.InvestigacionRequest;
import mx.com.rmsh.horusControl.vo.ReporteRequest;

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
			+ " A.segundo_nombre as segundoNombre,"
			+ " A.json_desc as json_desc,"
			+ " A.nivel_riesgo_inicial as riesgoInicial,"
			+ " A.nivel_riesgo_final as riesgoFinal,"
			+ " A.fecha_creacion as fecha_creacion,"
			+ " A.estatus as estatus "
			+ " FROM investigacion A inner join usuario B on  A.id_usuario = B.id_usuario left join empresa C on  B.id_empresa = C.id_empresa where A.estatus = 1 order by A.fecha_creacion desc;";
	
	String QUERY_CREATE_REPORTEE=
			"INSERT INTO `horusDatabase`.`investigacion`"
			+ "("
			+ "`apellidos`,"
			+ "`primer_nombre`,"
			+ "`segundo_nombre`,"
			+ "`id_usuario`,"
			+ "`id_empresa`,"
			+ "`json_desc`,"
			+ "`nivel_riesgo_inicial`"
			+ ")"
			+ "VALUES"
			+ "(?,?,?,?,?,?,?)";

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
						rs.getString("segundoNombre"), 
						rs.getString("json_desc"), 
						rs.getInt("riesgoInicial"), 
						rs.getInt("riesgoFinal"), 
						rs.getTimestamp("fecha_creacion"),
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
	          ps.setString(3, request.getFirstname());
	          ps.setLong(4, 2l);
	          ps.setLong(5, 121l);
	          ps.setString(6, request.getInvestigacionJson());
	          ps.setLong(7, 3l);
	          return ps;
	        }, keyHolder);

	        return (long) keyHolder.getKey().longValue();
	    }
	
	
}