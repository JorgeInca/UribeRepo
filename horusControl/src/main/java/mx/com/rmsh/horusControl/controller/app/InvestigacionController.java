package mx.com.rmsh.horusControl.controller.app;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
import mx.com.rmsh.horusControl.vo.OrigenesBorradoRequest;
import mx.com.rmsh.horusControl.vo.ReporteRequest;
import mx.com.rmsh.horusControl.vo.RiesgoRequest;
import mx.com.rmsh.horusControl.vo.UserHorus;
import mx.com.rmsh.horusControl.vo.ca.CampaniaCA;
import mx.com.rmsh.horusControl.vo.ca.ClienteCA;

@Controller
public class InvestigacionController {

	@Autowired
	AWSService awsService;

	@Autowired
	InvestigacionService investigacionService;

	@Autowired
	SecurityService securityService;

	@Autowired
	ServletContext servletContext;

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

		System.out.println("********* [Controller] cargaInvestigacionId : " + response);

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

		System.out.println("********* [Controller] consultaInvestigaciones : OK ");

		return response;
	}
	
	@RequestMapping(value = "/consultaReportesFiltros", method = RequestMethod.POST)
	public @ResponseBody String reporteFiltro(ReporteRequest reporteRequest) {

		String response = "";
		Gson gson = new Gson();

		System.out.println(reporteRequest.toString());

		// arraylist

		//response = gson.toJson(investigacionService.getReportes(reporteRequest));

		System.out.println("********* [Controller] consultaReportesFiltros : OK ");

		return response;
	}

	@PostMapping("/uploadMasiva")
	@ResponseBody
	public String fileUpload(@RequestParam("file") MultipartFile file, @RequestParam("idUserHorus") Long idUserHorus,
			@RequestParam("nombreCampaña") String nombreCampaña) {

		MasivaRequest masivaRequest = new MasivaRequest();
		masivaRequest.setFile(file);
		masivaRequest.setIdUsuario(idUserHorus);
		masivaRequest.setNombreCampaña(nombreCampaña);

		String response = "";

//		if (file.isEmpty()) {
//			return response;
//		}

		try {
			response = investigacionService.guardaInvestigacionMasiva(masivaRequest) + "";

			System.out.println("********* [Controller] uploadID : " + response);
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

	@RequestMapping(value = "/generaReporteByID/{idInvestigacion}", method = RequestMethod.GET)
	public ResponseEntity<ByteArrayResource> generateInventoryPurchasePdf(HttpServletResponse response,
			@PathVariable("idInvestigacion") Long idInvestigacion) throws Exception {

		System.out.println("********* [Controller] generaReporteByID : ");
			
		
		return investigacionService.getPDFInvestigacion(idInvestigacion, "/General/", false);

	}

	@RequestMapping(value = "/consultaMasivas", method = RequestMethod.POST)
	public @ResponseBody String reporteMasivas(MasivaRequest masivarequest) {

		String response = "";
		Gson gson = new Gson();

		System.out.println(masivarequest.toString());

		// arraylist

		response = gson.toJson(investigacionService.getMasivas(masivarequest));

		System.out.println("********* [Controller] consultaMasivas : OK ");

		return response;
	}

	@RequestMapping(value = "/actualizaRiesgoById", method = RequestMethod.POST)
	public @ResponseBody String actualizaRiesgoById(RiesgoRequest riegoRequest) {

		String response = "1";

		System.out.println(riegoRequest.toString());

		// arraylist

		investigacionService.updateRiesgoFinalById(riegoRequest);

		System.out.println("********* [Controller] actualizaRiesgoById : OK ");

		return response;
	}

	@RequestMapping(value = "/actualizaOrigenMention", method = RequestMethod.POST)
	public @ResponseBody String actualizaOrigenMention(OrigenesBorradoRequest borraRequest) {

		String response = "1";

		System.out.println(borraRequest.toString());

		// arraylist
		if (borraRequest.isEsMention()) {
			investigacionService.updateEliminadosMention(borraRequest);
		} else {
			investigacionService.updateEliminadosOrigen(borraRequest);
		}

		System.out.println("********* [Controller] actualizaRiesgoById : OK ");

		return response;
	}

	@RequestMapping(value = "/pruebaAWS", method = RequestMethod.POST)
	public @ResponseBody String pruebaAWS(RiesgoRequest riegoRequest) {

		String response = "1";		
		System.out.println("Prueba S3");
		
		boolean lanux = false;
		String reportFolderPath = (lanux ? File.separator : "") + "src" + File.separator + "main" + File.separator
				+ "java" + File.separator + "mx" + File.separator + "com" + File.separator + "rmsh" + File.separator
				+ "horusControl" + File.separator + "reportes" + File.separator;
		
		String finalReportName = reportFolderPath + "Investigacion74.pdf";
		
		File file = new File(finalReportName);	
		
		
		riegoRequest.setNombreCliente(investigacionService.getNombreClientePorUsuario(riegoRequest.getIdUsuario()));

		String newFolder = riegoRequest.getNombreCliente() + "/General/";
		// arraylist
		String urlFolder = awsService.createFolder(newFolder);
		String urlFile =   awsService.saveFile(newFolder,file);
		
				
		System.out.println("URL " + urlFolder + " is ready.");
		System.out.println("URL " + urlFile + " is ready.");
		
		
		

		return response;
	}
	
	@RequestMapping(value = "/eliminaRegistroById", method = RequestMethod.POST)
	public @ResponseBody String eliminaRegistroById(RiesgoRequest riegoRequest) {

		String response = "1";

		System.out.println(riegoRequest.toString());

		// arraylist

		investigacionService.eliminaRegistrioById(riegoRequest);

		System.out.println("********* [Controller] EliminaReisgo By Id : OK ");

		return response;
	}
	
	@RequestMapping(value = "/consultaCACampania", method = RequestMethod.POST)
	public @ResponseBody String consultaCACampania(ReporteRequest reporteRequest) {

		String response = "";
		Gson gson = new Gson();

		System.out.println(reporteRequest.toString());

		// arraylist
		CampaniaCA nuevo = new CampaniaCA( 2L , "Metalsa LA");
		CampaniaCA nuevo1 = new CampaniaCA( 1L , "Citro LA");
		
		List <CampaniaCA> pollos = new ArrayList<CampaniaCA>();
		pollos.add(nuevo);
		pollos.add(nuevo1);

		response = gson.toJson(pollos);

		System.out.println("********* [Controller] consultaCACampania : OK ");

		return response;
	}
	
	@RequestMapping(value = "/consultaCACliente", method = RequestMethod.POST)
	public @ResponseBody String consultaCACliente(ReporteRequest reporteRequest) {

		String response = "";
		Gson gson = new Gson();

		System.out.println(reporteRequest.toString());

		// arraylist
		ClienteCA nuevo = new ClienteCA( 2L , "CitroFrut");
		ClienteCA nuevo1 = new ClienteCA( 1L , "Metalza");
		
		List <ClienteCA> pollos = new ArrayList<ClienteCA>();
		pollos.add(nuevo);
		pollos.add(nuevo1);

		response = gson.toJson(pollos);

		System.out.println("********* [Controller] consultaCACliente : OK ");

		return response;
	}
}
