package ua.lviv.lgs.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.lviv.lgs.university.domain.Mark;
import ua.lviv.lgs.university.domain.MarksListContainer;
import ua.lviv.lgs.university.domain.User;
import ua.lviv.lgs.university.service.UserService;

import java.util.List;

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
	public String addMarks(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.getName();
		
		User user = userService.getUserByEmail(userEmail);
		
		List<Mark> marksList = user.getFillMarkList();
		user.setMarkList(marksList); 
		MarksListContainer container = new MarksListContainer();
		container.setMarks(marksList);

		model.addAttribute("container", container);
		return "fillMarks";		
	}
	
	@RequestMapping(value = { "/addMarks" }, method = RequestMethod.POST)
	public ModelAndView saveMarks(@Validated @ModelAttribute("container") MarksListContainer container ) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.getName();

		User user = userService.getUserByEmail(userEmail);
		user.setMarkList(container.getMarks());
		userService.save(user);
		
		return new ModelAndView("redirect:/home");
	}



}
