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
public class CatalagosController {
	
	@Autowired
	AWSService awsService;
	
	@Autowired
	InvestigacionService service;
	
	@Autowired
	InvestigacionService investigacionService;
	
	
	//Guarda el nuevo Usuario
		@RequestMapping(value = "/guardaUsuario", method = RequestMethod.POST) //NOT USED YET
		public @ResponseBody String guardaUsuario(UserHorus user) {

			String response = "";

			final GsonBuilder gsonBuilder = new GsonBuilder();
			final Gson gson = gsonBuilder.create();

			System.out.println(user.toString());

			response = service.guardaUsuario(user) + "";
			
			System.out.println("********* [Controller] guardaUsuario : " + response);

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

}
