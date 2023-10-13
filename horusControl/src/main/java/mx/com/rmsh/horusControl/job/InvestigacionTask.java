package mx.com.rmsh.horusControl.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import mx.com.rmsh.horusControl.dao.InvestigacionDao;
import mx.com.rmsh.horusControl.service.AWSService;
import mx.com.rmsh.horusControl.vo.InvestigacionRequest;
import software.amazon.awssdk.services.lambda.LambdaClient;

@Component
public class InvestigacionTask {
	
	@Autowired
	AWSService servicioAws;
	
	@Autowired
	InvestigacionDao dao;

    @Scheduled(cron = "0 55 1 * * ?")
    public void everyFiveSeconds() {
    	
    	Integer limit = 10;
        System.out.println("*********START JOB task: " + new Date());
        
		String functionName = "arn:aws:lambda:us-east-1:100906894518:function:SearchEnginePost";
		
		LambdaClient awsLambda = servicioAws.getAWSlamdaClient();
           
        List<InvestigacionRequest> toProcces =  dao.getMasivasToAWS(limit);
        
        System.out.println("*********START JOB data: " + toProcces);
        
        for (InvestigacionRequest actual: toProcces) {
        	
        	System.out.println("*********START AWS LAMNDA: " + actual );
        	//getJson
        	String jsonToSave = servicioAws.invokeFunction(awsLambda, functionName,actual);
        	System.out.println("*********JSON: " + jsonToSave );
        	
        	dao.setJsonById(actual.getIdInvestigacion(), jsonToSave);
        	dao.finishInvestigacionTask(actual.getIdInvestigacion());
        	
        	
        	System.out.println("*********JSON: saved" );
		}
        
        dao.updateCampanias();       
        
        
    }

}
