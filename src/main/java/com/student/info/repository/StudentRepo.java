package com.student.info.repository;

import com.student.info.error.StudentNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.info.model.Student;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer>
{
	public Optional<Student> findByStudentName(String studentName) throws StudentNotFoundException;
}
