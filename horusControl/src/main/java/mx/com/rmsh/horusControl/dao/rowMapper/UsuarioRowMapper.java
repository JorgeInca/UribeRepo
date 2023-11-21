package mx.com.rmsh.horusControl.dao.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.com.rmsh.horusControl.enums.RolUsuario;
import mx.com.rmsh.horusControl.vo.UserHorus;

public class UsuarioRowMapper implements RowMapper<UserHorus>{
	
	@Override
    public UserHorus mapRow(ResultSet rs, int rowNum) throws SQLException {

		UserHorus user = new UserHorus();
		user.setId_usuario(rs.getLong("id_usuario"));
		user.setName(rs.getString("nombre"));
		user.setEmail(rs.getString("email"));
		
		user.setEstatus(rs.getInt("estatus"));
		user.setIdEmpresa(rs.getLong("idEmpresa"));
		user.setNombreEmpresa(rs.getString("nombreEmpresa"));
		
		user.setRol(rs.getInt("rol"));
		user.setRolText( RolUsuario.getNameyId( rs.getInt("rol") ) ) ;		
		
		user.setPassword(rs.getString("password"));
		
		
		
        return user;

    }

}
