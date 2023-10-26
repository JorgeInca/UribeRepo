package mx.com.rmsh.horusControl.service;

import java.io.File;

import mx.com.rmsh.horusControl.vo.InvestigacionLAMBDA;
import mx.com.rmsh.horusControl.vo.InvestigacionRequest;
import software.amazon.awssdk.services.lambda.LambdaClient;

public interface AWSService {
	
	public InvestigacionLAMBDA getAWSKendraResponse(InvestigacionRequest investigacionRequest);
	
	public InvestigacionLAMBDA getJSONFromBD(InvestigacionRequest investigacionRequest);
	
	public LambdaClient getAWSlamdaClient();

	public String invokeFunction(LambdaClient awsLambda, String functionName, InvestigacionRequest actual);
	
	public String createFolder(String folderName);
	
	public String saveFile(String folderName , File file);
	
}
