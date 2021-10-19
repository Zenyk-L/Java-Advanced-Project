package ua.lviv.lgs.university.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.lviv.lgs.university.domain.Faculty;
import ua.lviv.lgs.university.domain.FacultyName;
import ua.lviv.lgs.university.domain.MarksMap;
import ua.lviv.lgs.university.domain.Subject;
import ua.lviv.lgs.university.domain.User;
import ua.lviv.lgs.university.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new User());
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "registration";
		}
		userService.save(userForm);

		return "redirect:/home";
	}

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}

	@RequestMapping(value = { "/addMarks" }, method = RequestMethod.GET)
	public ModelAndView addMarks(@RequestParam("email") String email, Model model) {
		User user = userService.getUserByEmail(email);

		Map<Subject, Integer> marksMap = user.getMarkMap();
		Subject[] subjects = Subject.values();
		for (Subject subject : subjects) {
			marksMap.putIfAbsent(subject, 0);
		}

		ModelAndView modelAndView = new ModelAndView("fillMarks");
		modelAndView.addObject("subjectsMap", marksMap);
		modelAndView.addObject("user1", user);
		System.out.println(user);
		return modelAndView;
	}
	
	@RequestMapping(value = { "/addMarks" }, method = RequestMethod.POST)
	public ModelAndView saveMarks(@Validated @ModelAttribute("user1") User user, BindingResult bindingResult) {
		System.out.println(user);
		return new ModelAndView("redirect:/home");
	}

	
	
//	@RequestMapping(value = { "/addMarks" }, method = RequestMethod.GET)
//	public ModelAndView addMarks(@RequestParam("email") String email, Model model) {
//		 User user = userService.getUserByEmail(email);
//
//		 Map <Subject, Integer> marksMap = user.getMarkMap();
//		 Subject[] subjects = Subject.values();
////		 for (Subject subject : subjects) {
////			if (!marksMap.containsKey(subjects)) {
////				marksMap.put(subject, 0);
////			}
////		}
////		 userService.save(user);
//
//		 model.addAttribute("mail", email);
//		 model.addAttribute("subjectsMap", marksMap);
//
//		 ModelAndView modelAndView = new ModelAndView("fillMarks", "marksMap", new MarksMap());
//System.out.println(marksMap);
//
//		 return modelAndView;
//
//	}

	

}
