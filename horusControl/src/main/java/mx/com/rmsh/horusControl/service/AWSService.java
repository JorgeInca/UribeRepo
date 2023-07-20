package mx.com.rmsh.horusControl.service;

import mx.com.rmsh.horusControl.vo.Investigacion;
import mx.com.rmsh.horusControl.vo.InvestigacionRequest;

public interface AWSService {
	
	public Investigacion getAWSKendraResponse(InvestigacionRequest investigacionRequest);
	
	
}
