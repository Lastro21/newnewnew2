package com.example.newnewnew.controller;


import com.example.newnewnew.model.User;
import com.example.newnewnew.service.UserService;
import com.example.newnewnew.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;

	@Autowired
	public UserServiceImpl userServiceImpl;


	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping("/logout")
	public ModelAndView logout(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("openn", "red");
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	/*                     //////////////////////                     */

	@RequestMapping(value = "/saveUserUpdate", method = RequestMethod.POST)
	public void saveUser (@ModelAttribute User user, BindingResult bindingResult, HttpServletResponse response) throws IOException {
		userServiceImpl.saveUserUpdate(user);
		response.sendRedirect("allbooks");
	}

	/*                   //////////////////////                      */

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult, @RequestParam String password, @RequestParam String confirmPassword) throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null ) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}

		if (!password.equals(confirmPassword)) {
			System.out.printf("7777777777777777777777777777");

			bindingResult
					.rejectValue("", "");

			bindingResult.hasErrors();
			modelAndView.addObject("errorRegister", "Пароли не совпадают");
			modelAndView.setViewName("registration");
		}

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		}

		else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")"+""+user.getBackground_color());
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}

	@RequestMapping("/access-denied")
	public ModelAndView accessDenied(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("access-denied");
		return modelAndView;
	}
}
