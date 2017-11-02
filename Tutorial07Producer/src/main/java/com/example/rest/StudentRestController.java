package com.example.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.CourseModel;
import com.example.model.StudentModel;
import com.example.service.CourseService;
import com.example.service.StudentService;

@RestController
@RequestMapping("/rest")
public class StudentRestController {
	@Autowired
	StudentService studentService;
	@Autowired
	CourseService courseService;
	
	@RequestMapping("/student/view/{npm}")
	public StudentModel viewStudent (@PathVariable(value = "npm") String npm) {
		StudentModel student = studentService.selectStudent(npm);
		return student;
	}
	
	@RequestMapping("/student/viewall")
	public List<StudentModel> viewAllStudent (){
	    List<StudentModel> students = studentService.selectAllStudents();
	    return students;
	}
	
	@RequestMapping("/course/view/{id}")
	public CourseModel viewCourse (@PathVariable(value = "id") String id) {
		CourseModel course = courseService.selectCourseInfo(id);
		return course;
	}
	
	@RequestMapping("/course/viewall")
	public List<CourseModel> viewAllCourse (){
	    List<CourseModel> courses = courseService.selectAllCourses();
	    return courses;
	}
}
