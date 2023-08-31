package mx.com.rmsh.horusControl.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mx.com.rmsh.horusControl.vo.UserHorus;

@Repository
public class SecurityDaoImpl implements SecurityDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	String QUERY_GET_USERS=
			 " select * from usuario where Estatus = 1 order by fecha_creacion desc; ";


	@SuppressWarnings("deprecation")
	@Override
	public List<UserHorus> getUsers() {

		return jdbcTemplate.query(QUERY_GET_USERS,
				
				(rs, rowNum) -> new UserHorus(
						rs.getLong("id_usuario"), 
						rs.getString("nombre"),
						rs.getString("email"), 
						rs.getInt("estatus"),
						rs.getLong("estatus"),
						rs.getInt("ROL"), 
						rs.getString("password")
						));
	}


	
	
}