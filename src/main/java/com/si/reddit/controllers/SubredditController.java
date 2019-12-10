package com.si.reddit.controllers;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.si.reddit.entities.Subreddit;
import com.si.reddit.entities.UserFollowSubreddit;
import com.si.reddit.services.SubredditService;
import com.si.reddit.services.UserFollowSubredditService;

@Controller
@RequestMapping("/subreddits")
public class SubredditController {
	@Autowired
	SubredditService subredditService;

	@Autowired
	UserFollowSubredditService userFollowSubredditService;
	
	@GetMapping
	public String listSubreddits(Model model) {
		List<Subreddit> subreddits = subredditService.searchAll();
		model.addAttribute("subreddits", subreddits);
		model.addAttribute("id", "");
		model.addAttribute("name", "");
		model.addAttribute("description", "");
		return "subreddit/list_subreddits";
	}

	@PostMapping
	public String refreshListSubreddits(@RequestParam(required = false) String nameSubreddit,  Model model) {
		List<Subreddit> subreddits;
		if ((nameSubreddit != null) && !nameSubreddit.isEmpty()) {
			subreddits = subredditService.searchByName(nameSubreddit);
		} else {
			subreddits = subredditService.searchAll();
		}
		model.addAttribute("subreddits", subreddits);
		return "subreddit/list_subreddits";
	}

	@GetMapping("{id}/remove")
	public String removeSubreddit(@PathVariable("id") Long id, Model model) {
		Subreddit subreddit = subredditService.searchById(id);
		if (subreddit != null) {
			List<UserFollowSubreddit> u = userFollowSubredditService.searchByIdSubreddit(id);
			if (u.size() != 0) {
				model.addAttribute("messageError", "Para eliminar este subreddit debe dejar de tener seguidores");
				model.addAttribute("pageToReturn", "subreddits");
				return "error";
			} else {
				subredditService.remove(subreddit);
				return "redirect:/subreddits";
			}
		} else {
			model.addAttribute("messageError", "Subreddit no encontrado");
			model.addAttribute("pageToReturn", "subreddits");
			return "error";
		}
	}

	@GetMapping("add_subreddit")
	public String addSubredditView(Model model) {
		Subreddit subreddit = new Subreddit();
		model.addAttribute("subreddit", subreddit);
		model.addAttribute("isNew", true);
		return "subreddit/edit_subreddit";
	}
	
	@PostMapping("add_subreddit")
	public String createSubreddit(@Valid @ModelAttribute Subreddit subreddit, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			subredditService.create(subreddit);
			return "redirect:/subreddits";
		} else {
			model.addAttribute("isNew", true);
			return "subreddit/edit_subreddit";
		}
	}
	
	@GetMapping("{id}")
	public String editSubredditView(@PathVariable("id") Long id, Model model) {
		try {
			Subreddit subreddit = subredditService.searchById(id);
			model.addAttribute("subreddit", subreddit);
			model.addAttribute("isNew", false);
			return "subreddit/edit_subreddit";
		} catch (EntityNotFoundException e) {
			model.addAttribute("messageError", "Subreddit no encontrado");
			model.addAttribute("pageToReturn", "subreddits");
			return "error";
		}
	}

	@PostMapping("{id}")
	public String refreshSubreddit(@Valid Subreddit subreddit, BindingResult result, Model model, @PathVariable("id") Long id) {
		subreddit.setId(id);
		if (!result.hasErrors()) {
			subredditService.edit(subreddit);
			return "redirect:/subreddits";
		} else {
			model.addAttribute("subreddit", subreddit);
			model.addAttribute("isNew", false);
			return "subreddit/edit_subreddit";
		}
	}
}
