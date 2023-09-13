package mx.com.rmsh.horusControl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.rmsh.horusControl.dao.SecurityDao;
import mx.com.rmsh.horusControl.vo.UserHorus;

@Service
public class SecurityServiceImpl implements SecurityService {


	@Autowired
	SecurityDao daoUser;

	@Override
	public UserHorus getUserdataByName(String userName) {
		// TODO Auto-generated method stub
		return daoUser.getUserdataByName(userName);
	}

	
}