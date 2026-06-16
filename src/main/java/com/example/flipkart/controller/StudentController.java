package com.example.flipkart.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.flipkart.dto.StudentDTO;
import com.example.flipkart.exception.ResourceNotFoundException;
import com.example.flipkart.model.Student;
import com.example.flipkart.service.StudentService;

@RestController // return json
//@Controller // return html
public class StudentController {
	@Autowired
	StudentService studentService;
	
	private static final Logger logger = Logger.getLogger(StudentController.class);
	
	@RequestMapping("/test")
	private String test() {
		return "Virat";
	}

	@RequestMapping("/test1")
	private ResponseEntity<String> test1() {
		return new ResponseEntity<String>("Virat", HttpStatus.OK);
	}

	@GetMapping("/getStudent")
	private Student getStudent() {
		int studentId = 3;
		return studentService.getStudent(studentId);
	}

	@GetMapping("/getStudent1")
	private ResponseEntity<?> getStudent1() {
		int studentId = 20;
		try {
			return new ResponseEntity<Student>(studentService.getStudent1(studentId), HttpStatus.CREATED);
		} catch (RuntimeException e1) {
			return new ResponseEntity<String>("Student with roll number " + studentId + " does not exist",
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getStudent2")
	private ResponseEntity<?> getStudent2() {
		int studentId = 20;
		try {
			return new ResponseEntity<Student>(studentService.getStudent2(studentId), HttpStatus.CREATED);
		} catch (ResourceNotFoundException e1) {
			return new ResponseEntity<String>(e1.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getStudent3/{studentId}")
	private ResponseEntity<?> getStudent3(@PathVariable int studentId) {
		logger.info("Get Student with student id: "+studentId);
		return new ResponseEntity<Student>(studentService.getStudent2(studentId), HttpStatus.CREATED);

	}

	@GetMapping("/getStudentsGreaterThanPercentage/{inputPercentage}")
	private List<Student> getStudentsGreaterThanPercentage(@PathVariable double inputPercentage) {
		return studentService.getStudentsGreaterThanPercentage(inputPercentage);
	}

	@GetMapping("/getStudentsBetweenPercentage/{start}/{end}")
	private List<Student> getStudentsBetweenPercentage(@PathVariable double start, @PathVariable double end) {
		return studentService.getStudentsBetweenPercentage(start, end);
	}

	@GetMapping("/getStudentsFromDepartment/{inputDepartment}")
	private List<Student> getStudentsFromDepartment(@PathVariable String inputDepartment) {
		return studentService.getStudentsFromDepartment(inputDepartment);
	}

	@GetMapping("/getAllStudents")
	private List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}

	@GetMapping("/getAllStudents1")
	private ResponseEntity<List<Student>> getAllStudents1() {
		return new ResponseEntity<List<Student>>(studentService.getAllStudents(), HttpStatus.OK);
	}

	@PostMapping("/saveStudentGetString")
	private String saveStudentGetString() {
		Student student = Student.builder().sname("David").dname("MBA").per(85.89).build();
		studentService.saveStudentGetString(student);
		return "Record Saved";
	}

	@PostMapping("/saveStudentGetObject")
	private Student saveStudentGetObject() {
		Student student = Student.builder().sname("Chris").dname("Engineering").per(76.89).build();
		return studentService.saveStudentGetObject(student);
	}

	@PostMapping("/saveStudentGetResponseEntity")
	private ResponseEntity<Student> saveStudentGetResponseEntity() {
		Student newStudent = Student.builder().sname("Mangesh").dname("Teacher").per(96.89).build();
		Student savedStudent = studentService.saveStudentGetObject(newStudent);
		return new ResponseEntity<Student>(savedStudent, HttpStatus.CREATED);
	}

	@PostMapping("/saveStudentUsingRequestParam")
	private Student saveStudentUsingRequestParam(@RequestParam("a") String studentName,
			@RequestParam("b") String departmentName, @RequestParam("c") double percentage) {
		Student student = Student.builder().sname(studentName).dname(departmentName).per(percentage).build();
		return studentService.saveStudentGetObject(student);
	}

	@PostMapping("/saveStudentUsingRequestParam1")
	private Student saveStudentUsingRequestParam1(@RequestParam String studentName, @RequestParam String departmentName,
			@RequestParam double percentage) {
		Student student = Student.builder().sname(studentName).dname(departmentName).per(percentage).build();
		return studentService.saveStudentGetObject(student);
	}

	@PostMapping("/saveStudentUsingRequestParam2")
	private ResponseEntity<Student> saveStudentUsingRequestParam2(@RequestParam("a") String studentName,
			@RequestParam("b") String departmentName, @RequestParam("c") double percentage) {
		Student student = Student.builder().sname(studentName).dname(departmentName).per(percentage).build();
		return new ResponseEntity<Student>(studentService.saveStudentGetObject(student), HttpStatus.CREATED);
	}

	@PostMapping("/saveStudentUsingPathVariable/{a}/{b}/{c}")
	private Student saveStudentUsingPathVariable(@PathVariable("a") String studentName,
			@PathVariable("b") String departmentName, @PathVariable("c") double percentage) {
		Student student = Student.builder().sname(studentName).dname(departmentName).per(percentage).build();
		return studentService.saveStudentGetObject(student);
	}

	@PostMapping("/saveStudentUsingPathVariable1/{studentName}/{departmentName}/{percentage}")
	private Student saveStudentUsingPathVariable1(@PathVariable String studentName, @PathVariable String departmentName,
			@PathVariable double percentage) {
		Student student = Student.builder().sname(studentName).dname(departmentName).per(percentage).build();
		return studentService.saveStudentGetObject(student);
	}

	@PostMapping("/saveStudentUsingRequestBody")
	private Student saveStudentUsingRequestBody(@RequestBody Student student) {
		return studentService.saveStudentGetObject(student);
	}

	@GetMapping("/getStudentsByPage/{pageNumber}/{pageSize}")
	public Page<Student> getStudentsByPage(@PathVariable int pageNumber, @PathVariable int pageSize) {
		return studentService.getStudentsByPage(pageNumber, pageSize);
	}

	@GetMapping("/getStudentsByPageSorted/{pageNumber}/{pageSize}/{fieldName}")
	public Page<Student> getStudentsByPageSorted(@PathVariable int pageNumber, @PathVariable int pageSize,
			@PathVariable String fieldName) {
		return studentService.getStudentsByPageSorted(pageNumber, pageSize, fieldName);
	}

	@DeleteMapping("/deleteStudent/{inputId}")
	public ResponseEntity<String> deleteStudent(@PathVariable int inputId) {
		studentService.deleteStudent(inputId);
		return new ResponseEntity<String>("Student with id " + inputId + " is deleted", HttpStatus.OK);
	}

	@PutMapping("/updateStudent/{inputId}")
	public ResponseEntity<Student> updateStudent(@PathVariable int inputId, @RequestBody Student newValues) {
		Student updatedStudent = studentService.updateStudent(inputId, newValues);
		return new ResponseEntity<Student>(updatedStudent, HttpStatus.OK);
	}
	
	@PostMapping("/saveStudentUsingDTO")
	public ResponseEntity<StudentDTO> saveStudentUsingDTO(@RequestBody StudentDTO studentDTO) {
		return new ResponseEntity<StudentDTO>(studentService.saveStudentUsingDTO(studentDTO), HttpStatus.OK);
	}
}

/*
 * return 1 ) directly : limitation //fixed status code a= 200 fixed status
 * message OK 2 ) ResponseEntity<Student>, ResponseEntity<List<Student>>,
 * ResponseEntity<String>
 */
