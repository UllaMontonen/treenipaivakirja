package Harjoitustyo.Treenipaivakirja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import Harjoitustyo.Treenipaivakirja.service.UserDetailsServiceImpl;

@Configuration 	// tells that this class contains configuration which must be done when starting application.
@EnableGlobalMethodSecurity(prePostEnabled = true) 	// allows to use method level authorization. 
@EnableWebSecurity 	// tells that application is using spring security
public class WebSecurityConfig {

	 @Autowired
	    private UserDetailsServiceImpl userDetailsService;	
	
	 @Bean
		public SecurityFilterChain configure(HttpSecurity http) throws Exception {

			return http.authorizeRequests(auth -> {
				// css folder is accessible for all same with h2 database
				auth.antMatchers("/css/**").permitAll();
				auth.antMatchers("/h2-console/**").permitAll();
				auth.antMatchers("/h2-console").permitAll();
				
				// if user's role is ADMIN s/he can add, edit or delete data
				auth.antMatchers("/add/**").hasAuthority("ADMIN");
				auth.antMatchers("/edit/**").hasAuthority("ADMIN");
				auth.antMatchers("/delete/**").hasAuthority("ADMIN");
				
				// every http request will be authenticated
				auth.anyRequest().authenticated();
				
			}).csrf().ignoringAntMatchers("h2-console").and()
					// tells where to go after successful login
					.formLogin().defaultSuccessUrl("/traininglist", true).and()
					// Logout is permitted for all users
					.logout().permitAll().and()
					.httpBasic(Customizer.withDefaults()).build();
		}
	    
	    
	 @Autowired 	//application is using this method when user makes login
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
		}
	
}

