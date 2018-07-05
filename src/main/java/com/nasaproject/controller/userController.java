package com.nasaproject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nasaproject.bean.Camera;
import com.nasaproject.bean.Favorit;
import com.nasaproject.bean.User;
import com.nasaproject.repository.PhotoRepository;
import com.nasaproject.service.PhotoService;
import com.nasaproject.service.UserService;

/**
 * The User Controller that controls the User Management.
 * @author Kami Hassanzadeh
 *
 */
@Controller
public class userController {

	private UserService userService;
	private PhotoService photoService;
	private PhotoRepository photoRepository;

	public userController() {
		userService = new UserService();
		photoService = new PhotoService();
		photoRepository = new PhotoRepository();
	}

	@RequestMapping(value = {"/", "index"})
	public String customerForm(Model model) {
		
		model.addAttribute("user", new User());
		model.addAttribute("photos", photoService.findAllPhoto());
		return "index";
	}
	
	@GetMapping("/pageChoose/{id}")
	public ModelAndView pageChoose(@PathVariable("id") int id, Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession(true);
		Camera camera = photoRepository.getImageIdByPage(Integer.toString(id));
		
		session.setAttribute("cameraResult", camera);
		
		List<Camera> cameraList = new ArrayList<Camera>();
		cameraList.add(camera);
		model.addAttribute("cameras", camera);

		return new ModelAndView("forward:/index");
	}
	
	@GetMapping("/pictureChoose/{id}")
	public ModelAndView pictureChoose(@PathVariable("id") int id, Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession(true);
		String result = photoService.getPhotoById(id);
		Camera camera = photoRepository.getImageIdByNumber(result);
		session.setAttribute("cameraResult", camera);
		
		List<Camera> cameraList = new ArrayList<Camera>();
		cameraList.add(camera);
		model.addAttribute("cameras", camera);

		return new ModelAndView("forward:/index");
	}
	
	@GetMapping("/deleteFavorit/{id}")
	public ModelAndView deteleFavorit(@PathVariable("id") int id) {
		
		photoService.deletePhoto(id);
		return new ModelAndView("forward:/favoritUser");
	}
	
	@GetMapping(value = "/add/{id}")
	public ModelAndView addToFavorit(@PathVariable("id") int id, HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		Camera camera = (Camera) session.getAttribute("cameraResult");
		List<User> userList =  userService.findAllUsers();

		for(User userlist : userList) {
			if(userlist.getId() == (Integer) session.getAttribute("id")) {
				photoService.addPhotoToFavorit(userlist.getId(),camera);
				break;
			}
		}

		return new ModelAndView("redirect:/favoritUser");
	}

	@RequestMapping("/favoritUser")
	public ModelAndView favoritUser(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		List<User> userList=  userService.findAllUsers();

		for(User userlist : userList) {
			if(userlist.getId() == (Integer) session.getAttribute("id")) {
				model.addAttribute("favorits", photoService.selectFavoritById(userlist.getId()));
			}
		}
		return new ModelAndView("forward:/index");
	}

	public boolean isInFavorit(List<Favorit> favoritList, int id) {
		
		for(Favorit list : favoritList) {
			if(list.getPhotoId() == id) {
				return true;
			}
		}
		return false;
	}
}
