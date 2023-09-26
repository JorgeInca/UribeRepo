package mx.com.rmsh.horusControl.controller.app;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
import mx.com.rmsh.horusControl.vo.ReporteRequest;
import mx.com.rmsh.horusControl.vo.UserHorus;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

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

		System.out.println("********* [Controller] consultaInvestigaciones : OK " );

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

	@RequestMapping(value = "/generaReporteByID/{idInvestigacion}", method = RequestMethod.GET)
	public ResponseEntity<ByteArrayResource> generateInventoryPurchasePdf(HttpServletResponse response,
			@PathVariable("idInvestigacion") Long idInvestigacion) throws Exception {
		
		System.out.println("********* [Controller] generaReporteByID : ");
		
		InvestigacionRequest reguest = new InvestigacionRequest();
		reguest.setIdInvestigacion(idInvestigacion);
		
		
		InvestigacionLAMBDA lambdaQuery = awsService.getJSONFromBD(reguest);		

		// generate the PDF
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("clienteName", "Coca-Cola");
		parameters.put("idInvestigacion", idInvestigacion.toString());
		parameters.put("nombreCompleto", lambdaQuery.getBody().getParametros_busqueda().get(0) + " " + lambdaQuery.getBody().getParametros_busqueda().get(1));

		try {
			
			
			String reportName =  File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "mx"
					+ File.separator + "com" + File.separator + "rmsh" + File.separator + "horusControl" + File.separator + "reportes"+ File.separator +"reporteInvestigacion";

			// compiles jrxml
			JasperCompileManager.compileReportToFile(reportName + ".jrxml");
			// fills compiled report with parameters and a connection
			JasperPrint print = JasperFillManager.fillReport(reportName + ".jasper", parameters,
					new JREmptyDataSource());
			// exports report to pdf
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(reportName + ".pdf"));
			exporter.exportReport();

			File file = new File(reportName + ".pdf");
			System.out.println("Archivo abierto PDF");

			Path path = Paths.get(file.getAbsolutePath());
			byte[] data = Files.readAllBytes(path);

			MediaType mediaType = MediaType.APPLICATION_JSON_UTF8;
			ByteArrayResource resource = new ByteArrayResource(data);

			System.out.println("fileName: " + file.getName());
			System.out.println("mediaType: " + mediaType);

			return ResponseEntity.ok()
					// Content-Disposition
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
					// Content-Type
					.contentType(mediaType) //
					// Content-Lengh
					.contentLength(data.length) //
					.body(resource);

		} catch (Exception e) {
			throw new RuntimeException("It's not possible to generate the pdf report.", e);
		} finally {

		}

	}
}
