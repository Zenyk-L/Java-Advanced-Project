package ua.lviv.lgs.university.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ua.lviv.lgs.university.domain.Faculty;
import ua.lviv.lgs.university.domain.FacultyName;
import ua.lviv.lgs.university.domain.RequestToFaculty;
import ua.lviv.lgs.university.domain.Subject;
import ua.lviv.lgs.university.domain.User;
import ua.lviv.lgs.university.service.FacultyService;
import ua.lviv.lgs.university.service.RequestToFacultyService;
import ua.lviv.lgs.university.service.UserService;

@Controller
public class FacultyController {

	@Autowired
	private FacultyService facultyService;

	@Autowired
	private UserService userService;

	@Autowired
	private RequestToFacultyService requestToFacultyService;

	@GetMapping(value = "/createFaculty")
	public ModelAndView createFaculty(Model model) {
		model.addAttribute("namesList", FacultyName.values());
		model.addAttribute("subjectsList", Subject.values());
		return new ModelAndView("createFaculty", "faculty", new Faculty());
	}

	@PostMapping(value = "/addFaculty")
	public ModelAndView addFaculty(@RequestParam MultipartFile file,
			@Validated @ModelAttribute("faculty") Faculty faculty, BindingResult bindingResult) throws IOException {

		if (faculty.getSubjects().size() == 0) {
			List<Subject> list = new ArrayList<Subject>();
			list.add(Subject.UKRAINIAN_LANGUAGE);
			faculty.setSubjects(list);
		}
		faculty.setEncodedImage(Base64.getEncoder().encodeToString(file.getBytes()));
		facultyService.save(faculty);
		return new ModelAndView("redirect:/home");

	}

	@GetMapping(value = "/home")
	public ModelAndView welcome(Model model) {
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("faculty", facultyService.getAllFaculty());
		return mav;
	}
	
	@GetMapping(value = "/showAllFacultys")
	public ModelAndView allUniversity(Model model) {
		ModelAndView mav = new ModelAndView("university");
		mav.addObject("faculty", facultyService.getAllFaculty());
		return mav;
	}
	
	@GetMapping("/deleteUser")
	public ModelAndView deleteUserFromFaculty(@RequestParam("userId") Integer userId, @RequestParam("facultyId") Integer facultyId) {
System.out.println(userId);
System.out.println(facultyId);
		facultyService.deleteUserFromFaculty(userId, facultyId);
		ModelAndView modelAndView = new ModelAndView("university");
		modelAndView.addObject("faculty", facultyService.getAllFaculty());
		return modelAndView;
	}

	@GetMapping(value = "/showAllRequest")
	public ModelAndView showAllRequest(Model model) {
		ModelAndView mav = new ModelAndView("showAllRequest");
		mav.addObject("requests", requestToFacultyService.getAllRequest());
		mav.addObject("request", new RequestToFaculty());
		return mav;
	}

	@GetMapping("/addRequestToFaculty")
	public ModelAndView registerForFaculty(@RequestParam("facultyId") Integer id, @RequestParam("email") String email) {
		Faculty faculty = facultyService.getFacultyById(id);
		User user = userService.getUserByEmail(email);
		requestToFacultyService.save(new RequestToFaculty(user, faculty));
		ModelAndView modelAndView = new ModelAndView("redirect:/home");
		return modelAndView;
	}

	@GetMapping("/deleteFaculty")
	public ModelAndView deleteFaculty(@RequestParam("facultyId") Integer id, @RequestParam("email") String email) {

		facultyService.deleteFacultyById(id);
		ModelAndView modelAndView = new ModelAndView("redirect:/home");
		return modelAndView;
	}

	@PostMapping(value = "/asseptRequest")
	public String addFaculty(@Validated @ModelAttribute("request") RequestToFaculty requestToFaculty, Model model) {


		String message;
		User user = requestToFaculty.getUser();
		Faculty faculty = requestToFaculty.getFaculty();

		if (faculty.getUsers().contains(user)) {
			message = "Sorry you alredy student of this faculty";
			System.out.println(message);
			requestToFacultyService.delete(requestToFaculty.getId());

			model.addAttribute("message", message);
			return "messageAfterRequest";

		}
		if (faculty.getUsers().size() >= faculty.getStudentQuantity()) {
			message = "No available place";
			System.out.println(message);
			requestToFacultyService.delete(requestToFaculty.getId());
			model.addAttribute("message", message);
			return "messageAfterRequest";
		}
//		Integer requestId = requestToFaculty.getId();
		List<Subject> facultySubjects = faculty.getSubjects();
		int count = 0;
		double avg = 0;
		for (Subject subject : facultySubjects) {
			if (user.getPresentSubjects().contains(subject)) {
				avg += user.getGradeBySubject(subject);
				count++;
			}
		}
		if (count != 0) {
			avg = avg / count;
		}

		System.out.println(requestToFaculty.getId() + " " + requestToFaculty.getFaculty().getName() + " "
				+ requestToFaculty.getUser().getEmail());
		System.out.println(avg);

		if (faculty.getRequiredLevel() <= avg) {
			faculty.getUsers().add(user);
			message = "congratulation you are student now";
			System.out.println(message);
		} else {
			message = "Sorry your grade is to low " + avg + " needed " + faculty.getRequiredLevel();
			System.out.println(message);
		}
		requestToFacultyService.delete(requestToFaculty.getId());

		model.addAttribute("message", message);
		return "messageAfterRequest";

	}

}
