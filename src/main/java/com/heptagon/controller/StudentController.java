package com.heptagon.controller;

import java.util.List;

import javax.validation.Valid;

import com.heptagon.error.StudentNotFoundException;
import com.heptagon.model.Student;
import com.heptagon.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class StudentController {
    @Autowired
    private StudentService studService;

    private final Logger log = LoggerFactory.getLogger(StudentController.class);

    @PostMapping(value = "/addStudent")
    public Student saveStudent(@Valid @RequestBody Student stud) {
        log.info("Inside Add Student of StudentController");
        return studService.saveStudent(stud);
    }

    @GetMapping(value = "/getStudents")
    public List<Student> getStudentList() {
        log.info("Inside GetStudentListAll of StudentController");
        return studService.getStudentList();
    }

    @GetMapping(value = "/getStudent/{id}")
    public Student getSingleStudent(@PathVariable("id") int sid) throws StudentNotFoundException {
        log.info("Student is not found with ID :: {}", sid);
        return studService.fetchStudentById(sid);
    }

    @DeleteMapping(value = "/deleteStudent/{id}")
    public String deleteStudById(@PathVariable("id") int sid) {
        log.info("Student is not found with ID :: {}", sid);
        studService.deleteStudentById(sid);
        return "Student Deleted Successfully";
    }

    @PutMapping(value = "/updateStudent/{id}")
    public Student updateStudent(@PathVariable("id") int sid, @RequestBody Student stud) {
        log.info("Student is not found with ID :: {}", sid);
        return studService.updateStudent(sid, stud);
    }

    @GetMapping(value = "/getStudent/name/{name}")
    public Student getStudentByName(@PathVariable("name") String studName) throws StudentNotFoundException {
        log.info("Student is not found with name :: {}", studName);
        return studService.getStudentByName(studName);
    }
}
