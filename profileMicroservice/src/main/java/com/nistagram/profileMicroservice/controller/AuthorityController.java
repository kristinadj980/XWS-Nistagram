package com.nistagram.profileMicroservice.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.nistagram.profileMicroservice.dto.PersonRequestDTO;
import com.nistagram.profileMicroservice.dto.UserTokenState;
import com.nistagram.profileMicroservice.model.Person;
import com.nistagram.profileMicroservice.model.Profile;
import com.nistagram.profileMicroservice.security.TokenUtils;
import com.nistagram.profileMicroservice.security.auth.JwtAuthenticationRequest;
import com.nistagram.profileMicroservice.service.implService.PersonService;
import com.nistagram.profileMicroservice.service.implService.ProfileService;



@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorityController {
	
	private final TokenUtils tokenUtils;
	private final ProfileService profileService;
	private final PersonService personService;
	private final AuthenticationManager authenticationManager;

	@Autowired
	public AuthorityController(TokenUtils tokenUtils,ProfileService profileService,PersonService personService,AuthenticationManager authenticationManager) {
		this.tokenUtils = tokenUtils;
		this.profileService = profileService;
		this.personService= personService;
		this.authenticationManager=authenticationManager;
	}
	
	// Endpoint za login korisnika
	@PostMapping("/login")
	public ResponseEntity<UserTokenState> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response) {
		System.out.println(authenticationRequest.getUsername());
		System.out.println(authenticationRequest.getPassword());
		System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
							authenticationRequest.getPassword()));  //pokusavamo autentifikaciju
	       System.out.println("********************************");
	       
	       
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		
		// Ubaci korisnika u trenutni security kontekst
		//SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
		// Kreiraj token za tog korisnika
		//Person person = (Person) authentication.getPrincipal();
		//String jwt = tokenUtils.generateToken(person.getUsername());
		System.out.println("KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
		int expiresIn = tokenUtils.getExpiredIn();
		// Vrati token kao odgovor na uspesnu autentifikaciju
		String jwt = "";
		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
	}
	
	@GetMapping("/authority")
	@PreAuthorize("hasRole('REGISTRED_USER')")
    ResponseEntity<Person> getMyAccount()
    {
		System.out.println("******************");
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        
        System.out.println(currentUser.getCredentials());
        Person user = (Person)currentUser.getPrincipal();
        Person userWithId = personService.findById(user.getId());

        return (ResponseEntity<Person>) (userWithId == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(userWithId));
    }
	
	
	// Endpoint za registraciju novog korisnika
		@PostMapping("/register")
		public ResponseEntity<String> registerUser(@RequestBody PersonRequestDTO userRequest, UriComponentsBuilder ucBuilder) {
			
	       if(!userRequest.getPassword().equals(userRequest.getConfirmPassword())) {
	        throw new IllegalArgumentException("Please make sure your password and confirmed password match!");
	       }
	       Person existingUser = profileService.findByEmail(userRequest.getEmail());
	       if (existingUser != null) {
	    	   throw new IllegalArgumentException("Email already exists!");
	       }
	       Person user = profileService.save(userRequest);
	    
	       return new ResponseEntity<>("User is successfully registred!", HttpStatus.CREATED);
		}

	

}