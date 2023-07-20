package mx.com.rmsh.horusControl.secutiry;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
//@EnableWebSecurity 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

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
			
		 UserDetails uli =
				 User.withDefaultPasswordEncoder()
					.username("Uli")
					.password("horus123")
					.roles("USER")
					.build();
		 
		 UserDetails generic =
				 User.withDefaultPasswordEncoder()
					.username("Diego")
					.password("horus123")
					.roles("USER")
					.build();
		 UserDetails ashley =
				 User.withDefaultPasswordEncoder()
					.username("Ashley")
					.password("horus222")
					.roles("USER")
					.build();
		 
		 List <UserDetails> userList = new ArrayList<UserDetails>();
		 
		 userList.add(uli);
		 userList.add(generic);
		 userList.add(ashley);

		return new InMemoryUserDetailsManager(userList);
		}	 	
	
}