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
import mx.com.rmsh.horusControl.vo.UserHorus;

@Repository
public class SecurityDaoImpl implements SecurityDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/* String QUERY_GET_USERS=
			 " select * from usuario where Estatus = 1 order by fecha_creacion desc; "; */
	
	
	String QUERY_GET_USERS = "SELECT "
			+ " A.id_usuario as id_usuario,"
			+ " A.nombre as name,"
			+ " A.email as email,"
			+ " A.fecha_creacion as fecha_creacion,"
			+ " A.estatus as estatus, "
			+ " B.id_empresa as id_empresa,"
			+ " A.rol as rol, "
			+ " A.password as password "
			+ " FROM usuario A inner join empresa B on A.id_empresa = B.id_empresa "
			+ "where A.estatus = 1 order by A.fecha_creacion desc;";
			
	


	@SuppressWarnings("deprecation")
	@Override
	public List<UserHorus> getUsers(UserHorus request) {

		return jdbcTemplate.query(QUERY_GET_USERS,
				
				(rs, rowNum) -> new UserHorus(
						rs.getLong("id_usuario"), 
						rs.getString("name"),
						rs.getString("email"), 
						rs.getInt("estatus"),
						rs.getLong("id_empresa"),
						rs.getInt("rol"), 
						rs.getString("password")
						));
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	public List<UserHorus> getUser() {

		return jdbcTemplate.query(QUERY_GET_USERS,
				
				(rs, rowNum) -> new UserHorus(
						rs.getLong("id_usuario"), 
						rs.getString("name"),
						rs.getString("email"), 
						rs.getInt("estatus"),
						rs.getLong("id_empresa"),
						rs.getInt("rol"), 
						rs.getString("password")
						));
	}





	
	
}