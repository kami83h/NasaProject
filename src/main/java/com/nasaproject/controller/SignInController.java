package com.nasaproject.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		return new ModelAndView("forward:/index");
	}

	@RequestMapping("/signOut")
	public ModelAndView signOut(HttpSession session) {

		session.setAttribute("isSignedIn", false);
		return new ModelAndView("forward:/index");
	}
}
