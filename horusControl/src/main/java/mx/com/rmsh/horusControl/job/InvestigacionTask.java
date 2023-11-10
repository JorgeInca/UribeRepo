package mx.com.rmsh.horusControl.job;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import mx.com.rmsh.horusControl.service.AWSService;
import mx.com.rmsh.horusControl.service.InvestigacionService;
import mx.com.rmsh.horusControl.vo.InvestigacionLAMBDA;
import mx.com.rmsh.horusControl.vo.InvestigacionRequest;
import mx.com.rmsh.horusControl.vo.RiesgoRequest;
import software.amazon.awssdk.services.lambda.LambdaClient;

@Component
public class InvestigacionTask {
	
	@Autowired
	AWSService servicioAws;

	@Autowired
	InvestigacionService service;

	@Scheduled(cron = "0 42 21 * * ?")
	public void everyFiveSeconds() {

		Integer limit = 1500;
		System.out.println("*********START JOB task: " + new Date());

		String functionName = "arn:aws:lambda:us-east-1:100906894518:function:SearchEnginePost";

		LambdaClient awsLambda = servicioAws.getAWSlamdaClient();

		List<InvestigacionRequest> toProcces = service.getMasivasToAWS(limit);

		System.out.println("*********START JOB data: " + toProcces);

		for (InvestigacionRequest actual : toProcces) {
			try {

				System.out.println("*********START AWS LAMNDA: " + actual);
				// getJson
				String jsonToSave = servicioAws.invokeFunction(awsLambda, functionName, actual);

				jsonToSave = jsonToSave.replaceAll("\n", "\\n");

				System.out.println("*********JSON: " + jsonToSave);

				Gson gson = new Gson();
				InvestigacionLAMBDA lambda = new InvestigacionLAMBDA();
				lambda = gson.fromJson(jsonToSave, InvestigacionLAMBDA.class);
				System.out.println("Actualizando riesgo: " + lambda.getBody().getNivel_riesgo());

				// Update al Nivel de riesgo
				RiesgoRequest riesgoRequest = new RiesgoRequest();
				riesgoRequest.setIdInvestigacion(actual.getIdInvestigacion());
				riesgoRequest.setIdInvestigacion(actual.getIdInvestigacion());
				riesgoRequest.setRiesgo(lambda.getBody().getNivel_riesgo());
				service.setRiesgoById(riesgoRequest);

				service.setJsonById(actual.getIdInvestigacion(), jsonToSave);
				service.finishInvestigacionTask(actual.getIdInvestigacion());

				System.out.println("*********JSON: saved");

				service.getPDFInvestigacion(actual.getIdInvestigacion(), "/" + actual.getTitulo() + "/", false);

				System.out.println("*********PDF:  created");
			} catch (Exception e) {
				
				System.err.println("Error en Folio : ");
				service.errorInvestigacionTask(actual.getIdInvestigacion());
				
			}
		}

		service.updateCampanias();

	}

}
