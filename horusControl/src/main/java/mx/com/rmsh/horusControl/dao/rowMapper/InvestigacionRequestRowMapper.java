package mx.com.rmsh.horusControl.dao.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.com.rmsh.horusControl.vo.InvestigacionRequest;

//NOT USED
public class InvestigacionRequestRowMapper  implements RowMapper<InvestigacionRequest>{
	
	@Override
    public InvestigacionRequest mapRow(ResultSet rs, int rowNum) throws SQLException {

		InvestigacionRequest request = new InvestigacionRequest();
		
		request.setIdInvestigacion(rs.getLong("id_investigacion"));
		request.setFirstname(rs.getString("apellidos"));
		request.setLastname(rs.getString("primer_nombre"));
		request.setRfc("");
		request.setPais( 14 );
		
        return request;

    }

}
