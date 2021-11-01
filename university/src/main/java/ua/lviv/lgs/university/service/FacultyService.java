package ua.lviv.lgs.university.service;

import java.util.Iterator;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.university.dao.FacultyRepository;
import ua.lviv.lgs.university.dao.UserRepository;
import ua.lviv.lgs.university.domain.Faculty;
import ua.lviv.lgs.university.domain.User;

@Service
public class FacultyService {
    private Logger logger = LoggerFactory.getLogger(FacultyService.class);

    @Autowired
    private FacultyRepository facultyRepository;


    @Autowired
    private UserRepository userRepository;

    public Faculty save(Faculty faculty) {
        logger.info("Saved faculty " + faculty);
        return facultyRepository.save(faculty);
    }

    public List<Faculty> getAllFaculty() {
        logger.info("Get all faculties");
        return facultyRepository.findAll();
    }

    public Faculty getFacultyByName(String name) {
        logger.info("Get faculty by name" + name);
        return facultyRepository.findByName(name).get();
    }

    public Faculty getFacultyById(Integer id) {
        logger.info("Get faculty by id" + id);
        return facultyRepository.getById(id);
    }

    public void deleteFacultyById(Integer id) {
        logger.info("Delete faculty by id" + id);
        facultyRepository.deleteById(id);
    }

    public void deleteUserFromFaculty(Integer userId, Integer facultyId) {

        Faculty faculty = facultyRepository.getById(facultyId);

        User user1 = userRepository.getById(userId);
        System.out.println(user1);

        List<User> userlist = faculty.getUsers();
        Iterator<User> iterator = userlist.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            System.out.println(user);
            System.out.println(user.equals(user1));

            if (user.getId().equals(userId)) {
                iterator.remove();
            }
        }

        System.out.println(userlist);
        System.out.println(faculty.getUsers());
        facultyRepository.save(faculty);
        logger.info("User with id " + userId + " delete from faculty with id" + facultyId);

    }


}
