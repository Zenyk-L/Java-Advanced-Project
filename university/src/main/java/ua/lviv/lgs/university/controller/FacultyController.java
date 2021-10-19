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
	public ModelAndView addFaculty(	@RequestParam MultipartFile file, @Validated @ModelAttribute("faculty") Faculty faculty, BindingResult bindingResult) throws IOException {
		
	
			if(faculty.getSubjects().size() == 0) {
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
	
	
	@GetMapping(value = "/showAllRequest")
	public ModelAndView showAllRequest(Model model) {
		ModelAndView mav = new ModelAndView("showAllRequest");
		mav.addObject("requests", requestToFacultyService.getAllRequest());
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

//	
//	@PostMapping("/addRequestToFaculty")
//    public ModelAndView registerForFaculty1(@RequestParam("facultyId") Integer id, @RequestParam("email") String email) {
//        Faculty faculty = facultyService.getFacultyById(id);
//        User user = userService.getUserByEmail(email);
//        requestToFacultyService.save(new RequestToFaculty(user, faculty));
//
//        ModelAndView modelAndView = new ModelAndView("redirect:/home");
//        return modelAndView;
//    }
	
	
	
	
//	@PostMapping(value = "/addRequestToFaculty")
//	public ModelAndView addRequestToFacult(	HttpServletRequest request,  BindingResult bindingResult) {
//		String userEmal = request.getParameter("userEmail")	;
//		System.out.println( "   " + userEmal);
////		Faculty faculty = facultyService.getFacultyByName(facultyName);
//			User user = userService.getUserByEmail(userEmal);
//			requestToFacultyService.save(new RequestToFaculty(user, new Faculty()));
////			return new ModelAndView("redirect:/home");	
//			return null;
//		
//	}
	
	
//	@GetMapping(value = "/addMarks")
//	public ModelAndView addMarks(Model model) {
//		Map <Subject, Integer> marksMap = new HashMap<Subject, Integer>();
////		model.addAttribute("namesList", FacultyName.values());
//		model.addAttribute("subjectsList", Subject.values());
//		return new ModelAndView("fillMarks", "marksMap", marksMap);
//	}

}
