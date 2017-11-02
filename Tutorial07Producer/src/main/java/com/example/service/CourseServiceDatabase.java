package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.StudentMapper;
import com.example.model.CourseModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CourseServiceDatabase implements CourseService
{
    @Autowired
    private StudentMapper studentMapper;
    
    @Override
    public List<CourseModel> selectAllCourses (){
        log.info ("select all students");
        return studentMapper.selectAllCourses ();
    }
    
    public CourseModel selectCourseInfo(String id) {
    	log.info("course" + id + "selected");
    	return studentMapper.selectCourseInfo(id);
    }
}
