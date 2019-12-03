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

import com.si.reddit.entities.UserFollowSubreddit;
import com.si.reddit.services.UserFollowSubredditService;

@Controller
@RequestMapping("/follows")
public class UserFollowSubredditController {
	@Autowired
	UserFollowSubredditService userFollowSubredditService;

	@GetMapping
	public String listUsersFollowSubreddit(Model model) {
		List<UserFollowSubreddit> usersFollowSubreddit = userFollowSubredditService.searchAll();
		model.addAttribute("usersFollowSubreddit", usersFollowSubreddit);
		return "follows/list_user_follow_subreddit";
	}

	@PostMapping
	public String refreshListUsersFollowSubreddit(@RequestParam(required = false) String dni, Long id, Model model) {
		List<UserFollowSubreddit> usersFollowSubreddit;
		if ((dni != null) && !dni.isEmpty()) {
			//usersFollowSubreddit = userFollowSubredditService.searchByName(nameUser);
			usersFollowSubreddit = userFollowSubredditService.searchAll();
		} else {
			usersFollowSubreddit = userFollowSubredditService.searchAll();
		}
		model.addAttribute("usersFollowSubreddit", usersFollowSubreddit);
		return "follows/list_user_follow_subreddit";
	}
/*
	@GetMapping("{dni}/{id}/remove")
	public String removeUserFollowSubreddit(@PathVariable("dni") String dni, Long id, Model model) {
		UserFollowSubreddit userFollowSubreddit = userFollowSubredditService.searchByIds(dni, id);
		if (userFollowSubreddit != null) {
			userFollowSubredditService.remove(userFollowSubreddit);
			return "redirect:/follows";
		} else {
			model.addAttribute("messageError", "Seguidor " + dni + " de " + id + " no encontrado");
			model.addAttribute("pageToReturn", "follows");
			return "error";
		}
	}

	@GetMapping("add_user_follow_subreddit")
	public String addUserFollowSubredditView(Model model) {
		UserFollowSubreddit userFollowSubreddit = new UserFollowSubreddit();
		model.addAttribute("userFollowSubreddit", userFollowSubreddit);
		model.addAttribute("isNew", true);
		return "follows/edit_user_follow_subreddit";
	}
	
	@PostMapping("add_user_follow_subreddit")
	public String createUserFollowSubredditView(@ModelAttribute UserFollowSubreddit userFollowSubreddit) {
		userFollowSubredditService.create(userFollowSubreddit);
		return "redirect:/follows";
	}
	*/
}
