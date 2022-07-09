package com.heptagon.service;

import java.util.List;

import com.heptagon.model.Student;
import com.heptagon.error.StudentNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {

	public Student saveStudent(Student stud);

	public List<Student> getStudentList();

	public Student fetchStudentById(int sid) throws StudentNotFoundException;

	public void deleteStudentById(int sid);

	public Student updateStudent(int sid, Student stud);

	public Student getStudentByName(String studName) throws StudentNotFoundException;

	

}
