package com.example.flipkart.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.flipkart.dto.StudentDTO;
import com.example.flipkart.exception.ResourceNotFoundException;
import com.example.flipkart.model.Student;
import com.example.flipkart.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	ModelMapper modelMapper;
	private static final Logger logger = Logger.getLogger(StudentService.class);
	
	public Student getStudent(int studentId) {
		Optional<Student> optionalStudent = studentRepository.findById(studentId);
		return optionalStudent.get();
	}
	
	public Student getStudent1(int studentId) throws RuntimeException {
		if(studentRepository.existsById(studentId)) {
			Optional<Student> optionalStudent = studentRepository.findById(studentId);
			return optionalStudent.get();
		} else {
			throw new RuntimeException("Student with roll number " + studentId + " does not exist");
		}
	}
	
	public Student getStudent2(int studentId) throws ResourceNotFoundException {
		if(studentRepository.existsById(studentId)) {
			logger.info("Get Student with student id: "+studentId);
			Optional<Student> optionalStudent = studentRepository.findById(studentId);
			return optionalStudent.get();
		}
		throw new ResourceNotFoundException("Resource with id " + studentId + " does not exist");
	}
	
	public List<Student> getStudentsGreaterThanPercentage(double inputPercentage){
//		return studentRepository.getStudentsGreaterThanPercentage(percentage);
		return studentRepository.findByPerGreaterThan(inputPercentage);
	}
	
	public List<Student> getStudentsBetweenPercentage(double start, double end){
//		return studentRepository.getStudentsGreaterThanPercentage(percentage);
		return studentRepository.findByPerBetween(start, end);
	}
	
	public List<Student> getStudentsFromDepartment(String inputDepartment){
//		return studentRepository.getStudentsFromDepartment(inputDepartment);
		return studentRepository.findByDnameEquals(inputDepartment);
	}
	
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	public void saveStudentGetString(Student student) {
		studentRepository.save(student);
	}
	
	public Student saveStudentGetObject(Student student) {
		return studentRepository.save(student);
	}
	
	public Page<Student> getStudentsByPage(int pageNumber, int pageSize){
		return studentRepository.findAll(PageRequest.of(pageNumber, pageSize));
	}
	
	public Page<Student> getStudentsByPageSorted(int pageNumber, int pageSize, String fieldName){
		return studentRepository.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC, fieldName)));
	}
	
	public void deleteStudent(int inputId){
		if(studentRepository.existsById(inputId)) {
			studentRepository.deleteById(inputId);
			return;
		}
		throw new ResourceNotFoundException("Resource not available in database");
	}
	
	public Student updateStudent(int inputId, Student newValues){
		if(studentRepository.existsById(inputId)) {
			Student studentFromDB = studentRepository.findById(inputId).get();
			// Using setter
//			studentFromDB.setSname(newValues.getSname());
//			studentFromDB.setDname(newValues.getDname());
//			studentFromDB.setPer(newValues.getPer());
			Student updatedStudent = Student
			.builder()
			.rno(inputId)
			.sname(newValues.getSname())
			.dname(newValues.getDname())
			.per(newValues.getPer())
			.build();
			return studentRepository.save(updatedStudent);
		}
		throw new ResourceNotFoundException("Resource not available in database");
	}
	
	public StudentDTO saveStudentUsingDTO(StudentDTO studentDTO) {
		Student studentEntity = modelMapper.map(studentDTO, Student.class);
		Student savedStudentEntity = studentRepository.save(studentEntity); 
		return modelMapper.map(savedStudentEntity, StudentDTO.class);
	}
}








