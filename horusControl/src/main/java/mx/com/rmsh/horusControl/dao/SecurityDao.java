package mx.com.rmsh.horusControl.dao;

import java.util.List;

import mx.com.rmsh.horusControl.vo.Empresas;
import mx.com.rmsh.horusControl.vo.ReporteRequest;
import mx.com.rmsh.horusControl.vo.UserHorus;


public interface SecurityDao {

	public List<UserHorus> getUsers(UserHorus request);

	public List<UserHorus> getUser();
	
	public UserHorus getUserdataByName(String userName);

	public Long guardaUsuario(UserHorus user);
	
	public UserHorus getUsuarioById(Long id_usuario);
	
	public long eliminiarUsuario(Long id_usuario);
	
	public UserHorus editUser(Long id_usuario);
	
	public long updateUser(UserHorus user);
	
	
	//Informacion de las Empresas
	public List<Empresas> getEmpresa(Empresas request);
	
	public Empresas getEmpresadataById(Long id_empresa);
	
	
	

}
