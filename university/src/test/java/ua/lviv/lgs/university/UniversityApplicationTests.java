package ua.lviv.lgs.university;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.lviv.lgs.university.dao.FacultyRepository;
import ua.lviv.lgs.university.dao.UserRepository;
import ua.lviv.lgs.university.domain.Faculty;
import ua.lviv.lgs.university.domain.FacultyName;
import ua.lviv.lgs.university.domain.User;
import ua.lviv.lgs.university.domain.UserRole;
import ua.lviv.lgs.university.service.FacultyService;
import ua.lviv.lgs.university.service.UserService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
@DataJpaTest
class UniversityApplicationTests {
	
	@Autowired
	private UserService userService;

	@Autowired
	private FacultyService facultyService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FacultyRepository facultyRepository;


	@Test
	void testSaveUser() {

		List<User> users = userRepository.findAll();
		assertThat(users, hasSize(0));

		User user = new User();
		user.setEmail("1@gmail.com");
		user.setFirstName("1");
		user.setLastName("1");
		user.setPassword("1");
		user.setPasswordConfirm("1");
		user.setRole(UserRole.ROLE_USER);

		userService.save(user);

		users = userRepository.findAll();
		assertThat(users, hasSize(1));

		User userFromDb = users.get(0);
		Assertions.assertEquals(userFromDb.getEmail(), user.getEmail());
		Assertions.assertEquals(userFromDb.getFirstName(), user.getFirstName());
		Assertions.assertEquals(userFromDb.getLastName(), user.getLastName());
		Assertions.assertEquals(userFromDb.getRole(), user.getRole());
	}

	@Test
	void testGetUserByEmail() {

		List<User> users = userRepository.findAll();
		assertThat(users, hasSize(0));

		User user = new User();
		user.setEmail("2@gmail.com");
		user.setFirstName("2");
		user.setLastName("2");
		user.setPassword("2");
		user.setPasswordConfirm("1");
		user.setRole(UserRole.ROLE_USER);

		userRepository.save(user);

		User userFromDb = userService.getUserByEmail("2@gmail.com");

		Assertions.assertEquals(userFromDb.getEmail(), user.getEmail());
		Assertions.assertEquals(userFromDb.getFirstName(), user.getFirstName());
		Assertions.assertEquals(userFromDb.getLastName(), user.getLastName());
		Assertions.assertEquals(userFromDb.getRole(), user.getRole());

	}
	

	@Test
	void testSaveFacultyl() {

		List<Faculty> faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(0));

		Faculty faculty = new Faculty();
		faculty.setName(FacultyName.BIOLOGY);
		faculty.setRequiredLevel(5.5);
		faculty.setStudentQuantity(10);
		

		facultyService.save(faculty);

		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(1));

		Faculty facultyFromDb = faculties.get(0);
		Assertions.assertEquals(faculty.getName(), facultyFromDb.getName());
		Assertions.assertEquals(faculty.getRequiredLevel(), facultyFromDb.getRequiredLevel());
		Assertions.assertEquals(faculty.getStudentQuantity(), facultyFromDb.getStudentQuantity());
	}

	@Test
	void testFindById() {

		List<Faculty> faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(0));

		Faculty faculty = new Faculty();
		faculty.setName(FacultyName.BIOLOGY);
		faculty.setRequiredLevel(5.5);
		faculty.setStudentQuantity(10);

		facultyService.save(faculty);

		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(1));

		Faculty facultyFromDb = facultyService.getFacultyById(faculties.get(0).getId());
		Assertions.assertEquals(faculty.getName(), facultyFromDb.getName());
		Assertions.assertEquals(faculty.getRequiredLevel(), facultyFromDb.getRequiredLevel());
		Assertions.assertEquals(faculty.getStudentQuantity(), facultyFromDb.getStudentQuantity());

	}

	@Test
	void testGetAllPeriodicals() {


		List<Faculty> faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(0));

		Faculty faculty = new Faculty();
		faculty.setName(FacultyName.BIOLOGY);
		faculty.setRequiredLevel(5.5);
		faculty.setStudentQuantity(10);

		Faculty faculty2 = new Faculty();
		faculty2.setName(FacultyName.CHEMISTRY);
		faculty2.setRequiredLevel(9.5);
		faculty2.setStudentQuantity(5);

		facultyRepository.saveAll(Arrays.asList(faculty,faculty2));

		faculties = facultyRepository.findAll();
		assertThat(faculties, hasSize(2));

		List<Faculty> facultiesFromDb = facultyService.getAllFaculty();

		Assertions.assertEquals(facultiesFromDb.get(0).getName(), faculty.getName());
		Assertions.assertEquals(facultiesFromDb.get(0).getRequiredLevel(), faculty.getRequiredLevel());
		Assertions.assertEquals(facultiesFromDb.get(0).getStudentQuantity(), faculty.getStudentQuantity());

		Assertions.assertEquals(facultiesFromDb.get(1).getName(), faculty2.getName());
		Assertions.assertEquals(facultiesFromDb.get(1).getRequiredLevel(), faculty2.getRequiredLevel());
		Assertions.assertEquals(facultiesFromDb.get(1).getStudentQuantity(), faculty2.getStudentQuantity());


	}
	
	

}
