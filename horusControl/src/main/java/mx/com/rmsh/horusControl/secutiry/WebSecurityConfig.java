package mx.com.rmsh.horusControl.secutiry;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import mx.com.rmsh.horusControl.dao.SecurityDaoImpl;
import mx.com.rmsh.horusControl.vo.UserHorus;

@Configuration
//@EnableWebSecurity 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	SecurityDaoImpl daoUser;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests				
				.antMatchers("/","/resources/**").permitAll()							
				.anyRequest().authenticated()
				
			)
			.formLogin((form) -> form
				.loginPage("/login").permitAll()
				.defaultSuccessUrl("/menu")
				
			)
			.logout((logout) -> logout.permitAll());
		
		http.csrf().disable();

		
	}

	
	 @Override
     public void configure(WebSecurity web) throws Exception {
         web
                 .ignoring()
                 .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","/vendor/**","/fonts/**");
     }

	 @Bean
	public UserDetailsService userDetailsService() {
		 
		 
		 List <UserDetails> userList = new ArrayList<UserDetails>();		 
		 
		 
		 for(UserHorus aux : daoUser.getUser()){
			 
			 UserDetails actualUser =
					 User.withDefaultPasswordEncoder()
						.username(aux.getName())
						.password(aux.getPassword())
						.roles("PAPU")
						.build();
			 
			 userList.add(actualUser);
			 
			}
		 
		
		 return new InMemoryUserDetailsManager(userList);
		//return new InMemoryUserDetailsManager(userList);
		}	 	
	
}