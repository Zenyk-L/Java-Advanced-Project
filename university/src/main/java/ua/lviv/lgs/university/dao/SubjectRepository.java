package ua.lviv.lgs.university.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.lgs.university.domain.Subject;

public interface SubjectRepository extends JpaRepository <Subject, Integer> {

}
