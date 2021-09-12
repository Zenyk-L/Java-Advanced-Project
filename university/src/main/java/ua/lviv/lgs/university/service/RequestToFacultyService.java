package ua.lviv.lgs.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.university.dao.RequestToFacultyRepository;
import ua.lviv.lgs.university.domain.RequestToFaculty;

@Service
public class RequestToFacultyService {
	
	@Autowired
	private  RequestToFacultyRepository requestToFacultyRepository;
	
	public RequestToFaculty save(RequestToFaculty requestToFaculty) {
		return requestToFacultyRepository.save(requestToFaculty);
	}
	
	public List<RequestToFaculty> getAllRequest(){
		return requestToFacultyRepository.findAll();
	}

}
