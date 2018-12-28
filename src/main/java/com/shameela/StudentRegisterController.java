package com.shameela;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiOperation;

/*
 * @class StudentRegisterController to handle all incoming requests
 */
@RestController
public class StudentRegisterController {

	@Autowired
	private StudentRepository studentRepository;
	
	/*
	 * @Method retrieveAllStudents to fetch all students
	 */
	@GetMapping("/students")
	@ResponseBody
	public List<StudentRegistration> retrieveAllStudents() {
		return studentRepository.findAll();
	}

	/*
	 * @Method retrieveStudent to fetch a particular student details
	 */
	@GetMapping("/students/{registrationnumber}")
	@ApiOperation(value = "Find student by id",
    notes = "Also returns a link to retrieve all students with rel - all-students")
	@ResponseBody
	public StudentRegistration retrieveStudent(@PathVariable long registrationnumber) {
		Optional<StudentRegistration> student = studentRepository.findById(registrationnumber);
        System.out.println(student.isPresent());
		if (!student.isPresent())
			throw new StudentNotFoundException("id-" + registrationnumber);

		return student.get();
	}

	/*
	 * @Method deleteStudent to delete a particular student details
	 */
	@DeleteMapping("/students/{registrationnumber}")
	public void deleteStudent(@PathVariable long registrationnumber) {
		studentRepository.deleteById(registrationnumber);
	}

	/*
	 * @Method createStudent to create a particular student details
	 */
	@PostMapping("/students")
	public ResponseEntity<Object> createStudent(@RequestBody StudentRegistration student) {
		StudentRegistration savedStudent = studentRepository.save(student);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{registrationnumber}")
				.buildAndExpand(savedStudent.getRegistrationnumber()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	
	/*
	 * @Method updateStudent to update a particular student details
	 */
	@PutMapping("/students/{registrationnumber}")
	public ResponseEntity<Object> updateStudent(@RequestBody StudentRegistration student, @PathVariable long registrationnumber) {

		Optional<StudentRegistration> studentOptional = studentRepository.findById(registrationnumber);

		if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();

		student.setRegistrationnumber(registrationnumber);
		
		studentRepository.save(student);

		return ResponseEntity.noContent().build();
	}
}
