package com.example.practise.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.practise.entity.Person;
import com.example.practise.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@GetMapping("/findperson/{id}")
	public ResponseEntity<ApiResponse<Person>> findbyid(@PathVariable String id ) {
		ApiResponse<Person> apiResponse = new ApiResponse<>();
		try {
			apiResponse = personService.findbyId(Integer.parseInt(id));
			 return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		}catch(Exception e) {
			apiResponse.setSuccess(false);
			apiResponse.setError(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.toString()));
			return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
	}
}