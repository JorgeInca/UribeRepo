package mx.com.rmsh.horusControl.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import mx.com.rmsh.horusControl.dao.InvestigacionDao;
import mx.com.rmsh.horusControl.enums.Pais;
import mx.com.rmsh.horusControl.vo.Investigacion;
import mx.com.rmsh.horusControl.vo.InvestigacionLAMBDA;
import mx.com.rmsh.horusControl.vo.InvestigacionRequest;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;
import software.amazon.awssdk.services.lambda.model.LambdaException;

@Service
public class AWSServiceImpl implements AWSService {
	
	@Autowired
	InvestigacionDao daoInvestigacion;

	@Override
	public InvestigacionLAMBDA getAWSKendraResponse(InvestigacionRequest investigacionRequest) {

		String nombreLambda = "arn:aws:lambda:us-east-1:100906894518:function:SearchEnginePost";

		String functionName = nombreLambda;
		Region region = Region.US_EAST_1;
		LambdaClient awsLambda = LambdaClient.builder().region(region)
				.credentialsProvider(StaticCredentialsProvider.create(
						AwsBasicCredentials.create("AKIARO7UFAS3A3ISPD6R", "2CFt//lW5g8jh7qkI5OUP8CSOpUNwHsdwk99QXo5")))
				.build();
		
		Investigacion respuestaInvestigacion = new Investigacion();
		respuestaInvestigacion.setJson(invokeFunction(awsLambda, functionName,investigacionRequest));

		System.out.println( "JASON" + respuestaInvestigacion.getJson()  );
		
		awsLambda.close();
		
		Gson gson = new Gson();
		InvestigacionLAMBDA lambda = new InvestigacionLAMBDA();
		
		
		respuestaInvestigacion.setJson( respuestaInvestigacion.getJson().replaceAll("\n", "\\n") );			
		
		lambda = gson.fromJson(respuestaInvestigacion.getJson() , InvestigacionLAMBDA.class); 
		lambda.setJsonBD(respuestaInvestigacion.getJson()); //Para guardar en BD
		 
		
		// TODO Auto-generated method stub
		return lambda;
	}

	// snippet-start:[lambda.java2.invoke.main]
	public static String invokeFunction(LambdaClient awsLambda, String functionName,InvestigacionRequest investigacionRequest) {

		InvokeResponse res = null;
		try {
			// Need a SdkBytes instance for the payload.
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("firstname", investigacionRequest.getFirstname());
			jsonObj.put("lastname", investigacionRequest.getLastname());
			jsonObj.put("rfc", investigacionRequest.getRfc());
			jsonObj.put("pais", Pais.getNameyId(investigacionRequest.getPais()));
			
			String json = jsonObj.toString();
			SdkBytes payload = SdkBytes.fromUtf8String(json);

			// Setup an InvokeRequest.
			InvokeRequest request = InvokeRequest.builder().functionName(functionName).payload(payload).build();

			res = awsLambda.invoke(request);
			String value = res.payload().asUtf8String();
			//System.out.println("Se logro" + value);
			
			//Para campos vacios
			String texto = "\\\"texto\\\": \\\"\\\",";
			
			value = value.replaceAll(texto, "\"texto\": { \"hola\" : \"holi\"  } ,");

			return value;

		} catch (LambdaException e) {
			System.err.println(e.getMessage());
			System.exit(1);
			return "X";

		}
	}

	@Override
	public InvestigacionLAMBDA getJSONFromBD(InvestigacionRequest investigacionRequest) {
		// TODO Auto-generated method stub
		InvestigacionLAMBDA lambda;
		
		Gson gson = new Gson();
		
		lambda = gson.fromJson(daoInvestigacion.getInvestigacionById(investigacionRequest.getIdInvestigacion()) , InvestigacionLAMBDA.class); 
		
		return lambda;
	}

}
