package com.student.info.controller;

import java.util.List;

import javax.validation.Valid;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

import com.student.info.model.Student;
import com.student.info.error.StudentNotFoundException;
import com.student.info.service.StudentService;

@RestController
@SecurityRequirement(name = "pullagura")
public class StudentController {
    @Autowired
    private StudentService studService;

    private final Logger log = LoggerFactory.getLogger(StudentController.class);

    @PostMapping(value = "/addStudent")
    public Student saveStudent(@Valid @RequestBody Student stud) {
        log.info("Inside Add Student of StudentController");
        return studService.saveStudent(stud);
    }

    @RequestMapping(value = "/getStudents")
    public List<Student> getStudentList() {
        log.info("Inside GetStudentListAll of StudentController");
        return studService.getStudentList();
    }

    @GetMapping(value = "/getStudent/{id}")
    public Student getSingleStudent(@PathVariable("id") int sid) throws StudentNotFoundException {
        return studService.fetchStudentById(sid);
    }

    @DeleteMapping(value = "/deleteStudent/{id}")
    public String deleteStudById(@PathVariable("id") int sid) {
        studService.deleteStudentById(sid);
        return "Student Deleted Successfully";
    }

    @PutMapping(value = "/updateStudent/{id}")
    public Student updateStudent(@PathVariable("id") int sid, @RequestBody Student stud) {
        return studService.updateStudent(sid, stud);
    }

    @GetMapping(value = "/getStudent/name/{name}")
    public Student getStudentByName(@PathVariable("name") String studName) throws StudentNotFoundException {
        return studService.getStudentByName(studName);
    }
}
