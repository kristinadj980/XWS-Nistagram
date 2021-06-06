package com.nistagram.profileMicroservice.service.implService;
import com.nistagram.profileMicroservice.model.Person;
import com.nistagram.profileMicroservice.repository.PersonRepository;
import com.nistagram.profileMicroservice.service.IPersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements IPersonService, UserDetailsService {
	
	private final PersonRepository personRepository;
	
	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	public Person findByEmail(String email) {
		return personRepository.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Person person = personRepository.findByUsername(username);
		if (person == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {
			return person;
		}
	}

	@Override
	public Person findById(Long id) {
		return personRepository.findById(id).orElseGet(null);
	}
}
