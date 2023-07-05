package com.example.practise.service;

import com.example.practise.controller.ApiResponse;
import com.example.practise.entity.Person;

public interface PersonService {

	ApiResponse<Person> findbyId(int id);
}
