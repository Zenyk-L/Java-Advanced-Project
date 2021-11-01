package ua.lviv.lgs.university.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.university.dao.RequestToFacultyRepository;
import ua.lviv.lgs.university.domain.RequestToFaculty;

@Service
public class RequestToFacultyService {
	private Logger logger = LoggerFactory.getLogger(RequestToFacultyService.class);


	@Autowired
	private  RequestToFacultyRepository requestToFacultyRepository;
	
	public RequestToFaculty save(RequestToFaculty requestToFaculty) {
		logger.info("Save request ot faculty " + requestToFaculty);

		return requestToFacultyRepository.save(requestToFaculty);
	}
	
	public List<RequestToFaculty> getAllRequest(){
		logger.info("Get all request");

		return requestToFacultyRepository.findAll();
	}
	
	public void delete(Integer id) {
		logger.info("Delete request ot faculty with id " + id);

		requestToFacultyRepository.deleteById(id);
	}
	

}
