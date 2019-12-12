package com.si.reddit.controllers;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.si.reddit.entities.User;
import com.si.reddit.entities.UserFollowSubreddit;
import com.si.reddit.services.UserFollowSubredditService;
import com.si.reddit.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	UserFollowSubredditService userFollowSubredditService;
	
	@Autowired
    private MessageSource messageSource;
	
	@GetMapping
	public String listUsers(Model model) {
		List<User> users = userService.searchAll();
		model.addAttribute("users", users);
		return "user/list_users";
	}

	@PostMapping
	public String refreshListUsers(@RequestParam(required = false) String nameUser,  Model model) {
		List<User> users;
		if ((nameUser != null) && !nameUser.isEmpty()) {
			users = userService.searchByName(nameUser);
		} else {
			users = userService.searchAll();
		}
		model.addAttribute("users", users);
		return "user/list_users";
	}

	@GetMapping("{dni}/remove")
	public String removeUser(@PathVariable("dni") String dni, Model model) {
		User user = userService.searchByDNI(dni);	
		if (user != null) {
			List<UserFollowSubreddit> u = userFollowSubredditService.searchByDNIUser(dni);
			if (u.size() != 0) {
				model.addAttribute("messageError", messageSource.getMessage("UserErrorRemove", new Object[] {},LocaleContextHolder.getLocale()));
				model.addAttribute("pageToReturn", "users");
				return "error";
			} else {
				userService.remove(user);
				return "redirect:/users";
			}
		} else {
			model.addAttribute("messageError", messageSource.getMessage("UserErrorNotFound", new Object[] {},LocaleContextHolder.getLocale()));
			model.addAttribute("pageToReturn", "users");
			return "error";
		}
	}

	@GetMapping("add_user")
	public String addUserView(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("isNew", true);
		return "user/edit_user";
	}
	
	@PostMapping("add_user")
	public String createUser(@Valid @ModelAttribute User user, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			User u = userService.searchByDNI(user.getDNI());
			if (u == null) {
				userService.create(user);
				return "redirect:/users";
			} else {
				model.addAttribute("messageError", messageSource.getMessage("UserErrorCreate", new Object[] {},LocaleContextHolder.getLocale()));
				model.addAttribute("pageToReturn", "users");
				return "error";
			}
		} else {
			model.addAttribute("isNew", true);
			return "user/edit_user";
		}
	}
	
	@GetMapping("{dni}")
	public String editUserView(@PathVariable("dni") String dni, Model model) {
		try {
			User user = userService.searchByDNI(dni);
			model.addAttribute("user", user);
			model.addAttribute("isNew", false);
			return "user/edit_user";
		} catch (EntityNotFoundException e) {
			model.addAttribute("messageError", messageSource.getMessage("UserErrorNotFound", new Object[] {},LocaleContextHolder.getLocale()));
			model.addAttribute("pageToReturn", "users");
			return "error";
		}
	}

	@PostMapping("{dni}")
	public String refreshUser(@Valid User user, BindingResult result, Model model, @PathVariable("dni") String dni) {
		user.setDNI(dni);
		if (!result.hasErrors()) {
			userService.edit(user);
			return "redirect:/users";
		} else {
			model.addAttribute("user", user);
			model.addAttribute("isNew", false);
			return "user/edit_user";
		}
	}

}
