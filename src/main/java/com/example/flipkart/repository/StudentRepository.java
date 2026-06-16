package com.example.flipkart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.flipkart.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
//	@Query(nativeQuery = true, value = "Select * from student where per > ?1")
//	public List<Student> getStudentsGreaterThanPercentage(double percentage);
	
	public List<Student> findByPerGreaterThan(double inputPercentage);
	
//	@Query(nativeQuery = true, value = "Select * from student where dname like ?1")
//	public List<Student> getStudentsFromDepartment(String inputDepartment);
	
	public List<Student> findByDnameEquals(String inputDepartment);
	
	public List<Student> findByPerBetween(double start, double end);
}







