package mx.com.rmsh.horusControl.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import mx.com.rmsh.horusControl.dao.rowMapper.UsuarioRowMapper;
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
			+ " B.nombre as nombreEmpresa,"
			+ " A.rol as rol, "
			+ " A.password as password "
			+ " FROM usuario A left join empresa B on A.id_empresa = B.id_empresa "
			+ "where A.estatus = 1 order by A.fecha_creacion desc;";
	
	
	//Insertar nuevo usuario
	String QUERY_CREATE_USUARIO=
			"INSERT INTO `horusDatabase`.`usuario`"
			+ "("
			+ "`nombre`,"
			+ "`email`,"
			+ "`estatus`,"
			+ "`id_empresa`,"
			+ "`rol`,"
			+ "`password`"
			+ ")"
			+ "VALUES"
			+ "(?,?,?,?,?,?)";
	
	//Insertar nuevo usuario
	String QUERY_GET_USERBYNAME=
			" select * from usuario where nombre = ? ";
			
	


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
						rs.getString("nombreEmpresa"),
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
						rs.getString("nombreEmpresa"),
						rs.getInt("rol"), 
						rs.getString("password")
						));
	}
	
	@Override
	public Long guardaUsuario(UserHorus user) {
		// TODO Auto-generated method stub
		KeyHolder keyHolder = new GeneratedKeyHolder();

	    jdbcTemplate.update(connection -> {
	        PreparedStatement ps = connection
	          .prepareStatement(QUERY_CREATE_USUARIO,Statement.RETURN_GENERATED_KEYS);
	          ps.setString(1, user.getName());
	          ps.setString(2, user.getEmail());
	          ps.setInt(3, user.getEstatus());
	          ps.setLong(4, user.getIdEmpresa());
	          ps.setInt(5, user.getRol());
	          ps.setString(6, user.getPassword());
	          return ps;
	        }, keyHolder);

	        return (long) keyHolder.getKey().longValue();
	    }

		@Override
		@SuppressWarnings("deprecation")
		public UserHorus getUserdataByName(String userName) {
			// TODO Auto-generated method stub
			return jdbcTemplate.queryForObject(QUERY_GET_USERBYNAME, 
					new Object[] { userName }, new UsuarioRowMapper());

		}


		@Override
		public String getUsuarioById(Long id_usuario) {
			// TODO Auto-generated method stub
			return null;
		}


	
	
}