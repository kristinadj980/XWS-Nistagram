package com.nistagram.profileMicroservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.nistagram.profileMicroservice.security.TokenUtils;
import com.nistagram.profileMicroservice.security.auth.RestAuthenticationEntryPoint;
import com.nistagram.profileMicroservice.security.auth.TokenAuthenticationFilter;
import com.nistagram.profileMicroservice.service.implService.PersonService;


@Configuration
//Ukljucivanje podrske za anotacije "@Pre*" i "@Post*" koje ce aktivirati autorizacione provere za svaki pristup metodi
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	// Implementacija PasswordEncoder-a koriscenjem BCrypt hashing funkcije.
		// BCrypt po defalt-u radi 10 rundi hesiranja prosledjene vrednosti.
		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}

		// Servis koji se koristi za citanje podataka o korisnicima aplikacije
		@Autowired
		private PersonService jwtPersonService;

		// Handler za vracanje 401 kada klijent sa neodogovarajucim korisnickim imenom i lozinkom pokusa da pristupi resursu
		@Autowired
		private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

		// Registrujemo authentication manager koji ce da uradi autentifikaciju korisnika za nas
		@Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

		 // Define instructions for the authentication manager which service to use to extract data about the user who wants to be authenticated,
	    // as well as through which encoder to pass the password received from the client in the request to compare the adequate hash obtained as a result of the bcrypt algorithm with the one in the database (since no plain password is stored in the database)
	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(jwtPersonService).passwordEncoder(passwordEncoder());
	    }

		// Injektujemo implementaciju iz TokenUtils klase kako bismo mogli da koristimo njene metode za rad sa JWT u TokenAuthenticationFilteru
		@Autowired
		private TokenUtils tokenUtils;

		// Definisemo prava pristupa odredjenim URL-ovima
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
			 // Communication between the client and the server is stateless since it is a REST application
          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

          // Process all unauthenticated requests uniformly and send a 401 error
          .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()

					// svim korisnicima dopusti da pristupe putanjama /auth/**, (/h2-console/** ako se koristi H2 baza) i /api/foo
					.authorizeRequests().antMatchers("/api/auth/**").permitAll().antMatchers("/h2-console/**").permitAll().antMatchers("/api/foo").permitAll()
					
					// za svaki drugi zahtev korisnik mora biti autentifikovan
					.anyRequest().authenticated().and()
					// za development svrhe ukljuci konfiguraciju za CORS iz WebConfig klase
					.cors().and()

					// umetni custom filter TokenAuthenticationFilter kako bi se vrsila provera JWT tokena umesto cistih korisnickog imena i lozinke (koje radi BasicAuthenticationFilter)
					.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtPersonService),
							BasicAuthenticationFilter.class);
			// zbog jednostavnosti primera
			http.csrf().disable();
		}

		// Generalna bezbednost aplikacije
		@Override 
		public void configure(WebSecurity web) throws Exception {
			// TokenAuthenticationFilter ce ignorisati sve ispod navedene putanje
			web.ignoring().antMatchers(HttpMethod.POST, "/api/auth/login");
			web.ignoring().antMatchers(HttpMethod.POST, "/api/auth/register"); 
			web.ignoring().antMatchers(HttpMethod.GET, "/api/profile/getAllUsers", "/api/profile/getUserProfile/{username}");
			web.ignoring().antMatchers(HttpMethod.POST, "/api/profile/proba"); 
			web.ignoring().antMatchers(HttpMethod.POST, "/api/notifications/postNotify");
			web.ignoring().antMatchers(HttpMethod.POST, "/api/notifications/storyNotify"); 
			web.ignoring().antMatchers(HttpMethod.POST, "/api/notifications/commentNotify"); 
			web.ignoring().antMatchers(HttpMethod.GET, "/api/profile/proba"); 
			web.ignoring().antMatchers(HttpMethod.GET, "/api/profile/getAllUsers");
			//web.ignoring().antMatchers(HttpMethod.POST, "/api/profile/getCloseFriends"); 
			//web.ignoring().antMatchers(HttpMethod.GET, "/api/profile/getCloseFriends"); 
			//web.ignoring().antMatchers(HttpMethod.GET, "/api/profile/getAllUsers");
			web.ignoring().antMatchers(HttpMethod.GET, "/api/profile/getPublicProfiles");
			web.ignoring().antMatchers(HttpMethod.GET, "/api/profile/getPrivateProfiles");
			
			web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html",
					"/**/*.css", "/**/*.js");
		}

}
