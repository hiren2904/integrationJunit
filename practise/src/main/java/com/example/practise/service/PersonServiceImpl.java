package com.example.practise.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.practise.controller.ApiResponse;
import com.example.practise.controller.ErrorResponse;
import com.example.practise.entity.Person;
import com.example.practise.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService{

	@Autowired
	private PersonRepository repo;
	
	@Override
	public 	ApiResponse<Person> findbyId(int id) {
		ApiResponse<Person> response = new ApiResponse<>();
		try {
			
		Person person = repo.findById(id).get();	
		
		countnumberofPerson(id);
		response.setResponse(person);	
		response.setSuccess(true);
		return response;
		}catch(Exception e) {
			response.setResponse(null);
			response.setError(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.toString()));
			return response;
		}
		
	}
	
	public String countnumberofPerson(int id) {
		
		Person person = repo.findById(id).get();
		return person.getName();
//		List<Person>  list = repo.findAll();
	}

}
