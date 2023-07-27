package mx.com.rmsh.horusControl.service;

import mx.com.rmsh.horusControl.vo.InvestigacionLAMBDA;
import mx.com.rmsh.horusControl.vo.InvestigacionRequest;

public interface AWSService {
	
	public InvestigacionLAMBDA getAWSKendraResponse(InvestigacionRequest investigacionRequest);
	
	
}
