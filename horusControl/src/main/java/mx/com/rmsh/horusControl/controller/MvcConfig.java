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
		registry.addViewController("/investigacion").setViewName("investigacion");
		registry.addViewController("/usuarios").setViewName("usuarios");
		
		
		
	}
	
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

}