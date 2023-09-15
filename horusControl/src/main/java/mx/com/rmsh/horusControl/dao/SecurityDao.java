package mx.com.rmsh.horusControl.dao;

import java.util.List;
import mx.com.rmsh.horusControl.vo.ReporteRequest;
import mx.com.rmsh.horusControl.vo.UserHorus;


public interface SecurityDao {

	public List<UserHorus> getUsers(UserHorus request);

	public List<UserHorus> getUser();
	
	public UserHorus getUserdataByName(String userName);

	public Long guardaUsuario(UserHorus user);
	
	public String getUsuarioById(Long id_usuario);

}
