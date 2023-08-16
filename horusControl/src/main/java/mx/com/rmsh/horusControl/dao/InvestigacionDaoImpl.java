package mx.com.rmsh.horusControl.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mx.com.rmsh.horusControl.vo.Investigacion;
import mx.com.rmsh.horusControl.vo.ReporteRequest;

@Repository
public class InvestigacionDaoImpl implements InvestigacionDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings("deprecation")
	@Override
	public List<Investigacion> getReportes(ReporteRequest request) {

		return jdbcTemplate.query("select \r\n"
				+ "A.id_investigacion as id_investigacion,\r\n"
				+ "B.id_usuario as idUsuario,\r\n"
				+ "B.nombre as nombreUsuario,\r\n"
				+ "C.id_empresa as idEmpresa,\r\n"
				+ "C.nombre as nombreEmpresa,\r\n"
				+ "A.apellidos as apellidos,\r\n"
				+ "A.primer_nombre as primerNombre,\r\n"
				+ "A.segundo_nombre as segundoNombre,\r\n"
				+ "A.json_desc as json_desc,\r\n"
				+ "A.nivel_riesgo_inicial as riesgoInicial,\r\n"
				+ "A.nivel_riesgo_final as riesgoFinal,\r\n"
				+ "A.fecha_creacion as fecha_creacion,\r\n"
				+ "A.estatus as estatus\r\n"
				+ "from investigacion A inner join usuario B on  A.id_usuario = B.id_usuario left join empresa C on  B.id_empresa = C.id_empresa where A.estatus = 1 order by A.fecha_creacion desc;",
				
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
	
}