package ua.lviv.lgs.university.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.lgs.university.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	List<Student> findByEmail(String email);
}
