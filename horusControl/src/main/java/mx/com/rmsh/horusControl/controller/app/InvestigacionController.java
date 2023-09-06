package mx.com.rmsh.horusControl.controller.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mx.com.rmsh.horusControl.service.AWSService;
import mx.com.rmsh.horusControl.service.InvestigacionService;
import mx.com.rmsh.horusControl.vo.InvestigacionLAMBDA;
import mx.com.rmsh.horusControl.vo.InvestigacionRequest;
import mx.com.rmsh.horusControl.vo.ReporteRequest;
import mx.com.rmsh.horusControl.vo.UserHorus;

@Controller
public class InvestigacionController {

	@Autowired
	AWSService awsService;
	
	@Autowired
	InvestigacionService service;
	
	@Autowired
	InvestigacionService investigacionService;
	

	@RequestMapping(value = "/consumeLambda", method = RequestMethod.POST)
	public @ResponseBody String posted(InvestigacionRequest investigacionRequest) {

		String response = "";
		Gson gson = new Gson();

		System.out.println(investigacionRequest.toString());

		// arraylist
		
		InvestigacionLAMBDA lambdaQuery = awsService.getAWSKendraResponse(investigacionRequest);	
		
		//Conversion a BD
		investigacionRequest.setInvestigacionJson(lambdaQuery.getJsonBD());
		investigacionRequest.setNivel_riesgo(lambdaQuery.getBody().getNivel_riesgo());
					
		lambdaQuery.setCreated(service.guardaInvestigacion(investigacionRequest));
		response = gson.toJson(lambdaQuery);	

		System.out.println("********* [Controller] consumeLambda : " + response);

		return response;
	}
	
	@RequestMapping(value = "/guardaInvestigacion", method = RequestMethod.POST) //NOT USED YET
	public @ResponseBody String guardaInvestigacion(InvestigacionRequest investigacionRequest) {

		String response = "";

		final GsonBuilder gsonBuilder = new GsonBuilder();
		final Gson gson = gsonBuilder.create();

		System.out.println(investigacionRequest.toString());

		response = service.guardaInvestigacion(investigacionRequest) + "";
		
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
	
	
	@RequestMapping(value = "/consultaUsuarios", method = RequestMethod.POST)
	public @ResponseBody String usuario(UserHorus userHorus) {

		String response = "";
		Gson gson = new Gson();

		System.out.println(userHorus.toString());

		// arraylist

		response = gson.toJson(investigacionService.getUser());

		System.out.println("********* [Controller] consultaUsuarios : " + response);

		return response;
	}
	
	
	
	

	@PostMapping("/upload")
	public ModelAndView fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

		String UPLOAD_FOLDER = "C://test//";

		if (file.isEmpty()) {
			return new ModelAndView("status", "message", "Please select a file and try again");
		}

		try {

			XSSFWorkbook excelPeticion;

			String lowerCaseFileName = file.getOriginalFilename().toLowerCase();
			if (lowerCaseFileName.endsWith(".xlsx")) {

				excelPeticion = new XSSFWorkbook(file.getInputStream());

				XSSFSheet sheet = excelPeticion.getSheetAt(0);

				Iterator<Row> rowIterator = sheet.iterator();

				while (rowIterator.hasNext()) {

					Row row = rowIterator.next();

					// For each row, iterate through all the
					// columns
					Iterator<Cell> cellIterator = row.cellIterator();

					while (cellIterator.hasNext()) {

						Cell cell = (Cell) cellIterator.next();

						// Checking the cell type and format
						// accordingly
						switch (cell.getCellType()) {

						// Case 1
						case NUMERIC:
							System.out.println(cell.getNumericCellValue());
							break;

						// Case 2
						case STRING:
							System.out.println(cell.getStringCellValue());
							break;
						}
					}
				}

			} else {
				System.out.println("notFormatted");
			}

			// read and write the file to the selected location-
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ModelAndView("status", "message", "File Uploaded sucessfully");
	}
}
