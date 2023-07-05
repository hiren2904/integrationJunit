package com.example.practise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.practise.controller.ApiResponse;
import com.example.practise.entity.Person;
import com.example.practise.service.PersonService;
import com.example.practise.service.PersonServiceImpl;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

	private static final Class IllegalArgumentException = null;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate template;
	
	@Autowired
	private PersonService service;
	
	@Autowired
	private PersonServiceImpl personserviceImpl;

	@Test
	public void testPerson() {

		int personId = 1;

		Map<Integer, Integer> map = new HashMap<>();
		map.put(personId, 1);

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Map<Integer, Integer>> requestEntity = new HttpEntity<>(map, headers);

		ResponseEntity<ApiResponse<Person>> response = template.exchange("/findperson/{personId}", HttpMethod.GET,
				requestEntity, new ParameterizedTypeReference<ApiResponse<Person>>() {
				}, personId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
// Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
		assertNotNull(response.getBody());
	}

	@Test
	public void testPersonException() {

		int personID = 10000000;
		Map<Integer, Integer> map = new HashMap<>();
		map.put(personID, 10000000);
		HttpHeaders header = new HttpHeaders();
		HttpEntity<Map<Integer, Integer>> requestEntity = new HttpEntity<>(map, header);

		ResponseEntity<ApiResponse<Person>> response = template.exchange("/findperson/{personId}", HttpMethod.GET,
				requestEntity, new ParameterizedTypeReference<ApiResponse<Person>>() {
				}, map);

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
	
	@Test
	public void testChecklist() throws Exception{
	        
	int personId = 1;
	String expectedname = "Hiren";
	
	String actualname = personserviceImpl.countnumberofPerson(personId);
	
	assertEquals(expectedname, actualname);
	//assertThrows(IllegalArgumentException.class, () -> personserviceImpl.countnumberofPerson(personId));
	}
	
	@Test
	public void testChecklistException() throws Exception{
	        
	String personId = "hh" ;
	// String expectedname = "Hiren";
	
	// String actualname = personserviceImpl.countnumberofPerson(personId);
	
	assertThrows(IllegalArgumentException.class, () -> personserviceImpl.countnumberofPerson(Integer.parseInt(personId)));
	}

}
































