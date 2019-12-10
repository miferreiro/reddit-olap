package com.si.reddit.controllers;

import java.util.ArrayList;
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

import com.si.reddit.entities.User;
import com.si.reddit.services.UserService;

import com.si.reddit.entities.Subreddit;
import com.si.reddit.services.SubredditService;

@Controller
@RequestMapping("/follows")
public class UserFollowSubredditController {
	@Autowired
	UserFollowSubredditService userFollowSubredditService;
	@Autowired
	UserService userService;
	@Autowired
	SubredditService subredditService;
	@GetMapping
	public String listUsersFollowSubreddit(Model model) {
		List<UserFollowSubreddit> usersFollowSubreddit = userFollowSubredditService.searchAll();
		model.addAttribute("usersFollowSubreddit", usersFollowSubreddit);
		return "follows/list_user_follow_subreddit";
	}

	@PostMapping
	public String refreshListUsersFollowSubreddit(@RequestParam(required = false) String DNIUser, String nameSubreddit, Model model) {
		List<UserFollowSubreddit> usersFollowSubreddit = new ArrayList<>();
		if (((DNIUser != null) && !DNIUser.isEmpty())) {
			usersFollowSubreddit = userFollowSubredditService.searchByDNIUser(DNIUser);
		} else {
			if (((nameSubreddit != null) && !nameSubreddit.isEmpty())) {
				usersFollowSubreddit = userFollowSubredditService.searchByNameSubreddit(nameSubreddit);
			} else {
				usersFollowSubreddit = userFollowSubredditService.searchAll();				
			}
		}
		
		if (usersFollowSubreddit.size() == 1 && usersFollowSubreddit == null) {
			usersFollowSubreddit = new ArrayList<>();
		}
		model.addAttribute("usersFollowSubreddit", usersFollowSubreddit);
		return "follows/list_user_follow_subreddit";
	}

	@GetMapping("{id}/{DNI}/remove")
	public String removeUserFollowSubreddit(@PathVariable("id") Long id, @PathVariable("DNI") String DNI, Model model) {
		UserFollowSubreddit userFollowSubreddit = userFollowSubredditService.searchByIds(DNI, id);
		if (userFollowSubreddit != null) {
			userFollowSubredditService.remove(userFollowSubreddit);
			return "redirect:/follows";
		} else {
			model.addAttribute("messageError", "Seguidor " + DNI + " de " + id + " no encontrado");
			model.addAttribute("pageToReturn", "follows");
			return "error";
		}
	}

	@GetMapping("add_user_follow_subreddit")
	public String addUserFollowSubredditView(Model model) {
		UserFollowSubreddit userFollowSubreddit = new UserFollowSubreddit();
		List<User> users = userService.searchAll();
		List<Subreddit> subreddits = subredditService.searchAll();
		model.addAttribute("users", users);
		model.addAttribute("subreddits", subreddits);
		return "follows/add_user_follow_subreddit";
	}
	
	@PostMapping("add_user_follow_subreddit")
	public String createUserFollowSubredditView(String user, Long subreddit) {
		User userObject = userService.searchByDNI(user);
		Subreddit subredditObject = subredditService.searchById(Long.valueOf(subreddit));
		userFollowSubredditService.create(new UserFollowSubreddit(userObject, subredditObject));
		return "redirect:/follows";
	}
}
