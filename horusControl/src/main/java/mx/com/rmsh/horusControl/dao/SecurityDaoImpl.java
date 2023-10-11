package mx.com.rmsh.horusControl.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import mx.com.rmsh.horusControl.dao.rowMapper.*;
import mx.com.rmsh.horusControl.vo.*;


@Repository
public class SecurityDaoImpl implements SecurityDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
//---------------- Empresas --------------------------------------------------	
	 String QUERY_GET_Empresa = "SELECT "
			 + " id_empresa as id_empresa,"
			 + " nombre as nombre,"
			 + " email as email,"
			 + " phone as phone,"
			 + " fecha_creacion as fecha_creacion,"
			 + " estatus as estatus"
			 + " FROM empresa "; 
	
     //Busca empresa por id
		String QUERY_GET_EMPRESA_BYID =
				"SELECT "
						 + " id_empresa as id_empresa,"
						 + " nombre as nombre,"
						 + " email as email,"
						 + " phone as phone,"
						 + " fecha_creacion as fecha_creacion,"
						 + " estatus as estatus"
						 + " FROM empresa "
				    	 + " WHERE id_empresa = ? "; 
		
//---------------------- Usuarios -------------------------------------------------		
	
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
			+ " Where A.Estatus = 1 order by A.fecha_creacion desc;";
	
	
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
	
	//Obtiene el usuario de la sesion
	String QUERY_GET_USERBYNAME =
			"SELECT "
					+ " A.id_usuario as id_usuario,"
					+ " A.nombre as nombre,"
					+ " A.email as email,"
					+ " A.fecha_creacion as fecha_creacion,"
					+ " A.estatus as estatus, "
					+ " A.id_empresa as idEmpresa,"
					+ " B.nombre as nombreEmpresa,"
					+ " A.rol as rol, "
					+ " A.password as password "
					+ " FROM usuario A left join empresa B on A.id_empresa = B.id_empresa "
					+ " WHERE A.nombre = ? ";
	
		//Buscar usuario por Id
			String QUERY_GET_USUARIO_BYID =
					"SELECT "
							+ " A.id_usuario as id_usuario,"
							+ " A.nombre as nombre,"
							+ " A.email as email,"
							+ " A.fecha_creacion as fecha_creacion,"
							+ " A.estatus as estatus, "
							+ " A.id_empresa as idEmpresa,"
							+ " B.nombre as nombreEmpresa,"
							+ " A.rol as rol, "
							+ " A.password as password "
							+ " FROM usuario A left join empresa B on A.id_empresa = B.id_empresa "
							+ " WHERE A.id_usuario = ? ";  
			
			//Eliminar usuario por Id
			String QUERY_DELETE_USUARIO_BYID =
					"Update usuario set estatus = 2 where id_usuario = ?"; 
			
			
			//Muestra la informacion del usuario a Editar
			String QUERY_GET_USUARIO_BYID2 =
					"SELECT "
							+ " A.id_usuario as id_usuario,"
							+ " A.nombre as nombre,"
							+ " A.email as email,"
							+ " A.fecha_creacion as fecha_creacion,"
							+ " A.estatus as estatus, "
							+ " A.id_empresa as idEmpresa,"
							+ " B.nombre as nombreEmpresa,"
							+ " A.rol as rol, "
							+ " A.password as password "
							+ " FROM usuario A left join empresa B on A.id_empresa = B.id_empresa "
							+ " WHERE A.id_usuario = ? ";  
			
	  // Edita la informacion
		String QUERY_UPDATE_USUARIO =
		"Update usuario set nombre= ?,email =?,estatus =?,id_empresa =?,rol=?,password=? where id_usuario = ?"; 
			
	


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
		public UserHorus getUsuarioById(Long id_usuario) {
			// TODO Auto-generated method stub
			return jdbcTemplate.queryForObject(QUERY_GET_USUARIO_BYID, 
					new Object[] { id_usuario }, new UsuarioRowMapper());
		}


		@Override
		public long eliminiarUsuario(Long id_usuario) {
			 return jdbcTemplate.update(QUERY_DELETE_USUARIO_BYID, 
					new Object[] { id_usuario });
		}


		@Override
		public UserHorus editUser(Long id_usuario) {
			return jdbcTemplate.queryForObject(QUERY_GET_USUARIO_BYID2, 
					new Object[] { id_usuario }, new UsuarioRowMapper());
		}

	
		@Override
		public long updateUser(UserHorus user) {
		
			 return jdbcTemplate.update(connection -> {
			        PreparedStatement ps = connection
			          .prepareStatement(QUERY_UPDATE_USUARIO);
			          ps.setString(1, user.getName());
			          ps.setString(2, user.getEmail());
			          ps.setInt(3, user.getEstatus());
			          ps.setLong(4, user.getIdEmpresa());
			          ps.setInt(5, user.getRol());
			          ps.setString(6, user.getPassword());
			          ps.setLong(7, user.getId_usuario());
			          return ps;
			        });
		}


//------------------------ Empresas -------------------------------------------------
		@SuppressWarnings("deprecation")
		@Override
		public List<Empresas> getEmpresa(Empresas request) {
			
			 return jdbcTemplate.query(QUERY_GET_Empresa,
					
					(rs, rowNum) -> new Empresas(
							rs.getLong("id_empresa"),
							rs.getString("nombre"),
							rs.getString("email"),
							rs.getLong("phone"),
							rs.getInt("estatus")
							));
		}
		
		@Override
		public Empresas getEmpresadataById(Long id_empresa) {
			return jdbcTemplate.queryForObject(QUERY_GET_EMPRESA_BYID, 
					new Object[] { id_empresa }, new EmpresaRowMapper());
		}



	
		
		

	
	
		
}