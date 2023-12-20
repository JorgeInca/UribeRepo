package mx.com.rmsh.horusControl.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		
		//registry.addViewController("/").setViewName("home");
		registry.addViewController("/menu").setViewName("menu");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/reportes").setViewName("reportes");
		registry.addViewController("/reportesOld").setViewName("reportesOld");
		registry.addViewController("/investigacion").setViewName("investigacion");
		registry.addViewController("/investigacionOld").setViewName("investigacionOld");
		registry.addViewController("/usuarios").setViewName("usuarios");
		registry.addViewController("/empresas").setViewName("empresas");
		registry.addViewController("/empresa").setViewName("empresa");
		registry.addViewController("/configuracion").setViewName("configuracion");
		registry.addViewController("/index").setViewName("index");
		registry.addViewController("/project-list-view").setViewName("project-list-view");
		registry.addViewController("investigacionp").setViewName("investigacionp");
		registry.addViewController("masiva").setViewName("masiva");
		registry.addViewController("indexTest").setViewName("indexTest");
		registry.addViewController("/widgets").setViewName("widgets");
		registry.addViewController("/showcase").setViewName("showcase");
		registry.addViewController("/changelog").setViewName("changelog");
		
		
		
	}
	
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

}