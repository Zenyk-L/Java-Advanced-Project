package ua.lviv.lgs.university.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.lgs.university.domain.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Integer>{

}
