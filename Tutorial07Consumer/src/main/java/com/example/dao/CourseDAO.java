package com.example.dao;

import java.util.List;

import com.example.model.CourseModel;

public interface CourseDAO {
	CourseModel selectCourseInfo(String id);
	List<CourseModel> selectAllCourses();
}
