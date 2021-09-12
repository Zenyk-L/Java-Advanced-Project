package ua.lviv.lgs.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.university.dao.FacultyRepository;
import ua.lviv.lgs.university.domain.Faculty;

@Service
public class FacultyService {
	
	@Autowired
	private FacultyRepository facultyRepository;
	
	public Faculty save(Faculty faculty) {
		return facultyRepository.save(faculty);
	}
	
	public List<Faculty> getAllFaculty(){
		return facultyRepository.findAll();
	}
	
	public Faculty getFacultyByName(String name){
		return facultyRepository.findByName(name).get();
	}
	
	public Faculty getFacultyById(Integer id) {
		return facultyRepository.getById(id);
	}
	

}
