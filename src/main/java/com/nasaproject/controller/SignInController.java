package com.nasaproject.controller;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nasaproject.bean.User;
import com.nasaproject.service.UserService;

/**
 * The Sign In Controller that controls whether or not the User is successfully Signed in or not.
 *
 * @author Kami Hassanzadeh
 */

@Controller
public class SignInController {

	private UserService userService;

	public SignInController() {
		userService = new UserService();
	}

	@RequestMapping("/admin")
	public ModelAndView signIn(@Valid @ModelAttribute User user, HttpServletRequest request) {

		if(inputMatches(user.getUsername())){
			
			return new ModelAndView("forward:/index?error=Only%20alphanumeric%20characters%20allowed!");
		}
		else {
			User entity = userService.findByUsername(user);
			if(user.getUsername().equals(entity.getUsername())
					&& user.getPassword().equals(entity.getPassword())) {

				HttpSession session = request.getSession(true);

				session.setAttribute("id", entity.getId());
				session.setAttribute("userName", entity.getUsername());
				session.setAttribute("isSignedIn", true);
			}
			else {
				return new ModelAndView("forward:/index?error=Invalid%20Credentials%20provided!");
			}
		}
		return new ModelAndView("forward:/index");
	}
	
	@PostMapping(value= "/user/add")
	public ModelAndView addEmployee(@ModelAttribute User user, Model model) {

		model.addAttribute("user", new User());
		List<User> userList =  userService.findAllUsers();
		
		if (inputMatches(user.getUsername())) {
			return new ModelAndView("forward:/index?error=Only%20alphanumeric%20characters%20allowed!");
		}
		else {
			for(User userlist : userList) {
				if(userlist.getUsername().equals(user.getUsername())) {
					return new ModelAndView("forward:/index?error=User%20already%20exists !");
				}
			}
			userService.addUser(user);
		}
		
		return new ModelAndView("redirect:/index");
	}

	@RequestMapping("/signOut")
	public ModelAndView signOut(HttpSession session) {

		session.setAttribute("isSignedIn", false);
		return new ModelAndView("forward:/index");
	}

	public boolean inputMatches(String input) {
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
		Matcher matcher = pattern.matcher(input);
		if(matcher.find()) {
			return false;
		}
		else return true;
	}
}
