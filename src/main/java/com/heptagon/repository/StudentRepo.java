package com.heptagon.repository;

import com.heptagon.error.StudentNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heptagon.model.Student;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer> {
	public Optional<Student> findByStudentName(String studentName) throws StudentNotFoundException;
}
