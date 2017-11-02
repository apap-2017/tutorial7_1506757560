package com.example.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.model.CourseModel;
import com.example.model.StudentModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CourseDAOImpl implements CourseDAO {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Autowired
	private RestTemplate restTemplate;

	public CourseModel selectCourseInfo(String id) {
		log.info(id);
		CourseModel course =
				restTemplate.getForObject(
						"http://localhost:8080/rest/course/view/"+id,
						CourseModel.class);
		return course;
	}
	
	public List<CourseModel> selectAllCourses(){
		List<CourseModel> courses = 
				restTemplate.getForObject(
						"http://localhost:8080/rest/course/viewall",
						List.class);
		log.info("masuk lagi");
		return courses;
	}
}
