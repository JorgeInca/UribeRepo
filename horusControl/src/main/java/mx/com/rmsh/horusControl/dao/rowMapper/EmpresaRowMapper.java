package mx.com.rmsh.horusControl.dao.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import mx.com.rmsh.horusControl.vo.*;


public class EmpresaRowMapper implements RowMapper<Empresas> {
	
	@Override
	public Empresas mapRow(ResultSet rs, int rowNum) throws SQLException {

		Empresas empresa = new Empresas();
		empresa.setId_empresa(rs.getLong("id_empresa"));
		empresa.setNombre(rs.getString("nombre"));
		empresa.setEmail(rs.getString("email"));
		empresa.setPhone(rs.getLong("phone"));
		empresa.setEstatus(rs.getInt("estatus"));

        return empresa;

    }
	
	
	
	
	
	
	

}
