package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.CourseModel;
import com.example.model.StudentModel;
import com.example.service.StudentService;

@Controller
public class StudentController
{
    @Autowired
    StudentService studentDAO;


    @RequestMapping("/")
    public String index (Model model)
    {
    	model.addAttribute("title_page", "Index");
        return "index";
    }


    @RequestMapping("/student/add")
    public String add (Model model)
    {
    	model.addAttribute("title_page", "Add");
        return "form-add";
    }


    @RequestMapping("/student/add/submit")
    public String addSubmit (Model model,
            @RequestParam(value = "npm", required = false) String npm,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gpa", required = false) double gpa)
    {
        StudentModel student = new StudentModel (npm, name, gpa, null);
        studentDAO.addStudent (student);
        model.addAttribute("title_page", "Index");
        return "success-add";
    }


    @RequestMapping("/student/view")
    public String view (Model model,
            @RequestParam(value = "npm", required = false) String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            model.addAttribute("title_page", "View Student");
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            model.addAttribute("title_page", "Not Found");
            return "not-found";
        }
    }


    @RequestMapping("/student/view/{npm}")
    public String viewPath (Model model,
            @PathVariable(value = "npm") String npm)
    {
        StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            model.addAttribute ("student", student);
            model.addAttribute("title_page", "View Student");
            return "view";
        } else {
            model.addAttribute ("npm", npm);
            model.addAttribute("title_page", "Not Found");
            return "not-found";
        }
    }


    @RequestMapping("/student/viewall")
    public String view (Model model)
    {
        List<StudentModel> students = studentDAO.selectAllStudents ();
        model.addAttribute ("students", students);
        model.addAttribute("title_page", "View All Student(s)");
        return "viewall";
    }


    @RequestMapping("/student/delete/{npm}")
    public String delete (Model model, @PathVariable(value = "npm") String npm)
    {
    	StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
            studentDAO.deleteStudent (npm);
            model.addAttribute("title_page", "Delete Student");
            return "delete";
        } else {
        	model.addAttribute("title_page", "Not Found");
            return "not-found";
        }
    }
    
    @RequestMapping("/student/update/{npm}")
    public String update (Model model, @PathVariable(value = "npm") String npm)
    {
    	StudentModel student = studentDAO.selectStudent (npm);

        if (student != null) {
        	model.addAttribute ("student", student);
        	model.addAttribute("title_page", "Update Student");
        	return "form-update";
        } else {
            return "not-found";
        }
    }
    
    @RequestMapping(value = "/student/update/submit", method = RequestMethod.POST)
    public String updateSubmit (Model model, @ModelAttribute StudentModel student)
    {
        studentDAO.updateStudent(student);
        model.addAttribute("title_page", "Success");
        return "success-update";
    }
    
    @RequestMapping("/course/view/{id}")
    public String viewCourse (Model model,
            @PathVariable(value = "id") String id)
    {
        CourseModel course = studentDAO.selectCourseInfo(id);

        if (course != null) {
            model.addAttribute ("course", course);
            model.addAttribute("title_page", "View Course");
            return "view-course";
        } else {
            model.addAttribute ("id", id);
            return "not-found";
        }
    }

}
