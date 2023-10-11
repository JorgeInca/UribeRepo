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
import mx.com.rmsh.horusControl.service.CatalagosService;
import mx.com.rmsh.horusControl.service.CatalagosServiceImp;
import mx.com.rmsh.horusControl.service.InvestigacionService;
import mx.com.rmsh.horusControl.vo.Empresas;
import mx.com.rmsh.horusControl.vo.InvestigacionLAMBDA;
import mx.com.rmsh.horusControl.vo.InvestigacionRequest;
import mx.com.rmsh.horusControl.vo.ReporteRequest;
import mx.com.rmsh.horusControl.vo.UserHorus;
import mx.com.rmsh.horusControl.service.*;


@Controller
public class CatalagosController {
	
	@Autowired
	AWSService awsService;
	
	@Autowired
	InvestigacionService service;
	
	@Autowired
	InvestigacionService investigacionService;
	
	@Autowired
	CatalagosService catalagosService;
	
	
	//Guarda el nuevo Usuario
		@RequestMapping(value = "/guardaUsuario", method = RequestMethod.POST) //NOT USED YET
		public @ResponseBody String guardaUsuario(UserHorus user) {

			String response = "";

			// final GsonBuilder gsonBuilder = new GsonBuilder();
			// final Gson gson = gsonBuilder.create();

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
		
		@RequestMapping(value = "/cargaUsuarioId", method = RequestMethod.POST)
		public @ResponseBody String getUserdataById(UserHorus userHorus) {

			String response = "";
			Gson gson = new Gson();

			System.out.println(userHorus.toString());

			// arraylist
			response = gson.toJson(investigacionService.getUsuarioById(userHorus.getId_usuario()));

			System.out.println("********* [Controller] cargaUsuarioId : " + response);

			return response;
		}
		
		@RequestMapping(value = "/eliminarUsuarioId", method = RequestMethod.POST)
		public @ResponseBody String eliminiarUsuario(UserHorus userHorus) {

			String response = "";
			Gson gson = new Gson();

			System.out.println(userHorus.toString());

			
		    response = gson.toJson(investigacionService.eliminiarUsuario(userHorus.getId_usuario()));

			System.out.println("********* [Controller] cargaUsuarioId : " + response);

			return response;
		}
		
		
		//Muestra la informacion del usuario a editar
				@RequestMapping(value = "/editarUsuarioId", method = RequestMethod.POST) //NOT USED YET
				public @ResponseBody String editUser(UserHorus user) {

					
					String response = "";
					Gson gson = new Gson();

					System.out.println(user.toString());

					response = gson.toJson(investigacionService.editUser(user.getId_usuario()));
					
					System.out.println("********* [Controller] guardaUsuario : " + response);

					return response;
				}
	//Editar la inforacion
				@RequestMapping(value = "/actualizaUsuario", method = RequestMethod.POST) //NOT USED YET
				public @ResponseBody String updateUser(UserHorus user) {

					
					String response = "";
					Gson gson = new Gson();

					System.out.println(user.toString());

					response = gson.toJson(investigacionService.updateUser(user));
					
					System.out.println("********* [Controller] guardaUsuario : " + response);

					return response;
				}
				
				
				//Informacion de las empresas 
				@RequestMapping(value = "/consultaEmpresas", method = RequestMethod.POST)
				public @ResponseBody String empresa(Empresas empresas) {

					String response = "";
					Gson gson = new Gson();

					System.out.println(empresas.toString());

			    //	response = gson.toJson(investigacionService.getEmpresa(empresas));
					
					response = gson.toJson(catalagosService.getEmpresa(empresas));

					System.out.println("********* [Controller] consultaEmpresas : " + response);

					return response;
				}
	
		

}
