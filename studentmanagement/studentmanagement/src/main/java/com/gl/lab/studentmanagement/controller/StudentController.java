package com.gl.lab.studentmanagement.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gl.lab.studentmanagement.Exception.StudentException;
import com.gl.lab.studentmanagement.entity.Student;
import com.gl.lab.studentmanagement.service.StudentService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/student")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Student theStudent = new Student();

        theModel.addAttribute("Student", theStudent);

        return "Student-form";
    }

    @RequestMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("studentId") int theId,
                                    Model theModel) {

        try {
            Student theStudent = studentService.findById(theId);

            theModel.addAttribute("Student", theStudent);
        }catch (StudentException e){
            e.getMessage();
        }
        return "Student-form";
    }

    @GetMapping(name="/list")
    public String listStudents(Model theModel){
        List<Student> studentList=studentService.findAll();

        theModel.addAttribute("Students",studentList);

        return "list-Students";
    }

    @PostMapping("/save")
    public String save(@RequestParam("id") int id,
                       @RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,@RequestParam("course") String course,@RequestParam("country") String country) {
        Student theStudent=new Student();
        if(id!=0)
        {
            try {
                theStudent = studentService.findById(id);
                theStudent.setFirstName(firstName);
                theStudent.setLastName(lastName);
                theStudent.setCourse(course);
                theStudent.setCountry(country);
            }catch (StudentException e){
                e.getMessage();
            }
        }
        else
            theStudent=new Student(firstName, lastName, course,country);

        studentService.save(theStudent);


        // use a redirect to prevent duplicate submissions
        return "redirect:/student/list";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("studentId") Integer id){
        studentService.deleteById(id);

        return "redirect:/student/list";
    }

    @RequestMapping(value = "/403")
    public ModelAndView accesssDenied(Principal user) {

        ModelAndView model = new ModelAndView();

        if (user != null) {
            model.addObject("msg", "Hi " + user.getName()
                    + ", you do not have permission to access this page!");
        } else {
            model.addObject("msg",
                    "You do not have permission to access this page!");
        }

        model.setViewName("403");
        return model;

    }
}
