package com.placement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.placement.entity.Job;
import com.placement.entity.Student;
import com.placement.exceptions.NotFoundException;
import com.placement.service.StudentService;

@SpringBootTest
class PlacementMgmtSystemApplicationTests {
	
	
	StudentService studentServ;
	
	@Autowired
	public PlacementMgmtSystemApplicationTests(StudentService studentServ) {
		super();
		this.studentServ = studentServ;
	}

	@Test
	public void saveStudentTest() {
		Set<Job> jobSet = new HashSet<>();
		Student theStudent  = new Student(22L, "abc213", "abc", "xyz", "abcxyz@gmail.com", "1234", "1209876543", "Btech", "Female", "https://www.linkedin.com", true,jobSet);
	
		Student student = studentServ.saveAllStudent(theStudent);
		
		assertNotNull(student);
	}
	
	@Test
	public void findAllStudentTest() {
		List<Student> student = studentServ.findAllStudent();
		
		assertThat(student).size().isGreaterThan(0);
	}
	
	
	@Test
	public void findStudentByUsernameAndPasswordTest() {
		Student student = studentServ.findStudentByUsernameAndPassword("abc2", "1234");
		assertEquals("abc2", student.getUsername());
	}
	
	@Test
	public void findStudentByUsernameAndPasswordTest1() {
//		Student student = studentServ.findStudentByUsernameAndPassword("lalit", "pas");
		Exception ex= assertThrows(NotFoundException.class,()->{
			studentServ.findStudentByUsernameAndPassword("sai", "1235");
		});
		String actualMsg = ex.getMessage();
		System.out.println(actualMsg);
		String expectedMsg = "Invalid Credential !!";
		
		assertFalse(expectedMsg.equals(actualMsg));	
	}
	@Test
	public void findStudentByIdTest() {
//		Student student = studentServ.findStudentByUsernameAndPassword("lalit", "pas");
		Exception ex= assertThrows(NotFoundException.class,()->{
			studentServ.findStudentById(70l);
		});
		String actualMsg = ex.getMessage();
		System.out.println(actualMsg);
		String expectedMsg = "Student not found !!, whose ID is "+70l;
		
		assertTrue(expectedMsg.equals(actualMsg));
		
	}
	
}
