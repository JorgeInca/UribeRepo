package mx.com.rmsh.horusControl.controller.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import mx.com.rmsh.horusControl.service.AWSService;
import mx.com.rmsh.horusControl.vo.InvestigacionRequest;

@Controller
public class InvestigacionController {

	@Autowired
	AWSService service;

	@RequestMapping(value = "/consumeLambda", method = RequestMethod.POST)
	public @ResponseBody String posted(InvestigacionRequest investigacionRequest) {

		String response = "";
		Gson gson = new Gson();

		System.out.println(investigacionRequest.toString());
		
		response = gson.toJson(service.getAWSKendraResponse(investigacionRequest));	

		System.out.println("********* [Controller] response : " + response);

		return response;
	}
}
