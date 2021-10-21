package ua.lviv.lgs.university.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.university.dao.FacultyRepository;
import ua.lviv.lgs.university.dao.UserRepository;
import ua.lviv.lgs.university.domain.Faculty;
import ua.lviv.lgs.university.domain.User;

@Service
public class FacultyService {
	
	@Autowired
	private FacultyRepository facultyRepository;
	
	
	@Autowired
	private UserRepository userRepository;
	
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
	
	public void deleteFacultyById(Integer id) {
		facultyRepository.deleteById(id);
	}
	
	public void deleteUserFromFaculty(Integer userId, Integer facultyId) {
		
		Faculty faculty = facultyRepository.getById(facultyId);

		User user =userRepository.getById(userId);
		faculty.getUsers().remove(user);
	}
	

}