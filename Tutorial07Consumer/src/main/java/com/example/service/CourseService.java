package com.example.service;

import java.util.List;

import com.example.model.CourseModel;

public interface CourseService
{
    List<CourseModel> selectAllCourses ();
    
    CourseModel selectCourseInfo(String id);
}
