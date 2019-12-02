package com.si.reddit.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.si.reddit.entities.User;
import com.si.reddit.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping
	public String prepararListarClientes(Model model) {
		List<User> users = userService.searchAll();
		model.addAttribute("users", users);
		model.addAttribute("DNI", "");
		model.addAttribute("name", "");
		model.addAttribute("numTrophies", "");
		return "user/list_users";
	}

	@PostMapping
	public String refreshListUsers(@RequestParam(required = false) String userName,  Model model) {
		List<User> users;
		if ((userName != null) && !userName.isEmpty()) {
			users = userService.searchByName(userName);
		} else {
			users = userService.searchAll();
		}
		model.addAttribute("users", users);
		return "user/list_users";
	}

	@GetMapping("{dni}/remove")
	public String borrarCliente(@PathVariable("dni") String dni, Model model) {
		User user = userService.searchByDNI(dni);
		if (user != null) {
			userService.remove(user);
			return "redirect:/users";
		} else {
			model.addAttribute("messageError", "User not found");
			return "error";
		}
	}

	@GetMapping("add_user")
	public String prepararNuevoCliente(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("isNew", true);
		return "user/edit_user";
	}
	
	@PostMapping("add_user")
	public String crearCliente(@ModelAttribute User user) {
		userService.create(user);
		return "redirect:/users";
	}
	
	@GetMapping("{dni}")
	public String prepararEditarCliente(@PathVariable("dni") String dni, Model model) {
		User user = userService.searchByDNI(dni);
		if (user != null) {
			model.addAttribute("user", user);
			model.addAttribute("isNew", false);
			return "user/edit_user";
		} else {
			model.addAttribute("messageError", "User not found");
			return "error";
		}
	}

	@PostMapping("{dni}")
	public String refreshUser(@Valid User user, BindingResult resul) {
		userService.edit(user);
		return "redirect:/users";
	}

}
