package com.example.practise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.practise.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>,JpaSpecificationExecutor<Student> {

	Student findByName(String name);
	
	
	List<Student> findByAddressCity(String city);
	
	List<Student> findBySubjectName(String subname);
}
