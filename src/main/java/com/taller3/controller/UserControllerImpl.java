package com.taller3.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taller3.model.person.UserApp;
import com.taller3.model.person.UserApp.addValidator;
import com.taller3.model.person.UserApp.updateValidator;
import com.taller3.service.implementation.UserServiceImpl;

@Controller
public class UserControllerImpl {
	
	UserServiceImpl userService;
	
	@Autowired
	public UserControllerImpl(UserServiceImpl userService) {
		this.userService = userService;
	}
	
	@GetMapping("/users/")
	public String indexUser(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users/index";
	}

	@GetMapping("/index")
	public String index() {
		return "/index";
	}

	@GetMapping("/login")
	public String loginPrincipal() {
		return "/login";
	}

	@GetMapping("/users/add")
	public String addUser(Model model) {
		model.addAttribute("user", new UserApp());
		model.addAttribute("types", userService.getTypes());
		return "users/add-user";
	}
	
	@PostMapping("/users/add")
	public String saveUser(@Validated(addValidator.class) @ModelAttribute("user") UserApp user, BindingResult br, Model model, 
			@RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if(br.hasErrors()) {
				model.addAttribute("types", userService.getTypes());
				return "users/add-user";
			}
			userService.save(user);
		}
		return "redirect:/users/";
	}
	
	@GetMapping("/users/del/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		UserApp user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		userService.delete(user);
		model.addAttribute("users", userService.findAll());
		return "users/index";
	}
	
	@GetMapping("/users/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Optional<UserApp> user = userService.findById(id);
		if (user.isEmpty())
			throw new IllegalArgumentException("Invalid user Id:" + id);
		model.addAttribute("user", user.get());
		model.addAttribute("types", userService.getTypes());
		return "users/update-user";
	}

	@PostMapping("/users/edit/{id}")
	public String updateUser(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action, @Validated(updateValidator.class) @ModelAttribute("user") UserApp user, BindingResult br, Model model) {
		if (action != null && !action.equals("Cancel")) {
			if(br.hasErrors()) {
				model.addAttribute("types", userService.getTypes());
				return "users/update-user";
			}
			String pass = user.getPassword();
			String re = user.getRepeatPassword();
			if((pass.length()>=8 && pass.equals(re))) {
				userService.update(id, user);
			}else {
				user.setPassword(userService.findById(id).get().getPassword());
				userService.update(id, user);
			}
			model.addAttribute("users", userService.findAll());
		}
		return "redirect:/users/";
	}
}
