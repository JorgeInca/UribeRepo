package mx.com.rmsh.horusControl.controller.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mx.com.rmsh.horusControl.enums.EstatusInvestigacion;
import mx.com.rmsh.horusControl.service.AWSService;
import mx.com.rmsh.horusControl.service.InvestigacionService;
import mx.com.rmsh.horusControl.service.SecurityService;
import mx.com.rmsh.horusControl.vo.InvestigacionLAMBDA;
import mx.com.rmsh.horusControl.vo.InvestigacionRequest;
import mx.com.rmsh.horusControl.vo.MasivaRequest;
import mx.com.rmsh.horusControl.vo.ReporteRequest;
import mx.com.rmsh.horusControl.vo.UserHorus;

@Controller
public class InvestigacionController {

	@Autowired
	AWSService awsService;

	@Autowired
	InvestigacionService investigacionService;
	
	@Autowired
	SecurityService securityService;
	

	@RequestMapping(value = "/consumeLambda", method = RequestMethod.POST)
	public @ResponseBody String posted(InvestigacionRequest investigacionRequest) {

		String response = "";
		Gson gson = new Gson();

		System.out.println(investigacionRequest.toString());

		// arraylist

		InvestigacionLAMBDA lambdaQuery = awsService.getAWSKendraResponse(investigacionRequest);

		// Conversion a BD
		investigacionRequest.setInvestigacionJson(lambdaQuery.getJsonBD());
		investigacionRequest.setNivel_riesgo(lambdaQuery.getBody().getNivel_riesgo());
		investigacionRequest.setIdEstatus(EstatusInvestigacion.FINALIZADO.getIdEstatus());

		lambdaQuery.setCreated(investigacionService.guardaInvestigacion(investigacionRequest));
		response = gson.toJson(lambdaQuery);

		System.out.println("********* [Controller] consumeLambda : " + response);

		return response;
	}

	@RequestMapping(value = "/cargaInvestigacionId", method = RequestMethod.POST)
	public @ResponseBody String posted3(InvestigacionRequest investigacionRequest) {

		String response = "";
		Gson gson = new Gson();

		System.out.println(investigacionRequest.toString());

		// arraylist
		InvestigacionLAMBDA lambdaQuery = awsService.getJSONFromBD(investigacionRequest);

		response = gson.toJson(lambdaQuery);

		System.out.println("********* [Controller] consumeLambda : " + response);

		return response;
	}

	@RequestMapping(value = "/guardaInvestigacion", method = RequestMethod.POST) // NOT USED YET
	public @ResponseBody String guardaInvestigacion(InvestigacionRequest investigacionRequest) {

		String response = "";

		final GsonBuilder gsonBuilder = new GsonBuilder();
		final Gson gson = gsonBuilder.create();

		System.out.println(investigacionRequest.toString());

		response = investigacionService.guardaInvestigacion(investigacionRequest) + "";

		System.out.println("********* [Controller] guardaInvestigacion : " + response);

		return response;
	}

	@RequestMapping(value = "/consultaReportes", method = RequestMethod.POST)
	public @ResponseBody String reporte(ReporteRequest reporteRequest) {

		String response = "";
		Gson gson = new Gson();

		System.out.println(reporteRequest.toString());

		// arraylist

		response = gson.toJson(investigacionService.getReportes(reporteRequest));

		System.out.println("********* [Controller] consultaInvestigaciones : " + response);

		return response;
	}	

	@PostMapping("/uploadMasiva")
    @ResponseBody
	public String fileUpload(@RequestParam("file") MultipartFile file, @RequestParam("idUserHorus") Long idUserHorus, @RequestParam("nombreCampaña") String nombreCampaña) {
		
		MasivaRequest masivaRequest = new MasivaRequest();
		masivaRequest.setFile(file);
		masivaRequest.setIdUsuario(idUserHorus);
		masivaRequest.setNombreCampaña(nombreCampaña);

		Gson gson = new Gson();
		List<InvestigacionRequest> listaRetorno = new ArrayList<InvestigacionRequest>();
		String response = "";

//		if (file.isEmpty()) {
//			return response;
//		}

		try {
			listaRetorno = investigacionService.guardaInvestigacionMasiva(masivaRequest);
			response = gson.toJson(listaRetorno);
			
			System.out.println("********* [Controller] upload : " + response);
			return response;
		}
		
		

		catch (Exception e) {
			e.printStackTrace();
			return response;
		}

	}
	
	@RequestMapping(value = "/getUserdataByName", method = RequestMethod.POST)
	public @ResponseBody String getUserdataByName(UserHorus userHorus) {

		String response = "";
		Gson gson = new Gson();

		System.out.println(userHorus.toString());

		// arraylist
		response = gson.toJson(securityService.getUserdataByName(userHorus.getName()));

		System.out.println("********* [Controller] getUserdataByName : " + response);

		return response;
	}
}
