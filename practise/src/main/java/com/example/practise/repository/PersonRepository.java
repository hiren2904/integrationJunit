package com.example.practise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.practise.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{

}
