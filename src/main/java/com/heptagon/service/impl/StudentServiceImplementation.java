package com.heptagon.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.heptagon.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heptagon.model.Student;
import com.heptagon.error.StudentNotFoundException;
import com.heptagon.repository.StudentRepo;

@Service
public class StudentServiceImplementation implements StudentService {
    @Autowired
    private StudentRepo repo;

    @Override
    public Student saveStudent(Student stud) {
        return repo.save(stud);
    }

    @Override
    public List<Student> getStudentList() {
        return repo.findAll();
    }

    @Override
    public Student fetchStudentById(int sid) throws StudentNotFoundException {
        Optional<Student> st = repo.findById(sid);
        if (!st.isPresent()) {
            throw new StudentNotFoundException("Student Not Available");
        }
        return st.get();
    }

    @Override
    public void deleteStudentById(int sid) {
        repo.deleteById(sid);
    }

    @Override
    public Student updateStudent(int sid, Student stud) {
        Student st = repo.findById(sid).get();
        if (Objects.nonNull(stud.getStudentName()) && !"".equalsIgnoreCase(stud.getStudentName())) {
            st.setStudentName(stud.getStudentName());
        }
        if (Objects.nonNull(stud.getCollegeName()) && !"".equalsIgnoreCase(stud.getCollegeName())) {
            st.setCollegeName(stud.getCollegeName());
        }
        if (Objects.nonNull(stud.getStudentCode()) && !"".equalsIgnoreCase(stud.getStudentCode())) {
            st.setStudentCode(stud.getStudentCode());
        }

        return repo.save(st);
    }

    @Override
    public Student getStudentByName(String studName) throws StudentNotFoundException {
        Optional<Student> st = repo.findByStudentName(studName);
        if (!st.isPresent()) {
            throw new StudentNotFoundException("Student Not Available");
        }
        return st.get();
    }


}
