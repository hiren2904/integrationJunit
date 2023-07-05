package com.example.practise.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.practise.dto.RequestDto;
import com.example.practise.entity.Student;
import com.example.practise.repository.StudentRepository;
import com.example.practise.service.FilterSpecification;

@RestController
@RequestMapping("/filter")
public class FilterController {

	@Autowired
	private StudentRepository repo;
	
	@Autowired
	private FilterSpecification<Student> studentfilterspecification;
	
	@GetMapping("/{name}")
	public Student getStudentByName(@PathVariable("name") String name) {
		return repo.findByName(name);
	}
	@GetMapping("/city/{CITY}")
	public List<Student> getStudentByCity(@PathVariable("CITY") String city) {
		return repo.findByAddressCity(city);
	}
	@GetMapping("/subject/{SUB}")
	public List<Student> getStudentBySubject(@PathVariable("SUB") String subject) {
		return repo.findBySubjectName(subject);
	}
	
//	@PostMapping("/specification")
//	public List<Student> getStudents(){
//		
//		Specification specification = new Specification<Student>() {
//
//			@Override
//			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//				// TODO Auto-generated method stub
//				return criteriaBuilder.equal(root.get("id"),"3");
//			}
//		};
//		
//		List<Student> students = repo.findAll(specification);
//		
//		return students;
//	}
	
	@PostMapping("/specification")
	public List<Student> getStudents(@RequestBody RequestDto requestdto){
		
	
		Specification<Student> specification = studentfilterspecification
				.getSearchSpecification(requestdto.getSearchRequestDto(),requestdto.getGlobaloperator());
		return repo.findAll(specification);
	}
	
	
	
}


























