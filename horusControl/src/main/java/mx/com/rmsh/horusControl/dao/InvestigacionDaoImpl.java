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

		return jdbcTemplate.query("select * from investigacion order by fecha_creacion;",
				
				(rs, rowNum) -> new Investigacion(
						rs.getLong("id_investigacion"), 
						rs.getString("nombre"),
						rs.getLong("id_usuario"), 
						rs.getLong("id_empresa"), 
						rs.getString("json_desc"), 
						rs.getInt("nivel_riesgo_inicial"), 
						rs.getInt("nivel_riesgo_final"), 
						rs.getTimestamp("fecha_creacion"),
						rs.getInt("estatus")
						));
	}
	
}